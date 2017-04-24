/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drunkster;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author Aleksi
 */
public class DB {
    
	public static final String MYSQL_URL = "mysql.labranet.jamk.fi";
	public static final String MYSQL_KAYTTAJA = "H8827";
	public static final String MYSQL_SALASANA = "ZVCt2kgTPNfvLdKH89T4RenbT0yA4VmH";
	public static final String MYSQL_AJURI = "com.mysql.jdbc.Driver";
        
         ResultSet rs = null;

	// privaatti konstruktori, ei voi kutsua ulkopuolelta
	DB(JLabel virhe) {
           /* try {
                Class.forName(MYSQL_AJURI);
            } catch (ClassNotFoundException e) {
                    virhe.setText(e.toString());
            }*/
	}

	private Connection luoYhteys(JLabel virhe) {
		Connection yhteys = null;
		try {
                    Class.forName(MYSQL_AJURI);
                    yhteys = DriverManager.getConnection(MYSQL_URL, MYSQL_KAYTTAJA, MYSQL_SALASANA);
		} catch (Exception e) {
                    virhe.setText(e.toString()); 
		}
		return yhteys;
	}
    
        public ResultSet päivitäAinekset(JLabel virhe){
            Connection conn = luoYhteys(virhe);
            
            if(conn == null){
                //virhe.setText("conn on null"); 
            }
            
            /*try {
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM ainekset;");
                rs = pstmt.executeQuery();
                conn.close();
            } catch (SQLException ex) {
                virhe.setText(ex.toString()); 
                Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            } */
            return rs; 
        }
        
        public ResultSet päivitäDrinkit(JLabel virhe){
            Connection conn = luoYhteys(virhe);
            try {
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM drinkit;");
                rs = pstmt.executeQuery();
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            }
            return rs;
        }
        
        public void tallennaUusiAines(JLabel virhe, String nimi, String tyyppi){
             Connection conn = luoYhteys(virhe);
            try {
                PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ainekset(nimi, tyyppi) VALUES (?,?);");
                pstmt.setString(1, nimi);
                pstmt.setString(2, tyyppi);
                rs = pstmt.executeQuery();
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        public void tallennaUusiDrinkki(){
        }
        
        public void poistaAines(){
        }
        
        public void poistaDrinkki(){
        }
}
