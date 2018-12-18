package com.kniberg.flipcoin.model;

/**
 * A model of the game itself
 */
public class FlipCoinGameModel {
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

    public boolean isWin() {
        return coin.getState() == bet;
    }

    public void addToScore() {
        score += 1;
    }

    public void removeFromScore() {
        score -= 1;
    }

    public int getScore() {
        return score;
    }
}
