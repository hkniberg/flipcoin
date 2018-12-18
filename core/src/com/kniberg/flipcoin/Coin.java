package com.kniberg.flipcoin;

public class Coin {
    private CoinState state = CoinState.HEADS;

    public CoinState getState() {
        return state;
    }

    public void setState(CoinState state) {
        this.state = state;
    }

    public void setHeads() {
        state = CoinState.HEADS;
    }

    public void setTails() {
        state = CoinState.TAILS;
    }

    public void flip() {
        if (state == CoinState.HEADS) {
            this.state = CoinState.TAILS;
        } else if (state == CoinState.TAILS) {
            this.state = CoinState.HEADS;
        }
    }

    public void spin() {
        this.state = CoinState.SPINNING;
    }

    public void endSpin() {
        if (Math.random() < 0.5) {
            setHeads();
        } else {
            setTails();
        }
    }

    public boolean isSpinning() {
        return state == CoinState.SPINNING;
    }
}
