package cryptowatch;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonValue;
import java.awt.List;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
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
                    String body = IOUtils.toString(in, encoding);
                    arrayBittrex = Json.parse(body).asObject().get("result").asArray();
                    //System.out.println(arrayBittrex);
                }
                catch(MalformedURLException e) {}
                catch(IOException e) {}
            }
        }, 0,5000);
    }
    
    //public static 
    
    public static float getPrice(String exchange, String market) {
        for (JsonValue item : arrayBittrex) {
            float last = item.asObject().getFloat("Last", 0);
            System.out.println(last);
            return last;
        }
        return 0;
    }
        
}
