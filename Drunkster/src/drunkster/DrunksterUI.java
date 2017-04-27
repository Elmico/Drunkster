/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drunkster;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;

/*
use H8827;
create table ainekset(
id INT(6) unsigned auto_increment primary key, 
nimi varchar(30) not null,
tyyppi varchar(30) not null);

create table drinkit(
id INT(6) unsigned auto_increment primary key, 
nimi varchar(30) not null,
aines1 varchar(30) not null,
aines2 varchar(30) not null,
aines3 varchar(30) null,
aines4 varchar(30) null,
aines5 varchar(30) null,
kuvaus varchar(500) not null);
*/
public class DrunksterUI extends javax.swing.JFrame {
 
    frameLisaaDrinkki drinkkiFrame = new frameLisaaDrinkki(this, this);
   
    DB db = new DB();
   
    DefaultListModel dlm1 = new DefaultListModel();                                                                                 //
    DefaultListModel dlm2 = new DefaultListModel();                                                                                 //
    DefaultListModel dlm3 = new DefaultListModel();
   
    public List<Aines> ainesList = new ArrayList<Aines>();
    public List<Drinkki> drinkkiList = new ArrayList<Drinkki>();
   
    public DrunksterUI() {
        
       
        initComponents();
         
        /*--------------------------rakennetaan oma selectionModel jList tyypille, joka tukee toggle valintaa.--------------------------*/
                                                                                                                                        //
        listMyBooze.setSelectionModel(new DefaultListSelectionModel() {                                                                 //
            private static final long serialVersionUID = 1L;                                                                            //
                                                                                                                                        //
            boolean gestureStarted = false;                                                                                             //
                                                                                                                                        //
            @Override                                                                                                                   //
            public void setSelectionInterval(int index0, int index1) {                                                                  //
                if(!gestureStarted){                                                                                                    //
                    if (isSelectedIndex(index0)) {                                                                                      //
                        super.removeSelectionInterval(index0, index1);                                                                  //
                    } else {                                                                                                            //
                        super.addSelectionInterval(index0, index1);                                                                     //
                    }                                                                                                                   //
                }                                                                                                                       //
                gestureStarted = true;                                                                                                  //
            }                                                                                                                           //
                                                                                                                                        //
            @Override                                                                                                                   //
            public void setValueIsAdjusting(boolean isAdjusting) {                                                                      //
                if (isAdjusting == false) {                                                                                             //
                    gestureStarted = false;                                                                                             //
                }                                                                                                                       //
            }                                                                                                                           //
        });                                                                                                                             //
        /*-------------------------Asetetaan default modelit ja kuuntelija valinnan muutoksen seuraamiseksi-----------------------------*/
                                                                                                                                        //
                                                                                                                                        //
                                                                                                                                        //
        listMyBooze.setModel(dlm1);                                                                                                     //
        selectedBoozes.setModel(dlm2);                                                                                                  //
        selectedDrinks.setModel(dlm3);                                                                                                  //
                                                                                                                                        //
        ListSelectionModel listSelectionModelAines = listMyBooze.getSelectionModel();                                                        //  
        listSelectionModelAines.addListSelectionListener(new ListSelectionHandler(virhe, listMyBooze, dlm2));
        
        ListSelectionModel listSelectionModelDrinkki = selectedDrinks.getSelectionModel();
        listSelectionModelDrinkki.addListSelectionListener(new ListSelectionHandler(virhe, drinkkiList){
                
            @Override
            public void valueChanged(ListSelectionEvent e) {
                
                if(selectedDrinks.getSelectedValue() != null){
                    String selection = selectedDrinks.getSelectedValue().toString();
                                
                    for(Drinkki drinkki : drinkkiList){
                        if(selection.equals(drinkki.getNimi())){
                            textAreaKuvaus.setText(drinkki.getKuvaus());
                        }
                    }
                }
            }
        });                           //
                                                                                                                                        //
        //List mylist = listMyBooze.getSelectedValuesList();                                                                            //
                                                                                                                                        //
        /*------------------------------------Täytetään JLIST testidatalla tiedostosta--------------------------------------------------*/
                                                                                                                                        //
        /*Path path = Paths.get("testi.txt");                                                                                           //
        try{                                                                                                                            //  
            mylist = Files.readAllLines(path, StandardCharsets.UTF_8);                                                                  //
        } catch (Exception e){                                                                                                          //
            virhe.setText(e.toString());                                                                                                //
        }                                                                                                                               //
                                                                                                                                        //
        if(mylist.isEmpty()){                                                                                                           //
            virhe.setText("mylist on tyhjä!");                                                                                          //
        }                                                                                                                               //
        for(int i = 0; i < mylist.size(); i++){                                                                                         //
            dlm1.addElement(mylist.get(i));                                                                                             //
        }*/                                                                                                                             //
        /*------------------------------------------------------------------------------------------------------------------------------*/
       
        labelAines1.setText("");
        labelAines2.setText("");
        labelAines3.setText("");
        labelAines4.setText("");
        labelAines5.setText("");
       
        //DB db = new DB(virhe);
       päivitäAineksetLista();
       drinkkiFrame.setAinesList(this.ainesList);
       try{
       db.conn.close();
       }
       catch(Exception e){}
       
       if(db.haeKaikkiDrinkit(virhe).isEmpty()){
          // virhe.setText("tyhjä saatana");
       }
       drinkkiFrame.setDrinkkiList(db.haeKaikkiDrinkit(virhe));
       
       
      //  ResultSet rs2 = db.päivitäDrinkit(virhe);
   
      /*  try {
            while (rs2.next()) {
                String nimi = rs2.getString("nimi");      
                dlm3.addElement(nimi);
            }
            db.conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DrunksterUI.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
   
    public void päivitäAineksetLista(){
        dlm1.clear();
        ainesList.clear();
        ainesList = db.päivitäAinekset(virhe);
       
        try {
            for (Aines aines : ainesList) {
                //int id = aines.getId();
                String nimi = aines.getNimi();      
                dlm1.addElement(nimi);
                
            }
//            db.conn.close();
        } catch (Exception ex) {
            virhe.setText(ex.toString());
        }
 
    }
        
    public void valueChanged(ListSelectionEvent e) {
        //ListSelectionModel lsm = (ListSelectionModel)e.getSource();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnAddBooze = new javax.swing.JButton();
        buttonHaeDrinkit = new javax.swing.JButton();
        txtfieldAddBooze = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listMyBooze = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        selectedBoozes = new javax.swing.JList<>();
        virhe = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnRemoveBooze = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        selectedDrinks = new javax.swing.JList<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButtonMuokkaaDrinkkeja = new javax.swing.JButton();
        labelAines1 = new javax.swing.JLabel();
        labelAines2 = new javax.swing.JLabel();
        labelAines3 = new javax.swing.JLabel();
        labelAines4 = new javax.swing.JLabel();
        labelAines5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        textAreaKuvaus = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Welcome to Drunkster!");

        btnAddBooze.setText("Lisää aines");
        btnAddBooze.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddBoozeActionPerformed(evt);
            }
        });

        buttonHaeDrinkit.setText("Hae drinkit");
        buttonHaeDrinkit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHaeDrinkitActionPerformed(evt);
            }
        });

        jLabel2.setText("Lisää aines");

        jScrollPane1.setViewportView(listMyBooze);

        jScrollPane2.setViewportView(selectedBoozes);

        virhe.setText("Debug");

        jLabel3.setText("Ainekset");

        jLabel4.setText("Kaapista löytyvät");

        btnRemoveBooze.setText("Poista aines");
        btnRemoveBooze.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveBoozeActionPerformed(evt);
            }
        });

        jScrollPane3.setViewportView(selectedDrinks);

        jLabel5.setText("Mahdolliset drinkit");

        jLabel6.setText("Kuvaus:");

        jButtonMuokkaaDrinkkeja.setText("Muokkaa drinkkejä");
        jButtonMuokkaaDrinkkeja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMuokkaaDrinkkejaActionPerformed(evt);
            }
        });

        labelAines1.setText("jLabel9");

        labelAines2.setText("jLabel9");

        labelAines3.setText("jLabel9");

        labelAines4.setText("jLabel9");

        labelAines5.setText("jLabel9");

        textAreaKuvaus.setBackground(new java.awt.Color(240, 240, 240));
        textAreaKuvaus.setColumns(20);
        textAreaKuvaus.setLineWrap(true);
        textAreaKuvaus.setRows(5);
        textAreaKuvaus.setText("Valitse listasta ainekset, joita sinulla on käsillä. Lisää listaan juomia tarvittaessa.");
        textAreaKuvaus.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane4.setViewportView(textAreaKuvaus);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel7))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(labelAines1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(labelAines2))
                                    .addComponent(jLabel6)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(28, 28, 28)
                                        .addComponent(jLabel5))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2)
                                    .addComponent(txtfieldAddBooze)
                                    .addComponent(btnAddBooze, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnRemoveBooze, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                                    .addComponent(buttonHaeDrinkit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButtonMuokkaaDrinkkeja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelAines3)
                                .addGap(18, 18, 18)
                                .addComponent(labelAines4)
                                .addGap(26, 26, 26)
                                .addComponent(labelAines5)))))
                .addContainerGap(48, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(virhe)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtfieldAddBooze, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddBooze)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemoveBooze)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonHaeDrinkit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonMuokkaaDrinkkeja))
                    .addComponent(jScrollPane3))
                .addGap(59, 59, 59)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelAines1)
                    .addComponent(labelAines2)
                    .addComponent(labelAines3)
                    .addComponent(labelAines4)
                    .addComponent(labelAines5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(virhe)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonMuokkaaDrinkkejaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMuokkaaDrinkkejaActionPerformed
        this.setVisible(false);
        drinkkiFrame.setVisible(true);
    }//GEN-LAST:event_jButtonMuokkaaDrinkkejaActionPerformed

    private void btnAddBoozeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddBoozeActionPerformed
        String nimi = txtfieldAddBooze.getText();       
        int lennimi = nimi.length();  
        
            if (lennimi>0){  
                try{
                    db.tallennaUusiAines(virhe,nimi);
                    db.conn.close();
                    db.päivitäAinekset(virhe);
                    päivitäAineksetLista();
                    db.conn.close();
                }
                catch(Exception e){
                    virhe.setText(e.toString());
                }    
      }
    }//GEN-LAST:event_btnAddBoozeActionPerformed

    private void btnRemoveBoozeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveBoozeActionPerformed
       String nimi = listMyBooze.getSelectedValue();
       try{
           virhe.setText(nimi);
           db.poistaAines(virhe, nimi);
           db.conn.close();
           db.päivitäAinekset(virhe);
           päivitäAineksetLista();
           db.conn.close();
       }
       catch(Exception e){
           virhe.setText(e.toString());
       }
    }//GEN-LAST:event_btnRemoveBoozeActionPerformed

    private void buttonHaeDrinkitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHaeDrinkitActionPerformed
        
       dlm3.clear();
       
        int size = selectedBoozes.getModel().getSize();
        List<String> listName = new ArrayList<String>();
        List<Integer> listId = new ArrayList<Integer>();
       
        if(size > 1 && size < 6){
           
            for(int i = 0; i < size; i++){
                listName.add(selectedBoozes.getModel().getElementAt(i));
            }
           
            for(int j = 0; j < size; j++){                                      //haetaan listassa olevien ainesten nimien perusteella niitä vastaavat id:t.
                for(Aines aines : ainesList){                                   //Alkuperäisessä tietokantatoteutuksessa drinkeillä oli ainekset niiden omilla id-arvoilla.
                    if(aines.getNimi() == listName.get(j)){                     //Nykymallissa käytetään Stringejä. Ongelmana oli, että kun drinkki lisätään kantaan käyttäen
                        listId.add(aines.getId());                              //olemassa olevien ainesten id-arvoja, jos aines poistettiin ja myöhemmin lisättiin uudelleen
                    }                                                           //drinkkiä ei koskaan löytynyt enää ko. aineksilla, koska poistetun ja uudelleen lisätyn aineksen
                }                                                               //id oli eri.
            }
           
            drinkkiList = db.päivitäDrinkit(virhe,size,listName);
           
            try {
                for (Drinkki drinkki : drinkkiList) {
                    //int id = aines.getId();
                    String nimi = drinkki.getNimi();      
                    dlm3.addElement(nimi);
                }
            } catch (Exception ex) {
               virhe.setText(ex.toString());
            }
           
        } else {
            virhe.setText("Liian vähän tai liian monta ainesta valittu.");
        }
        
    }//GEN-LAST:event_buttonHaeDrinkitActionPerformed

    public List<Aines> getAineslist(){
        return ainesList;
    }
    /**
     * @param args the command line arguments
     */
 


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddBooze;
    private javax.swing.JButton btnRemoveBooze;
    private javax.swing.JButton buttonHaeDrinkit;
    private javax.swing.JButton jButtonMuokkaaDrinkkeja;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel labelAines1;
    private javax.swing.JLabel labelAines2;
    private javax.swing.JLabel labelAines3;
    private javax.swing.JLabel labelAines4;
    private javax.swing.JLabel labelAines5;
    private javax.swing.JList<String> listMyBooze;
    private javax.swing.JList<String> selectedBoozes;
    private javax.swing.JList<String> selectedDrinks;
    private javax.swing.JTextArea textAreaKuvaus;
    private javax.swing.JTextField txtfieldAddBooze;
    private javax.swing.JLabel virhe;
    // End of variables declaration//GEN-END:variables
}
