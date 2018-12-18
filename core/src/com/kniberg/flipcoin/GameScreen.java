package com.kniberg.flipcoin;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Timer;

public class GameScreen extends ScreenAdapter {
    public static final float SPIN_TIME = 1.5f;
    private FlipCoinGame game;
    private FlipCoinModel model;
    private Stage stage;
    private Group buttonGroup;
    private Label scoreLabel;
    private Label winLabel;
    private Label loseLabel;

    public GameScreen(FlipCoinGame game) {
        this.game = game;
        this.model = new FlipCoinModel();
        this.model.getCoin().setState(CoinState.HEADS);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);


        scoreLabel = new Label("", game.skin);
        scoreLabel.setPosition(10, 500);
        stage.addActor(scoreLabel);
        updateScoreLabel();

        Label instructionsLabel = new Label("What do you bet on?", game.skin);
        instructionsLabel.setPosition(10, 400);
        stage.addActor(instructionsLabel);

        buttonGroup = createButtons(200);
        stage.addActor(buttonGroup);

        winLabel = new Label("You win!", game.skin);
        winLabel.setPosition(10, 100);
        stage.addActor(winLabel);
        loseLabel = new Label("You lose!", game.skin);
        loseLabel.setPosition(10, 100);
        stage.addActor(loseLabel);
        updateWinLoseLabel();

        CoinView coinView = new CoinView(this.model.getCoin());
        coinView.setPosition(400, 300);
        /*
        coinView.addAction(
                Actions.forever(
                        Actions.sequence(
                                Actions.moveBy(20, 20, 0.5f),
                                Actions.moveBy(-20, -20, 0.5f)
                        )
                )
        );
        */
        coinView.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Coin click");
                model.getCoin().flip();
                return true;
            }
        });

        stage.addActor(coinView);
    }

    private void updateWinLoseLabel() {
        if (model.getCoin().isSpinning() || model.getBet() == null) {
            winLabel.setVisible(false);
            loseLabel.setVisible(false);
        } else if (model.isWin()) {
            winLabel.setVisible(true);
            loseLabel.setVisible(false);
        } else {
            winLabel.setVisible(false);
            loseLabel.setVisible(true);
        }
    }

    private Group createButtons(int buttonY) {
        Group buttonGroup = new Group();

        Button headsButton = new TextButton("Heads", game.skin);
        headsButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                placeBet(CoinState.HEADS);
                return true;
            }
        });
        headsButton.setPosition(100, buttonY);
        buttonGroup.addActor(headsButton);

        Button tailsButton = new TextButton("Tails", game.skin);
        tailsButton.setPosition(500, buttonY);
        tailsButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                placeBet(CoinState.TAILS);
                return true;
            }
        });
        buttonGroup.addActor(tailsButton);
        return buttonGroup;
    }

    private void updateScoreLabel() {
        scoreLabel.setText("Score: " + model.getScore());
    }

    private void placeBet(CoinState bet) {
        model.setBet(bet);
        model.getCoin().spin();
        buttonGroup.setVisible(false);
        updateWinLoseLabel();

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                model.getCoin().endSpin();
                buttonGroup.setVisible(true);
                if (model.isWin()) {
                    wonBet();
                } else {
                    lostBet();
                }
                updateWinLoseLabel();
            }
        }, SPIN_TIME);
    }

    private void wonBet() {
        model.addToScore();
        updateScoreLabel();
    }

    private void lostBet() {
        model.removeFromScore();
        updateScoreLabel();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }
}
