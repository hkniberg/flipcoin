package com.kniberg.flipcoin;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class GameScreen extends ScreenAdapter {
    private FlipCoinGame game;
    private FlipCoinModel model;
    private Stage stage;

    public GameScreen(FlipCoinGame game) {
        this.game = game;
        this.model = new FlipCoinModel();
        this.model.getCoin().setState(CoinState.HEADS);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);


        Label label = new Label("What do you bet on?", game.skin);
        label.setPosition(100, 400);
        stage.addActor(label);

        Button headsButton = new TextButton("Heads", game.skin);
        headsButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Heada");
                return true;
            }
        });
        headsButton.setPosition(100, 50);
        stage.addActor(headsButton);

        Button tailsButton = new TextButton("Tails", game.skin);
        tailsButton.setPosition(500, 50);
        tailsButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Tails");
                return true;
            }
        });
        stage.addActor(tailsButton);

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
        coinView.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Coin click");
                model.getCoin().flip();
                return true;
            }
        });

        stage.addActor(coinView);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }
}
