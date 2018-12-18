package com.kniberg.flipcoin;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class FlipCoinGame extends Game {
	public final static int WIDTH = 800;
	public final static int HEIGHT = 480;

	public SpriteBatch batch;
	public BitmapFont font;
	public Skin skin;


	private Texture texture;
	private Texture coinHeads;
	private Texture coinTails;
	private Texture coinSpin;

	private Stage stage;
	private int rowHeight;
	private int colWidth;
	private Music music;

	private Animation<TextureRegion> coinAnimation;
	private Texture coinAnimationSheet;
	private SpriteBatch spriteBatch;

	private Rectangle bucket;

	// A variable for tracking elapsed time for the animation
	float stateTime;

	public void showPlayScreen() {
		setScreen(new GameScreen(this));
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));


		StartScreen startScreen = new StartScreen(this);
		setScreen(startScreen);


/*
		spriteBatch = new SpriteBatch();
		//bucketImage = new Texture(Gdx.files.internal("badlogic.jpg"));

		int coinAnimationCols = 2;
		int coinAnimationRows = 2;
		coinAnimationSheet = new Texture("coin-animated.png");
		TextureRegion[][] tmp = TextureRegion.split(coinAnimationSheet,
				coinAnimationSheet.getWidth() / coinAnimationCols,
				coinAnimationSheet.getHeight() / coinAnimationRows);

		// Place the regions into a 1D array in the correct order, starting from the top
		// left, going across first. The Animation constructor requires a 1D array.
		TextureRegion[] coinAnimationFrames = new TextureRegion[coinAnimationCols * coinAnimationRows];
		int index = 0;
		for (int i = 0; i < coinAnimationCols; i++) {
			for (int j = 0; j < coinAnimationRows; j++) {
				coinAnimationFrames[index++] = tmp[i][j];
			}
		}

		coinAnimation = new Animation<TextureRegion>(0.1f, coinAnimationFrames);
		stateTime = 0f;

		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);

		rowHeight = Gdx.graphics.getWidth() / 12;
		colWidth = Gdx.graphics.getWidth() / 12;
		addBackgroundGuide(12);

		addLabel("FlipCoinGame!", colWidth * 2, Gdx.graphics.getHeight() - rowHeight * 4, 100);
		Label label = addLabel("Press button for music", colWidth * 2, Gdx.graphics.getHeight() - rowHeight * 6, 30);
		addButton(label);

		music = Gdx.audio.newMusic(Gdx.files.getFileHandle("jazz.mp3", Files.FileType.Internal));
		music.setVolume(0.5f);
		music.setLooping(true);

		Gdx.app.log("mytag", "create");*/
	}


	private void addButton(final Label outputLabel) {
		Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

		Button button2 = new TextButton("Music",mySkin,"small");
		button2.setSize(colWidth*4,rowHeight);
		button2.setPosition(colWidth*7,Gdx.graphics.getHeight()-rowHeight*7);
		button2.addListener(new InputListener(){
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				outputLabel.setText("Nice music huh?");
				music.play();
				return true;
			}

			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				outputLabel.setText("Press button for music");
				music.stop();
			}
		});
		stage.addActor(button2);
	}

	private Label addLabel(String text, int posX, int posY, int size) {
		Label.LabelStyle labelStyle = new Label.LabelStyle();
		labelStyle.font = createFont(size);

		Label label = new Label(text,labelStyle);
		label.setPosition(posX, posY);
		stage.addActor(label);
		return label;
	}

	private BitmapFont createFont(int size) {

		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("truetypefont/Amble-Light.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = size;
		parameter.borderWidth = 1;
		parameter.color = Color.PINK;
		parameter.shadowOffsetX = 3;
		parameter.shadowOffsetY = 3;
		parameter.shadowColor = Color.GRAY;
		BitmapFont font = generator.generateFont(parameter); // font size 24 pixels
		generator.dispose();
		return font;
	}

	public void addBackgroundGuide(int columns){
		Texture texture = new Texture(Gdx.files.internal("background-block.png"));
		texture.setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);

		TextureRegion textureRegion = new TextureRegion(texture);
		textureRegion.setRegion(0,0,texture.getWidth()*columns,texture.getWidth()*columns);
		Image background = new Image(textureRegion);
		background.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getWidth());
		background.setPosition(0,Gdx.graphics.getHeight()-background.getHeight());
		stage.addActor(background);
	}

	@Override
	public void render () {
		super.render();
		/*
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act();
		stage.draw();


		stateTime += Gdx.graphics.getDeltaTime();

		// Get current frame of animation for the current stateTime
		TextureRegion currentFrame = coinAnimation.getKeyFrame(stateTime, true);
		spriteBatch.begin();
		spriteBatch.draw(currentFrame, 50, 50); // Draw current frame at (50, 50)
		spriteBatch.end();
		*/
	}
	
	@Override
	public void dispose () {

	}
}
