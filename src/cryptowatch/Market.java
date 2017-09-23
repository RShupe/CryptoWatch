package cryptowatch;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonValue;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.commons.io.IOUtils;

public class Market {
    static JsonArray arrayBittrex;
    
    public static void startTimer(){
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                String url = "https://bittrex.com/api/v1.1/public/getmarketsummaries";
                try {
                    URL url2 = new URL(url);
                    URLConnection con = url2.openConnection();
                    InputStream in = con.getInputStream();
                    String encoding = "UTF-8";
                    String rawBittrex = IOUtils.toString(in, encoding);
                    arrayBittrex = Json.parse(rawBittrex).asObject().get("result").asArray();
                }
                catch(MalformedURLException e) {}
                catch(IOException e) {}
                
                System.out.println("BTC: " + getPrice("bittrex", "usdt-btc") + " USD");
                System.out.println("ETH: " + getPrice("bittrex", "btc-eth") + " BTC");
                System.out.println("LTC: " + getPrice("bittrex", "btc-ltc") + " BTC");
                System.out.println("ZEC: " + getPrice("bittrex", "btc-zec") + " BTC");
                System.out.println();
            }
        }, 0,5000);
    }
    
    public static float getPrice(String exchange, String market) {
        if(exchange.equals("bittrex")) {
            int numArrays = arrayBittrex.size();
            for(int i=0; i<numArrays; i++){
                if(market.equalsIgnoreCase(arrayBittrex.get(i).asObject().getString("MarketName", ""))) {
                    float last = arrayBittrex.get(i).asObject().getFloat("Last", 0);
                    return last;
                }
            }
        }
        return 0;
    } 
}
