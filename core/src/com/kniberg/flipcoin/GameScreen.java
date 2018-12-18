package com.kniberg.flipcoin;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class GameScreen extends ScreenAdapter {
    private final FlipCoinGame game;
    private final Stage stage;

    public GameScreen(FlipCoinGame game) {
        this.game = game;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);


        Label label = new Label("Game Screen", game.skin);
        label.setPosition(100, 400);
        stage.addActor(label);


        CoinView coinView = new CoinView();
        coinView.addAction(
                Actions.forever(
                        Actions.sequence(
                                Actions.moveBy(20, 20, 0.5f),
                                Actions.moveBy(-20, -20, 0.5f)
                        )
                )
        );
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
