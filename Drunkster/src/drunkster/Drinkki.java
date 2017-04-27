/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drunkster;
 
/**
 *
 * @author H8951
 */
public class Drinkki {
   
    private int id;
    private String nimi;
    private String aines1;
    private String aines2;
    private String aines3;
    private String aines4;
    private String aines5;
    private String kuvaus;
   
    Drinkki(int id, String nimi, String aines1, String aines2, String aines3, String aines4, String aines5, String kuvaus){
       this.id = id;
       this.nimi = nimi;
       this.aines1 = aines1;
       this.aines2 = aines2;
       this.aines3 = aines3;
       this.aines4 = aines4;
       this.aines5 = aines5;
       this.kuvaus = kuvaus;
    }
   
    Drinkki(int id, String nimi, String aines1, String aines2, String aines3, String aines4, String kuvaus){
       this.id = id;
       this.nimi = nimi;
       this.aines1 = aines1;
       this.aines2 = aines2;
       this.aines3 = aines3;
       this.aines4 = aines4;
       this.kuvaus = kuvaus;
    }
       
    Drinkki(int id, String nimi, String aines1, String aines2, String aines3, String kuvaus){
       this.id = id;
       this.nimi = nimi;
       this.aines1 = aines1;
       this.aines2 = aines2;
       this.aines3 = aines3;
       this.kuvaus = kuvaus;
    }
           
    Drinkki(int id, String nimi, String aines1, String aines2, String kuvaus){
       this.id = id;
       this.nimi = nimi;
       this.aines1 = aines1;
       this.aines2 = aines2;
       this.kuvaus = kuvaus;
    }
               
    public String getNimi(){
        return this.nimi;
    }
   
    public int getId(){
        return this.id;
    }
   
    public String getAines1(){
        return this.aines1;
    }
   
    public String getAines2(){
        return this.aines2;
    }
   
    public String getAines3(){
        return this.aines3;
    }
   
    public String getAines4(){
        return this.aines4;
    }
   
    public String getAines5(){
        return this.aines5;
    }
   
    public String getKuvaus(){
        return this.kuvaus;
    }
   
}