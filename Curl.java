/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kinho
 */
public class Curl {
    
    private String url;
    private String method;
    private String param;
    private Proxy proxy;
    private int responseCod;
    

    
    public Curl(String url){
        this.url = url;
    }
    
    public String execute(){
        
        StringBuilder response = new StringBuilder();
        HttpURLConnection httpURLConnection = null;
        
        try {
            URL url = new URL(this.url);
            
            httpURLConnection = (HttpURLConnection) url.openConnection();
            if(this.proxy != null)
                httpURLConnection = (HttpURLConnection) url.openConnection(this.proxy);
            
            if(this.method != null)
                httpURLConnection.setRequestMethod(this.method);
            
            if(this.param != null && !this.method.equals("GET")){
                httpURLConnection.setDoOutput(true);
                DataOutputStream dos = new DataOutputStream(httpURLConnection.getOutputStream());
                dos.writeBytes(this.param);
                dos.flush();
                dos.close();
            }
            
            httpURLConnection.connect();
            // GET COD
            this.responseCod = httpURLConnection.getResponseCode();
            
            BufferedReader getResponse = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String lineRead;
            
            while((lineRead = getResponse.readLine()) != null){
                response.append(lineRead);
            }
            
            getResponse.close();
            
            
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(Curl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Curl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
            // close
            httpURLConnection.disconnect();
        }
        
        // output
        //System.out.println(response);
        return response.toString();
    }
    
    
    /***
     * Method setMethod
     * @param method 
     */
    public void setMethod(String method){
        this.method = method;
    }
    
    /***
     * Method setParam
     * @param param 
     */
    public void setParam(String param) {
        this.param = param;
    }
    
    /***
     * 
     * @return 
     */
    public int getResponseCod(){
        return this.responseCod;
    }
    
    public void setProxy(String proxy, int porta) {
        this.proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxy, porta));
    }
}
