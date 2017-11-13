package com.example.jpratt.cryptotracker.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jpratt on 11/3/2017.
 */

public class SearchResponse {

    @SerializedName("success")
    public String success;

    @SerializedName("message")
    public String message;

    @SerializedName("result")
    public List<CoinMarketInfo> results;
}
