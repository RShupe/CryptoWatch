package cryptowatch;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.MalformedURLException;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.eclipsesource.json.*;
import org.apache.commons.io.IOUtils;

public class Main {
    
    public static boolean isUnix() {
        String OS = System.getProperty("os.name").toLowerCase();
        return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
    }
    
    public static void main(String args[]) {
        try {
            if (isUnix()) {
                System.out.println("This is Unix or Linux");
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            } else {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
        }
        catch (UnsupportedLookAndFeelException e) {}
        catch (ClassNotFoundException e) {}
        catch (InstantiationException e) {}
        catch (IllegalAccessException e) {}
        
        try {
            FileWriter fw = new FileWriter("selected_currencies.txt");
            PrintWriter pw = new PrintWriter(fw);
            
            pw.println("testing");
            
            pw.close();
        }
        catch (IOException e){
            System.out.println("ERROR CREATING FILE");
        }
        
        System.out.println("\nGet LTC stats test:");
        try {
            URL url = new URL("https://bittrex.com/api/v1.1/public/getmarketsummary?market=btc-ltc");
            URLConnection con = url.openConnection();
            InputStream in = con.getInputStream();
            String encoding = con.getContentEncoding();  // ** WRONG: should use "con.getContentType()" instead but it returns something like "text/html; charset=UTF-8" so this value must be parsed to extract the actual encoding
            encoding = encoding == null ? "UTF-8" : encoding;
            String body = IOUtils.toString(in, encoding);
            //System.out.println("Raw JSON: " + body + "\n");
            JsonArray items = Json.parse(body).asObject().get("result").asArray();
            for (JsonValue item : items) {
                String market = item.asObject().getString("MarketName", "Unknown Item");
                float volume = item.asObject().getFloat("Volume", 0);
                float last = item.asObject().getFloat("Last", 0);
                System.out.println("Market: " + market);
                System.out.println("Volume: " + volume + " BTC");
                System.out.println("Last Price: " + last + " BTC");
            }
        }
        catch(MalformedURLException e) {}
        catch(IOException e) {}
     
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainGui().setVisible(true);
            }
        });
    }
}
