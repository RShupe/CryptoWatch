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
import org.apache.commons.io.IOUtils;

public class Market {
    
    public static float getPrice(String exchange, String market) {
        String url = "";
        if(Objects.equals(exchange, "bittrex")){
            url = ("https://bittrex.com/api/v1.1/public/getmarketsummary?market=" + market);
        }
        
        /* This code will be moved out and will refer to a different function when a timer is working.
           The above URL in that timer-based JSON-getting will have most info that we need for this class (and the program)
           i.e. https://bittrex.com/api/v1.1/public/getmarketsummaries */
        try {
            URL url2 = new URL(url);
            URLConnection con = url2.openConnection();
            InputStream in = con.getInputStream();
            String encoding = con.getContentEncoding();  // ** WRONG: should use "con.getContentType()" instead but it returns something like "text/html; charset=UTF-8" so this value must be parsed to extract the actual encoding
            encoding = encoding == null ? "UTF-8" : encoding;
            String body = IOUtils.toString(in, encoding);
            JsonArray items = Json.parse(body).asObject().get("result").asArray();
            
            // I'm not sure a for loop is the best for a single value but maybe it's the only way to read arrays?
            for (JsonValue item : items) {
                float last = item.asObject().getFloat("Last", 0);
                return last;
            }
        }
        catch(MalformedURLException e) {}
        catch(IOException e) {}
        
        return 0;
    }
}
