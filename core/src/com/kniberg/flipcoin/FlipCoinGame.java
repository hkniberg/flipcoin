package com.kniberg.flipcoin;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class FlipCoinGame extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	public Skin skin;
	public Sound buttonClickSound;

	private Music music;

	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

		StartScreen startScreen = new StartScreen(this);
		setScreen(startScreen);

		buttonClickSound = Gdx.audio.newSound(Gdx.files.internal("click.wav"));

		music = Gdx.audio.newMusic(Gdx.files.getFileHandle("Crazy-Keys-Groove.mp3", Files.FileType.Internal));
		music.setVolume(0.3f);
		music.setLooping(true);
		music.play();
	}

	public void showPlayScreen() {
		setScreen(new GameScreen(this));
	}
}
