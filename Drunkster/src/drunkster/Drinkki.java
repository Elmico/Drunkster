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
    private int aines1;
    private int aines2;
    private int aines3;
    private int aines4;
    private int aines5;
    private String kuvaus;
   
    Drinkki(int id, String nimi, int aines1, int aines2, int aines3, int aines4, int aines5, String kuvaus){
       this.id = id;
       this.nimi = nimi;
       this.aines1 = aines1;
       this.aines2 = aines2;
       this.aines3 = aines3;
       this.aines4 = aines4;
       this.aines5 = aines5;
       this.kuvaus = kuvaus;
    }
   
    public String getNimi(){
        return this.nimi;
    }
   
    public int getId(){
        return this.id;
    }
    
    public int getAines1(){
        return this.aines1;
    }
    
    public int getAines2(){
        return this.aines2;
    }
    
    public int getAines3(){
        return this.aines3;
    }
    
    public int getAines4(){
        return this.aines4;
    }
    
    public int getAines5(){
        return this.aines5;
    }
    
    public String getKuvaus(){
        return this.kuvaus;
    }
   
}