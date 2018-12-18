package com.kniberg.flipcoin;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.kniberg.flipcoin.model.Coin;
import com.kniberg.flipcoin.model.CoinState;

/**
 * An Actor that displays a single coin, and updates depending on the CoinState of the coin.
 */
public class CoinView extends Actor {
    private Coin coin;
    private Texture headsTexture;
    private Texture tailsTexture;
    private Animation<TextureRegion> spinAnimation;
    private float animationTime = 0;
    private float opacity = 1f;

    public CoinView(Coin coin) {
        this.coin = coin;
        headsTexture = new Texture("coin-heads.png");
        tailsTexture = new Texture("coin-tails.png");

        setWidth(100f);
        setHeight(100f);

        Texture animationSheet = new Texture("coin-animated.png");

        // Use the split utility method to create a 2D array of TextureRegions. This is
        // possible because this sprite sheet contains frames of equal size and they are
        // all aligned.
        TextureRegion[][] tmp = TextureRegion.split(animationSheet,
                animationSheet.getWidth() / 2,
                animationSheet.getHeight() / 2);

        // Place the regions into a 1D array in the correct order, starting from the top
        // left, going across first. The Animation constructor requires a 1D array.
        TextureRegion[] walkFrames = new TextureRegion[2 * 2];
        int index = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }

        // Initialize the Animation with the frame interval and array of frames
        spinAnimation = new Animation<TextureRegion>(0.05f, walkFrames);

    }


    @Override
    public void act(float delta) {
        super.act(delta);
        animationTime += delta;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        TextureRegion region;
        if (coin.getState() == CoinState.TAILS) {
            region = new TextureRegion(tailsTexture);
        } else if (coin.getState() == CoinState.HEADS) {
            region = new TextureRegion(headsTexture);
        } else {
            region = spinAnimation.getKeyFrame(animationTime, true);
        }

        batch.setColor(1, 1, 1, opacity);
        batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }


    public void setOpacity(float opacity) {
        this.opacity = opacity;
    }
}
