import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.lang.*;
import java.util.*;

public class App{
    public static void main(String[] args)throws IOException {
        while(true){
            Stock stock = Stock.GetObject();  
            stock.scanStock();

            stock.show();

            if(stock.sauvgardObject())
                System.out.println("saved");
            else
                System.out.println("Not Saved");    
        
            try {
                Thread.sleep(2000);                 
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
}