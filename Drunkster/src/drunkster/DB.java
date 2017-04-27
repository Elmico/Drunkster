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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
 
/**
 *
 * @author Aleksi
 */
public class DB {
   
    public static final String MYSQL_URL = "jdbc:mysql://mysql.labranet.jamk.fi";
    public static final String MYSQL_KAYTTAJA = "H8827";
    public static final String MYSQL_SALASANA = "ZVCt2kgTPNfvLdKH89T4RenbT0yA4VmH";
    public static final String MYSQL_AJURI = "com.mysql.jdbc.Driver";
       
    ResultSet rs = null;
    public Connection conn = null;
    List<Drinkki> kaikkiDrinkit = new ArrayList<Drinkki>();
    
         
    // privaatti konstruktori, ei voi kutsua ulkopuolelta
    DB() {
           /* try {
                Class.forName(MYSQL_AJURI);
            } catch (ClassNotFoundException e) {
                    virhe.setText(e.toString());
            }*/
    }
 
    private void luoYhteys(JLabel virhe) {
            conn = null;
            try {
                Class.forName(MYSQL_AJURI);
                conn = DriverManager.getConnection(MYSQL_URL, MYSQL_KAYTTAJA, MYSQL_SALASANA);
            } catch (Exception e) {
                virhe.setText(e.toString());
            }
    }
   
