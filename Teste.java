/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

/**
 *
 * @author kinho
 */
public class Teste {
    
    public static void main(String[] args){  
        
        Curl curl = new Curl(""); // Url Web Service
        curl.setMethod(""); // Method POST, GET, PUT ...
        curl.setParam(""); // Parametro POST query ex: user=henrique&senha=12565
       
        //response requisição
        System.out.println(curl.execute());
    }
    
    
}
