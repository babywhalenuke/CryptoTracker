package com.example.jpratt.cryptotracker.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by jpratt on 11/3/2017.
 */

public class CoinMarketInfo {

    @SerializedName("MarketName")
    public String marketName;

    @SerializedName("High")
    public String high;

    @SerializedName("Low")
    public String low;

    @SerializedName("Volume")
    public String volume;

    @SerializedName("Last")
    public String last;

    @SerializedName("BaseVolume")
    public String baseVolume;

    @SerializedName("TimeStamp")
    public String timestamp;

    @SerializedName("Bid")
    public String bid;

    @SerializedName("Ask")
    public String ask;

    @SerializedName("OpenBuyOrders")
    public String openBuyOrders;

    @SerializedName("OpenSellOrders")
    public String openSellOrders;

    @SerializedName("PrevDay")
    public String prevDay;

    @SerializedName("Created")
    public String created;

}
