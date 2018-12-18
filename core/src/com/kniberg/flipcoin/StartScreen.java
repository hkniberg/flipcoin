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
import com.badlogic.gdx.utils.Timer;
import com.kniberg.flipcoin.model.Coin;

/**
 * This is just shown in the beginning
 */
public class StartScreen extends ScreenAdapter {
    private Stage stage;

    public StartScreen(final FlipCoinGame game) {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        Label label = new Label("Flip Coin!", game.skin);
        label.setPosition(100, 400);
        stage.addActor(label);

        Button startGameButton = new TextButton("Start game", game.skin,"small");
        startGameButton.setPosition(100, 100);
        startGameButton.setZIndex(100);
        startGameButton.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.buttonClickSound.play();
                game.showPlayScreen();
                return true;
            }
        });
        stage.addActor(startGameButton);

        startDroppingCoins();
    }

    private void startDroppingCoins() {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                addDroppingCoin();
            }
        }, 1, 1);
    }

    private void addDroppingCoin() {
        Coin coin = new Coin();
        CoinView coinView = new CoinView(coin);
        coinView.setPosition(randomInt(0, 800), 600);
        coinView.addAction(Actions.moveBy(0, -800, 5));
        coinView.setZIndex(0);
        coinView.setScale(randomFloat(0.2f, 1f));
        coinView.setOpacity(0.5f);
        switch (randomInt(0,3)) {
            case 0: coin.setHeads(); break;
            case 1: coin.setTails(); break;
            case 2: coin.spin(); break;
        }

        stage.addActor(coinView);
    }


    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    private float randomFloat(float min, float max) {
        return (float) (min + Math.random() * (max - min));
    }
    private int randomInt(int min, int max) {
        return (int) (min + Math.random() * (max - min));
    }
}
