package com.example.jpratt.cryptotracker.helpers;


import java.util.HashMap;

/**
 * Created by jpratt on 11/2/2017.
 */

public class LogoRetriever {
    public HashMap<String,String> LogoMap;

    public LogoRetriever(){
        LogoMap = new HashMap<String,String>();
    }

    public void loadLogos(){
        LogoMap.put("VTC", "https://vertcoin.org/wp-content/uploads/2017/08/vertcoin-logo-1.png");
        LogoMap.put("BTC", "https://bitcoin.org/img/icons/opengraph.png");
        LogoMap.put("LTC", "https://pbs.twimg.com/profile_images/681000222198112256/zd6UbNBV.png");
    }

    public String getLogo(String symbol){
        return LogoMap.get(symbol);
    }

}

