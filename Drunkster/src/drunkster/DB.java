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
                    Logger.getLogger(DrunksterUI.class.getName()).log(Level.SEVERE, null, ex);
                }
               
                //conn.close();
            } catch (SQLException ex) {
                virhe.setText(ex.toString());
                Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            } 
            return ainesList;
        }
       
          public List<Drinkki> päivitäDrinkit(JLabel virhe, int selected, List<Integer> IDlista){
            luoYhteys(virhe);
            
            List<Drinkki> drinkkiList = new ArrayList<Drinkki>();
            
            //virhe.setText("Testi");
             virhe.setText("Selected = " + selected);
            
            try {
                
                PreparedStatement pstmt = conn.prepareStatement("use H8827;");
                pstmt.executeQuery();
                
                if(selected == 5){
                    
                    for(int myint : IDlista){
                         virhe.setText(Integer.toString(myint));
                    }
                    
                    pstmt = conn.prepareStatement("SELECT nimi FROM drinkit WHERE "
                                                + "(aines1 = ? OR aines1 = ? OR aines1 = ? OR aines1 = ? OR aines1 = ?) AND"
                                                + "(aines2 = ? OR aines2 = ? OR aines2 = ? OR aines2 = ? OR aines2 = ?) AND"
                                                + "(aines3 = ? OR aines3 = ? OR aines3 = ? OR aines3 = ? OR aines3 = ? OR aines3 is null) AND"
                                                + "(aines4 = ? OR aines4 = ? OR aines4 = ? OR aines4 = ? OR aines4 = ? OR aines4 is null) AND"
                                                + "(aines5 = ? OR aines5 = ? OR aines5 = ? OR aines5 = ? OR aines5 = ? OR aines5 is null);");
                    
                    pstmt.setInt(1, IDlista.get(0)); pstmt.setInt(6, IDlista.get(0));  pstmt.setInt(11, IDlista.get(0)); pstmt.setInt(16, IDlista.get(0)); pstmt.setInt(21, IDlista.get(0));
                    pstmt.setInt(2, IDlista.get(1)); pstmt.setInt(7, IDlista.get(1));  pstmt.setInt(12, IDlista.get(1)); pstmt.setInt(17, IDlista.get(1)); pstmt.setInt(22, IDlista.get(1));
                    pstmt.setInt(3, IDlista.get(2)); pstmt.setInt(8, IDlista.get(2));  pstmt.setInt(13, IDlista.get(2)); pstmt.setInt(18, IDlista.get(2)); pstmt.setInt(23, IDlista.get(2));
                    pstmt.setInt(4, IDlista.get(3)); pstmt.setInt(9, IDlista.get(3));  pstmt.setInt(14, IDlista.get(3)); pstmt.setInt(19, IDlista.get(3)); pstmt.setInt(24, IDlista.get(3));
                    pstmt.setInt(5, IDlista.get(4)); pstmt.setInt(10, IDlista.get(4)); pstmt.setInt(15, IDlista.get(4)); pstmt.setInt(20, IDlista.get(4)); pstmt.setInt(25, IDlista.get(4));
                
                } else if (selected == 4){
                    
                    for(int myint : IDlista){
                         virhe.setText(Integer.toString(myint));
                    }
                    
                    pstmt = conn.prepareStatement("SELECT nimi FROM drinkit WHERE "
                                                + "(aines1 = ? OR aines1 = ? OR aines1 = ? OR aines1 = ?) AND"
                                                + "(aines2 = ? OR aines2 = ? OR aines2 = ? OR aines2 = ?) AND"
                                                + "(aines3 = ? OR aines3 = ? OR aines3 = ? OR aines3 = ? OR aines3 is null) AND"
                                                + "(aines4 = ? OR aines4 = ? OR aines4 = ? OR aines4 = ? OR aines4 is null) AND"
                                                + "(aines5 = ? OR aines5 = ? OR aines5 = ? OR aines5 = ? OR aines5 is null);");
                    
                    pstmt.setInt(1, IDlista.get(0)); pstmt.setInt(5, IDlista.get(0));  pstmt.setInt(9, IDlista.get(0)); pstmt.setInt(13, IDlista.get(0)); pstmt.setInt(17, IDlista.get(0));
                    pstmt.setInt(2, IDlista.get(1)); pstmt.setInt(6, IDlista.get(1));  pstmt.setInt(10, IDlista.get(1)); pstmt.setInt(14, IDlista.get(1)); pstmt.setInt(18, IDlista.get(1));
                    pstmt.setInt(3, IDlista.get(2)); pstmt.setInt(7, IDlista.get(2));  pstmt.setInt(11, IDlista.get(2)); pstmt.setInt(15, IDlista.get(2)); pstmt.setInt(19, IDlista.get(2));
                    pstmt.setInt(4, IDlista.get(3)); pstmt.setInt(8, IDlista.get(3));  pstmt.setInt(12, IDlista.get(3)); pstmt.setInt(16, IDlista.get(3)); pstmt.setInt(20, IDlista.get(3));
                    
                    
                } else if (selected == 3){
                    
                     virhe.setText("Testi3");
                     
                    for(int myint : IDlista){
                         virhe.setText(Integer.toString(myint));
                    }
                    
                    pstmt = conn.prepareStatement("SELECT nimi FROM drinkit WHERE "
                                                + "(aines1 = ? OR aines1 = ? OR aines1 = ?) AND"
                                                + "(aines2 = ? OR aines2 = ? OR aines2 = ?) AND"
                                                + "(aines3 = ? OR aines3 = ? OR aines3 = ? OR aines3 is null) AND"
                                                + "(aines4 = ? OR aines4 = ? OR aines4 = ? OR aines4 is null) AND"
                                                + "(aines5 = ? OR aines5 = ? OR aines5 = ? OR aines5 is null);");
                    
                    pstmt.setInt(1, IDlista.get(0)); pstmt.setInt(4, IDlista.get(0));  pstmt.setInt(7, IDlista.get(0)); pstmt.setInt(10, IDlista.get(0)); pstmt.setInt(13, IDlista.get(0));
                    pstmt.setInt(2, IDlista.get(1)); pstmt.setInt(5, IDlista.get(1));  pstmt.setInt(8, IDlista.get(1)); pstmt.setInt(11, IDlista.get(1)); pstmt.setInt(14, IDlista.get(1));
                    pstmt.setInt(3, IDlista.get(2)); pstmt.setInt(6, IDlista.get(2));  pstmt.setInt(9, IDlista.get(2)); pstmt.setInt(12, IDlista.get(2)); pstmt.setInt(15, IDlista.get(2));
                    
                    virhe.setText("Testi4");
                    
                } else if (selected == 2){
                    
                    for(int myint : IDlista){
                         virhe.setText(Integer.toString(myint));
                    }
                    
                    pstmt = conn.prepareStatement("SELECT nimi FROM drinkit WHERE "
                                                + "(aines1 = ? OR aines1 = ?) AND"
                                                + "(aines2 = ? OR aines2 = ?) AND"
                                                + "(aines3 = ? OR aines3 = ? OR aines3 is null) AND"
                                                + "(aines4 = ? OR aines4 = ? OR aines4 is null) AND"
                                                + "(aines5 = ? OR aines5 = ? OR aines5 is null);");
                    
                    pstmt.setInt(1, IDlista.get(0)); pstmt.setInt(3, IDlista.get(0));  pstmt.setInt(5, IDlista.get(0)); pstmt.setInt(7, IDlista.get(0)); pstmt.setInt(9, IDlista.get(0));
                    pstmt.setInt(2, IDlista.get(1)); pstmt.setInt(4, IDlista.get(1));  pstmt.setInt(6, IDlista.get(1)); pstmt.setInt(8, IDlista.get(1)); pstmt.setInt(10, IDlista.get(1));
                    
                }
                virhe.setText("Testi5");
                rs = pstmt.executeQuery();
                virhe.setText("Testi6");
                
                //if (rs.next() ) {
                 //   virhe.setText("rs ei ole tyhjä");
                 //                  if (rs.next() ) {
                 //   virhe.setText("rs ei ole tyhjä edelleenkään");
                //} 
                //}       

 

                int iteraattori = 0; 
                
                //virhe.setText("Testi8");

                while (rs.next()) {
                    virhe.setText("Testi9");
                    int id = rs.getInt("id");
                    String nimi = rs.getString("nimi");
                    String kuvaus = rs.getString("kuvaus");

                    virhe.setText("Testi 10");

                    if(selected == 5){
                        drinkkiList.add(iteraattori, new Drinkki(id, nimi, IDlista.get(0), IDlista.get(1), IDlista.get(2), IDlista.get(3), IDlista.get(4), kuvaus));
                    } else if (selected == 4){                        
                        drinkkiList.add(iteraattori, new Drinkki(id, nimi, IDlista.get(0), IDlista.get(1), IDlista.get(2), IDlista.get(3), kuvaus));
                    } else if (selected == 3){
                        drinkkiList.add(iteraattori, new Drinkki(id, nimi, IDlista.get(0), IDlista.get(1), IDlista.get(2), kuvaus));
                    } else if (selected == 2){
                        drinkkiList.add(iteraattori, new Drinkki(id, nimi, IDlista.get(0), IDlista.get(1), kuvaus));
                    }

                    iteraattori++;
                }
                //virhe.setText("Testi11");
                //db.conn.close();
               
            } catch (Exception ex) {
                virhe.setText(ex.toString());
            }
                //virhe.setText("Palautetaan drinkkiLista");
                
                if(drinkkiList.isEmpty()){
                    //virhe.setText("Drinkkilista on tyhjä");
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
                virhe.setText(ex.toString());
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
                virhe.setText(ex.toString());
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
                virhe.setText(ex.toString());
            }
        }
       
        
}