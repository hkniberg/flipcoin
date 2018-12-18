package com.kniberg.flipcoin;

public class FlipCoinModel {
    private int score;
    private Coin coin = new Coin();
    private CoinState bet = null;


    public Coin getCoin() {
        return coin;
    }


    public CoinState getBet() {
        return bet;
    }

    public void setBet(CoinState bet) {
        this.bet = bet;
    }
}
