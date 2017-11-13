package com.example.jpratt.cryptotracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;


import com.example.jpratt.cryptotracker.helpers.AsyncHelper;
import com.example.jpratt.cryptotracker.helpers.ConnectionType;
import com.example.jpratt.cryptotracker.helpers.RequestType;
import com.example.jpratt.cryptotracker.model.CoinMarketInfo;
import com.example.jpratt.cryptotracker.model.SearchResponse;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    ListView marketListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locateViews();
        bindListViewOnClick();
        ArrayList<String> markets = getMarkets();
        bindListViewMarkets(markets);
    }

    public void locateViews() {
        marketListView = (ListView) findViewById(R.id.marketListView);
    }

    public void bindListViewOnClick(){
        marketListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedMarket = (String) marketListView.getItemAtPosition(i);
                AsyncHelper getCoinMarketInfoHelper = new AsyncHelper(ConnectionType.HttpsConnection, RequestType.GET);
                String url = getString(R.string.bittrex_coin_market_detail_base_url) + selectedMarket;
                String response = getCoinMarketInfoHelper.getData(url);
                Intent intent = new Intent(getApplicationContext(),CoinMarketDetailActivity.class);
                intent.putExtra("coinMarketInfo",response);
                intent.putExtra("marketSymbol",selectedMarket);
                startActivity(intent);
            }
        });
    }

    public void bindListViewMarkets(ArrayList<String> markets) {
        ListAdapter adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, markets);
        marketListView.setAdapter(adapter);
    }

    public ArrayList<String> getMarkets() {
        Gson gson = new Gson();
        AsyncHelper getMarketsHelper = new AsyncHelper(ConnectionType.HttpsConnection, RequestType.GET);
        String url = getString(R.string.bittrex_market_summaries_url);
        String response = getMarketsHelper.getData(url);
        SearchResponse markets = gson.fromJson(response, SearchResponse.class);
        ArrayList<String> marketList = new ArrayList<String>();
        //TODO return a list of coins that are searchable
        for (CoinMarketInfo c : markets.results) {
            marketList.add(c.marketName);
        }
        return marketList;
    }

}





