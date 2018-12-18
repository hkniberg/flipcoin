package com.kniberg.flipcoin;

public class Coin {
    private CoinState state = CoinState.HEADS;

    public CoinState getState() {
        return state;
    }

    public void setState(CoinState state) {
        this.state = state;
    }

    public void flip() {
        if (state == CoinState.HEADS) {
            this.state = CoinState.TAILS;
        } else if (state == CoinState.TAILS) {
            this.state = CoinState.HEADS;
        }
    }
}
