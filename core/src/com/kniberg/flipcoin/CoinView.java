package com.kniberg.flipcoin;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class CoinView extends Actor {
    Texture coinTexture;

    public CoinView() {
        coinTexture = new Texture("droplet.png");
        setWidth(100f);
        setHeight(100f);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        TextureRegion region = new TextureRegion(coinTexture);

        batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }
}
