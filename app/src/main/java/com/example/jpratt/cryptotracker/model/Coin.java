package com.example.jpratt.cryptotracker.model;

/**
 * Created by jpratt on 10/11/2017.
 */

public class Coin {
    public String symbol;
    public double priceUSD;
    public float priceBTC;

    public Coin(String symbol, double priceUSD, float priceBTC){
        this.symbol = symbol;
        this.priceUSD = priceUSD;
        this.priceBTC = priceBTC;
    }

    public Coin(){

    }

    public String getSymbol(){
        return this.symbol;
    }
    private void setSymbol(String symbol){
        this.symbol = symbol;
    }

    public double getPriceUSD(){
        return this.priceUSD;
    }

    private void setPriceUSD(double priceUSD){
        this.priceUSD = priceUSD;
    }
}





