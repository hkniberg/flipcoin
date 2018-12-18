package com.kniberg.flipcoin;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class StartScreen extends ScreenAdapter {
    private final FlipCoinGame game;
    private Stage stage;

    public StartScreen(final FlipCoinGame game) {
        this.game = game;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        Button button = new TextButton("Start game", game.skin,"small");
        button.setPosition(100, 100);
        button.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.showPlayScreen();
                return true;
            }
        });
        stage.addActor(button);


        Label label = new Label("Flip Coin!", game.skin);
        label.setPosition(100, 400);
        stage.addActor(label);

    }

    @Override
    public void render(float delta) {



        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }
}
