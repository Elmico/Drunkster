/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drunkster;

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author h8951
 */
class ListSelectionHandler implements ListSelectionListener {
   
    DefaultListModel dlm;
    JList listBooze;
    List<Drinkki> drinkitLista = new ArrayList<Drinkki>();
    
    ListSelectionHandler(JLabel virhe, JList listBooze, DefaultListModel dlm){
       // virhe.setText("event listeneri ampuu");
        this.dlm = dlm;
        this.listBooze = listBooze;
    }
    
    ListSelectionHandler(JLabel virhe, List<Drinkki> drinkitLista){
       // virhe.setText("event listeneri ampuu");
        this.dlm = dlm;
        this.listBooze = listBooze;
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        //ListSelectionModel lsm = (ListSelectionModel)e.getSource();
                
        List mylist = listBooze.getSelectedValuesList();
        
        dlm.clear();
        
        for(int i = 0; i < mylist.size(); i++){
            dlm.addElement(mylist.get(i));
        }
    }
}