        public List<Aines> päivitäAinekset(JLabel virhe){
            luoYhteys(virhe);
           
            List<Aines> ainesList = new ArrayList<Aines>();
           
            if(conn == null){
                //virhe.setText("conn on null");
            }
           
            try {
                PreparedStatement pstmt = conn.prepareStatement("use H8827;");
                pstmt.executeQuery();
                pstmt = conn.prepareStatement("SELECT * FROM ainekset;");
                rs = pstmt.executeQuery();
               
                try {
                    int iteraattori = 0;  
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String nimi = rs.getString("nimi");                      
                       
                        ainesList.add(iteraattori, new Aines(id, nimi));
 
                        iteraattori++;
                    }
                    //db.conn.close();
                } catch (SQLException ex) {
                    virhe.setText(ex.toString());
                }
               
                //conn.close();
            } catch (SQLException ex) {
                virhe.setText(ex.toString());
            } 
            return ainesList;
        }
       
          public List<Drinkki> päivitäDrinkit(JLabel virhe, int selected, List<String> tyyppiLista){
            luoYhteys(virhe);
           
            List<Drinkki> drinkkiList = new ArrayList<Drinkki>();
           
            //virhe.setText("Testi");
            //virhe.setText("Selected = " + selected);
           
            try {
               
                PreparedStatement pstmt = conn.prepareStatement("use H8827;");
                pstmt.executeQuery();
               
                if(selected == 5){
                                   
                    pstmt = conn.prepareStatement("SELECT * FROM drinkit WHERE "
                                                + "(aines1 = ? OR aines1 = ? OR aines1 = ? OR aines1 = ? OR aines1 = ?) AND"
                                                + "(aines2 = ? OR aines2 = ? OR aines2 = ? OR aines2 = ? OR aines2 = ?) AND"
                                                + "(aines3 = ? OR aines3 = ? OR aines3 = ? OR aines3 = ? OR aines3 = ? OR aines3 is null) AND"
                                                + "(aines4 = ? OR aines4 = ? OR aines4 = ? OR aines4 = ? OR aines4 = ? OR aines4 is null) AND"
                                                + "(aines5 = ? OR aines5 = ? OR aines5 = ? OR aines5 = ? OR aines5 = ? OR aines5 is null);");
                   
                    //pstmt.setInt(1, tyyppiLista.get(0)); pstmt.setInt(6, tyyppiLista.get(0));  pstmt.setInt(11, tyyppiLista.get(0)); pstmt.setInt(16, tyyppiLista.get(0)); pstmt.setInt(21, tyyppiLista.get(0));
                    //pstmt.setInt(2, tyyppiLista.get(1)); pstmt.setInt(7, tyyppiLista.get(1));  pstmt.setInt(12, tyyppiLista.get(1)); pstmt.setInt(17, tyyppiLista.get(1)); pstmt.setInt(22, tyyppiLista.get(1));
                    //pstmt.setInt(3, tyyppiLista.get(2)); pstmt.setInt(8, tyyppiLista.get(2));  pstmt.setInt(13, tyyppiLista.get(2)); pstmt.setInt(18, tyyppiLista.get(2)); pstmt.setInt(23, tyyppiLista.get(2));
                    //pstmt.setInt(4, tyyppiLista.get(3)); pstmt.setInt(9, tyyppiLista.get(3));  pstmt.setInt(14, tyyppiLista.get(3)); pstmt.setInt(19, tyyppiLista.get(3)); pstmt.setInt(24, tyyppiLista.get(3));
                    //pstmt.setInt(5, tyyppiLista.get(4)); pstmt.setInt(10, tyyppiLista.get(4)); pstmt.setInt(15, tyyppiLista.get(4)); pstmt.setInt(20, tyyppiLista.get(4)); pstmt.setInt(25, tyyppiLista.get(4));
                   
                    for(int i = 1; i <= 25; i+=5){
                        pstmt.setString(i, tyyppiLista.get(0));
                        pstmt.setString(i+1, tyyppiLista.get(1));
                        pstmt.setString(i+2, tyyppiLista.get(2));
                        pstmt.setString(i+3, tyyppiLista.get(3));
                        pstmt.setString(i+4, tyyppiLista.get(4));
                    }
               
                } else if (selected == 4){
                                       
                    pstmt = conn.prepareStatement("SELECT * FROM drinkit WHERE "
                                                + "(aines1 = ? OR aines1 = ? OR aines1 = ? OR aines1 = ?) AND"
                                                + "(aines2 = ? OR aines2 = ? OR aines2 = ? OR aines2 = ?) AND"
                                                + "(aines3 = ? OR aines3 = ? OR aines3 = ? OR aines3 = ? OR aines3 is null) AND"
                                                + "(aines4 = ? OR aines4 = ? OR aines4 = ? OR aines4 = ? OR aines4 is null) AND"
                                                + "(aines5 = ? OR aines5 = ? OR aines5 = ? OR aines5 = ? OR aines5 is null);");
                   
                    //pstmt.setInt(1, tyyppiLista.get(0)); pstmt.setInt(5, tyyppiLista.get(0));  pstmt.setInt(9, tyyppiLista.get(0)); pstmt.setInt(13, tyyppiLista.get(0)); pstmt.setInt(17, tyyppiLista.get(0));
                    //pstmt.setInt(2, tyyppiLista.get(1)); pstmt.setInt(6, tyyppiLista.get(1));  pstmt.setInt(10, tyyppiLista.get(1)); pstmt.setInt(14, tyyppiLista.get(1)); pstmt.setInt(18, tyyppiLista.get(1));
                    //pstmt.setInt(3, tyyppiLista.get(2)); pstmt.setInt(7, tyyppiLista.get(2));  pstmt.setInt(11, tyyppiLista.get(2)); pstmt.setInt(15, tyyppiLista.get(2)); pstmt.setInt(19, tyyppiLista.get(2));
                    //pstmt.setInt(4, tyyppiLista.get(3)); pstmt.setInt(8, tyyppiLista.get(3));  pstmt.setInt(12, tyyppiLista.get(3)); pstmt.setInt(16, tyyppiLista.get(3)); pstmt.setInt(20, tyyppiLista.get(3));
                   
                    for(int i = 1; i <= 20; i+=4){
                        pstmt.setString(i, tyyppiLista.get(0));
                        pstmt.setString(i+1, tyyppiLista.get(1));
                        pstmt.setString(i+2, tyyppiLista.get(2));
                        pstmt.setString(i+3, tyyppiLista.get(3));
                    }
                   
                   
                } else if (selected == 3){                  
                   
                    pstmt = conn.prepareStatement("SELECT * FROM drinkit WHERE "
                                                + "(aines1 = ? OR aines1 = ? OR aines1 = ?) AND"
                                                + "(aines2 = ? OR aines2 = ? OR aines2 = ?) AND"
                                                + "(aines3 = ? OR aines3 = ? OR aines3 = ? OR aines3 is null) AND"
                                                + "(aines4 = ? OR aines4 = ? OR aines4 = ? OR aines4 is null) AND"
                                                + "(aines5 = ? OR aines5 = ? OR aines5 = ? OR aines5 is null);");
                   
                    //pstmt.setInt(1, tyyppiLista.get(0)); pstmt.setInt(4, tyyppiLista.get(0));  pstmt.setInt(7, tyyppiLista.get(0)); pstmt.setInt(10, tyyppiLista.get(0)); pstmt.setInt(13, tyyppiLista.get(0));
                    //pstmt.setInt(2, tyyppiLista.get(1)); pstmt.setInt(5, tyyppiLista.get(1));  pstmt.setInt(8, tyyppiLista.get(1)); pstmt.setInt(11, tyyppiLista.get(1)); pstmt.setInt(14, tyyppiLista.get(1));
                    //pstmt.setInt(3, tyyppiLista.get(2)); pstmt.setInt(6, tyyppiLista.get(2));  pstmt.setInt(9, tyyppiLista.get(2)); pstmt.setInt(12, tyyppiLista.get(2)); pstmt.setInt(15, tyyppiLista.get(2));
                   
                    for(int i = 1; i <= 15; i+=3){
                        pstmt.setString(i, tyyppiLista.get(0));
                        pstmt.setString(i+1, tyyppiLista.get(1));
                        pstmt.setString(i+2, tyyppiLista.get(2));
                    }
                   
                    //virhe.setText("Testi4");
                   
                } else if (selected == 2){
                   
                    pstmt = conn.prepareStatement("SELECT * FROM drinkit WHERE "
                                                + "(aines1 = ? OR aines1 = ?) AND"
                                                + "(aines2 = ? OR aines2 = ?) AND"
                                                + "(aines3 = ? OR aines3 = ? OR aines3 is null) AND"
                                                + "(aines4 = ? OR aines4 = ? OR aines4 is null) AND"
                                                + "(aines5 = ? OR aines5 = ? OR aines5 is null);");
                   
                    //pstmt.setInt(1, tyyppiLista.get(0)); pstmt.setInt(3, tyyppiLista.get(0));  pstmt.setInt(5, tyyppiLista.get(0)); pstmt.setInt(7, tyyppiLista.get(0)); pstmt.setInt(9, tyyppiLista.get(0));
                    //pstmt.setInt(2, tyyppiLista.get(1)); pstmt.setInt(4, tyyppiLista.get(1));  pstmt.setInt(6, tyyppiLista.get(1)); pstmt.setInt(8, tyyppiLista.get(1)); pstmt.setInt(10, tyyppiLista.get(1));
                   
                    for(int i = 1; i <= 10; i+=2){
                        pstmt.setString(i, tyyppiLista.get(0));
                        pstmt.setString(i+1, tyyppiLista.get(1));
                    }
                   
                }
 
                rs = pstmt.executeQuery();    
 
                int iteraattori = 0;
               
                //virhe.setText("Testi8");
 
                while (rs.next()) {
                    //virhe.setText("Testi9");
                    int id = rs.getInt("id");
                    String nimi = rs.getString("nimi");
                    String kuvaus = rs.getString("kuvaus");
 
                    //virhe.setText("Testi 10");
 
                    if(selected == 5){
                        drinkkiList.add(iteraattori, new Drinkki(id, nimi, tyyppiLista.get(0), tyyppiLista.get(1), tyyppiLista.get(2), tyyppiLista.get(3), tyyppiLista.get(4), kuvaus));
                    } else if (selected == 4){                        
                        drinkkiList.add(iteraattori, new Drinkki(id, nimi, tyyppiLista.get(0), tyyppiLista.get(1), tyyppiLista.get(2), tyyppiLista.get(3), kuvaus));
                    } else if (selected == 3){
                        drinkkiList.add(iteraattori, new Drinkki(id, nimi, tyyppiLista.get(0), tyyppiLista.get(1), tyyppiLista.get(2), kuvaus));
                    } else if (selected == 2){
                        drinkkiList.add(iteraattori, new Drinkki(id, nimi, tyyppiLista.get(0), tyyppiLista.get(1), kuvaus));
                    }
 
                    iteraattori++;
                }
                //virhe.setText("Testi11");
                //db.conn.close();
               
            } catch (Exception ex) {
                virhe.setText(ex.toString());
            }
           
            if(drinkkiList.isEmpty()){
                virhe.setText("Drinkkilista on tyhjä");
            }
           
            return drinkkiList;
        }
       
        public void tallennaUusiAines(JLabel virhe, String nimi){
            luoYhteys(virhe);
            try {
                //virhe.setText(nimi);
                PreparedStatement pstmt = conn.prepareStatement("use H8827;");
                pstmt.executeQuery();
                pstmt = conn.prepareStatement("INSERT INTO ainekset(nimi) VALUES (?);");
                pstmt.setString(1, nimi);
                pstmt.executeUpdate();
                conn.close();
            } catch (SQLException ex) {
                //virhe.setText(ex.toString());
            }
        }
       
        public void tallennaUusiDrinkki(JLabel virhe, String nimi, int aines1, int aines2, int aines3, int aines4, int aines5, String kuvaus){
            luoYhteys(virhe);
             try{
                PreparedStatement pstmt = conn.prepareStatement("use H8827;");
                pstmt.executeQuery();
                pstmt = conn.prepareStatement("INSERT INTO drinkit(nimi, aines1, aines2, aines3, aines4, aines5, kuvaus) VALUES (?,?,?,?,?,?,?);");
                pstmt.setString(1, nimi);
                pstmt.setInt(2, aines1);
                pstmt.setInt(3, aines2);
                pstmt.setInt(4, aines3);
                pstmt.setInt(5, aines4);
                pstmt.setInt(6, aines5);
                pstmt.setString(7, kuvaus);
                pstmt.executeUpdate();
                conn.close();
            } catch (SQLException ex) {
                //virhe.setText(ex.toString());
            }
           
        }
        
         public void poistaDrinkki(JLabel virhe, String nimi, int aines1, int aines2, int aines3, int aines4, int aines5, String kuvaus){
             luoYhteys(virhe);
             try{
                PreparedStatement pstmt = conn.prepareStatement("use H8827;");
                pstmt.executeQuery();
                pstmt = conn.prepareStatement("DELETE FROM drinkit WHERE nimi=?;");
                pstmt.setString(1, nimi);
                pstmt.executeUpdate();
               
            }
            catch(Exception ex){
                //virhe.setText(ex.toString());
            }
        }
       
        public void poistaAines(JLabel virhe, String nimi){
            luoYhteys(virhe);
            try{
                PreparedStatement pstmt = conn.prepareStatement("use H8827;");
                pstmt.executeQuery();
                pstmt = conn.prepareStatement("DELETE FROM ainekset WHERE nimi=?;");
                pstmt.setString(1, nimi);
                pstmt.executeUpdate();
               
            }
            catch(Exception ex){
                //virhe.setText(ex.toString());
            }
        }
        
        public List<Drinkki> haeKaikkiDrinkit(JLabel virhe){
            luoYhteys(virhe);
            List<Drinkki> kaikkiDrinkit = new ArrayList<Drinkki>();
            try{
                PreparedStatement pstmt = conn.prepareStatement("use H8827;");
                pstmt.executeQuery();
                pstmt = conn.prepareStatement("SELECT * FROM drinkit;");
                rs = pstmt.executeQuery();
               /* if (rs.next()){
                    virhe.setText("rs ei ole tyhjä");
                }*/
                while (rs.next()){
                    
                    kaikkiDrinkit.add(new Drinkki(rs.getInt("id"), rs.getString("nimi"),rs.getString("aines1"), rs.getString("aines2"), rs.getString("kuvaus")));
                } 
                if(kaikkiDrinkit.isEmpty()){
                    virhe.setText("kaikki drinkit on tyhjä");
                }
                this.kaikkiDrinkit = kaikkiDrinkit;
            }
            catch(Exception ex){
                virhe.setText(ex.toString());
            }
            return this.kaikkiDrinkit;
        }
        
}