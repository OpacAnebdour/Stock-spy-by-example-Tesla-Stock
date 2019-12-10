import java.io.IOException;
import java.io.BufferedReader;
import java.util.Vector;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.lang.*;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Stock implements Serializable{
    private Vector collection;
    private URL url;
    public Stock(URL url){
        this.url = url;
        this.collection = new Vector<String>(5);
    }

    public void scanStock()throws IOException{

        String result = "";
        int index = 0;
        URLConnection urlconn = this.url.openConnection();

        InputStreamReader stream = new InputStreamReader(urlconn.getInputStream());
        BufferedReader buff = new BufferedReader(stream);
        
        String test = "<span class=\"Trsdu(0.3s) Trsdu(0.3s) Fw(b) Fz(36px) Mb(-4px) D(b)\" data-reactid=\"14\">";

        String line = buff.readLine();
        
        while(line != null){
            if(line.contains(test)){
                index = line.indexOf(test) + test.length();
            //    result = line.substring(index, index + 10);
               
                for (int i = 0; i <= line.substring(index, index + 10).length(); i++) {
                    if (line.substring(index, index + 10).charAt(i) != '<'){
                        result = result + line.substring(index, index + 10).charAt(i);
                    }    
                    else
                        break;
                }
            }
            line = buff.readLine();
        }
        this.collection.add(result);
    }

    public void show(){
        System.out.println(this.collection);
    }
    
    public static Stock GetObject(){
        
        Stock stocks = null;
        ObjectInputStream ois = null;
        
        try {
            final FileInputStream fichier = new FileInputStream("dataTestla.ser");
            ois = new ObjectInputStream(fichier);
            stocks = (Stock) ois.readObject();
        
        } catch (final java.io.IOException e) {
            System.out.println("\nNULL\n");
            e.printStackTrace();
        }catch (final ClassNotFoundException e) {
            e.printStackTrace();     
        }finally {
            try {
              if (ois != null) {
                ois.close();
              }
            } catch (final IOException ex) {
              ex.printStackTrace();
            }
        }
        return stocks;
    }
    
    public boolean sauvgardObject(){
        boolean done = true;
        ObjectOutputStream oos = null;

        try {
            final FileOutputStream fichier = new FileOutputStream("dataTestla.ser");
            oos = new ObjectOutputStream(fichier);
            oos.writeObject(this);
            oos.flush();
          } catch (final java.io.IOException e) {
              done = false;
            e.printStackTrace();
          } finally {
            try {
              if (oos != null) {
                oos.flush();
                oos.close();
              }
            } catch (final IOException ex) {
              ex.printStackTrace();
            }
          }

          return done;
    }
}