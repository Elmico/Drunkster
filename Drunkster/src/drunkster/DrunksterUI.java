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
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JFrame;
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
aines5 varchar(30) null);
*/
public class DrunksterUI extends javax.swing.JFrame {

    JFrame drinkkiFrame = new frameLisaaDrinkki(this);
    
    public DrunksterUI() {
        
        initComponents();
         
        listMyBooze.setSelectionModel(new DefaultListSelectionModel() { // rakennetaan oma selectionModel jList tyypille, joka tukee toggle valintaa.
            private static final long serialVersionUID = 1L;

            boolean gestureStarted = false;

            @Override
            public void setSelectionInterval(int index0, int index1) {
                if(!gestureStarted){
                    if (isSelectedIndex(index0)) {
                        super.removeSelectionInterval(index0, index1);
                    } else {
                        super.addSelectionInterval(index0, index1);
                    }
                }
                gestureStarted = true;
            }

            @Override
            public void setValueIsAdjusting(boolean isAdjusting) {
                if (isAdjusting == false) {
                    gestureStarted = false;
                }
            }
        });        
        
        DefaultListModel dlm1 = new DefaultListModel();
        DefaultListModel dlm2 = new DefaultListModel();
        
        listMyBooze.setModel(dlm1);
        selectedBoozes.setModel(dlm2);
        
        ListSelectionModel listSelectionModel = listMyBooze.getSelectionModel();
        listSelectionModel.addListSelectionListener(
            new ListSelectionHandler(virhe, listMyBooze, dlm2));
          
        List mylist = listMyBooze.getSelectedValuesList();
        List mylist2;
        
        Path path = Paths.get("testi.txt");
        try{
            mylist = Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (Exception e){
            virhe.setText(e.toString());
        }
        
        if(mylist.isEmpty()){
            virhe.setText("mylist on tyhjä!"); 
        }
        for(int i = 0; i < mylist.size(); i++){
            dlm1.addElement(mylist.get(i));
        }           
    }

    public void valueChanged(ListSelectionEvent e) {
        ListSelectionModel lsm = (ListSelectionModel)e.getSource();
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
        jButton4 = new javax.swing.JButton();
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
        selectedBoozes1 = new javax.swing.JList<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButtonMuokkaaDrinkkeja = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Welcome to Drunkster!");

        btnAddBooze.setText("Lisää alkoholi");

        jButton4.setText("Hae drinkit");

        jLabel2.setText("Lisää aines");

        jScrollPane1.setViewportView(listMyBooze);

        jScrollPane2.setViewportView(selectedBoozes);

        virhe.setText("Debug");

        jLabel3.setText("Ainekset");

        jLabel4.setText("Kaapista löytyvät");

        btnRemoveBooze.setText("Poista alkoholi");

        jScrollPane3.setViewportView(selectedBoozes1);

        jLabel5.setText("Mahdolliset drinkit");

        jLabel6.setText("Kuvaus:");

        jLabel8.setText("Valitse listasta alkoholit, joita sinulla on käsillä. Lisää listaan juomia tarvittaessa.");

        jButtonMuokkaaDrinkkeja.setText("Muokkaa drinkkejä");
        jButtonMuokkaaDrinkkeja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMuokkaaDrinkkejaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2)
                                    .addComponent(txtfieldAddBooze)
                                    .addComponent(btnAddBooze, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnRemoveBooze, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButtonMuokkaaDrinkkeja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jLabel1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(virhe)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtfieldAddBooze, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddBooze)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemoveBooze)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonMuokkaaDrinkkeja))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                            .addComponent(jScrollPane3)
                            .addComponent(jScrollPane1))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(virhe)))
                .addContainerGap(61, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonMuokkaaDrinkkejaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMuokkaaDrinkkejaActionPerformed
        this.setVisible(false);
        drinkkiFrame.setVisible(true);
    }//GEN-LAST:event_jButtonMuokkaaDrinkkejaActionPerformed

    /**
     * @param args the command line arguments
     */
 


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddBooze;
    private javax.swing.JButton btnRemoveBooze;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButtonMuokkaaDrinkkeja;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList<String> listMyBooze;
    private javax.swing.JList<String> selectedBoozes;
    private javax.swing.JList<String> selectedBoozes1;
    private javax.swing.JTextField txtfieldAddBooze;
    private javax.swing.JLabel virhe;
    // End of variables declaration//GEN-END:variables
}
