package com.example.jpratt.cryptotracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jpratt.cryptotracker.helpers.LogoRetriever;
import com.example.jpratt.cryptotracker.model.Coin;

import org.json.JSONException;
import org.json.JSONObject;

public class CoinDetailActivity extends AppCompatActivity {

    TextView symbolText;
    TextView priceBTC;
    TextView priceUSD;
    ImageView logoImageView;
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_detail);
        locateViews();
        bindData();
        setClickListener();
    }

    private void bindData(){
        Intent intent = getIntent();
        if(intent.hasExtra("coinJson")){
            Coin coin = new Coin();
            String extras = intent.getStringExtra("coinJson");
            try {
                JSONObject jsonObject = new JSONObject(extras);
                coin.symbol = intent.getStringExtra("coinSymbol");
                coin.priceBTC = Float.parseFloat(jsonObject.getString("BTC"));
                coin.priceUSD =  Double.parseDouble(jsonObject.getString("USD"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            populateViews(coin);
        }
    }

    private void populateViews(Coin coin){
        symbolText.setText(coin.symbol);
        priceUSD.setText(String.format("$ %f",coin.priceUSD));
        priceBTC.setText(String.format("%.8f",coin.priceBTC) + "BTC");

        LogoRetriever logoRetriever = new LogoRetriever();
        logoRetriever.loadLogos();
        String imageURL = logoRetriever.getLogo(coin.symbol);

        Glide.with(getApplicationContext()).load(imageURL).into(logoImageView);
    }

    private void locateViews(){
        backButton = (Button) findViewById(R.id.backButton);
        symbolText = (TextView) findViewById(R.id.symbolText);
        priceBTC = (TextView) findViewById(R.id.priceBTC);
        priceUSD = (TextView) findViewById(R.id.priceUSD);
        logoImageView = (ImageView) findViewById(R.id.logoImageView);
    }

    private void setClickListener(){
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
