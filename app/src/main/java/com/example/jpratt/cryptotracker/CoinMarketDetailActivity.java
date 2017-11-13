package com.example.jpratt.cryptotracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.jpratt.cryptotracker.model.CoinMarketInfo;
import com.example.jpratt.cryptotracker.model.SearchResponse;
import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class CoinMarketDetailActivity extends AppCompatActivity {

    TextView marketSymbol;
    TextView high;
    TextView low;
    TextView bid;
    TextView ask;
    TextView last;
    TextView volume;
    TextView baseVolume;
    TextView openBuyOrders;
    TextView openSellOrders;
    TextView buySellOrderRatio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_market_detail);
        locateViews();
        bindData();
    }

    public void locateViews(){
        marketSymbol = (TextView) findViewById(R.id.marketSymbol);
        high = (TextView) findViewById(R.id.high);
        low = (TextView) findViewById(R.id.low);
        bid = (TextView) findViewById(R.id.bid);
        ask = (TextView) findViewById(R.id.ask);
        last = (TextView) findViewById(R.id.last);
        volume = (TextView) findViewById(R.id.volume);
        baseVolume = (TextView) findViewById(R.id.baseVolume);
        openBuyOrders = (TextView) findViewById(R.id.openBuyOrders);
        openSellOrders = (TextView) findViewById(R.id.openSellOrders);
        buySellOrderRatio = (TextView) findViewById(R.id.buySellOrderRatio);
    }

    public void bindData() {
        Intent intent = getIntent();
        String jsonExtra = "";
        String symbol = "";
        if (intent.hasExtra("coinMarketInfo")) {
            jsonExtra = intent.getStringExtra("coinMarketInfo");
        }
        if(intent.hasExtra("marketSymbol")){
            symbol = intent.getStringExtra("marketSymbol");
        }

        CoinMarketInfo cm = createCoinMarketInfoFromGson(jsonExtra);

        NumberFormat formatter = NumberFormat.getCurrencyInstance();

        marketSymbol.setText(symbol);
        high.setText(cm.high);
        low.setText(cm.low);
        bid.setText(cm.bid);
        ask.setText(cm.ask);
        last.setText(cm.last);
        volume.setText(formatter.format(Double.parseDouble(cm.volume)));
        baseVolume.setText(cm.baseVolume);
        openBuyOrders.setText(cm.openBuyOrders);
        openSellOrders.setText(cm.openSellOrders);
        double buyOrders = Double.parseDouble(cm.openBuyOrders);
        double sellOrders = Double.parseDouble(cm.openSellOrders);
        String orderRatio = Double.toString(buyOrders / sellOrders);
        buySellOrderRatio.setText(orderRatio);
    }

    public CoinMarketInfo createCoinMarketInfoFromGson(String json) {
        Gson gson = new Gson();
        SearchResponse searchResponse = gson.fromJson(json, SearchResponse.class);
        CoinMarketInfo cmi = searchResponse.results.get(0);
        return cmi;
    }
}
