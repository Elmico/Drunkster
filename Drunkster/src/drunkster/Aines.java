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
public class Aines {
   
    private int id;
    private String nimi;
   
    Aines(int id, String nimi){
       this.id = id;
       this.nimi = nimi;
    }
   
    public String getNimi(){
        return this.nimi;
    }
   
    public int getId(){
        return this.id;
    }
   
}