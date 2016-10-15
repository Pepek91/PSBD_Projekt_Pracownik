/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psbd.projekt.model;

import java.awt.GridLayout;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import psbd.projekt.panes.Oblozenie;
import psbd.projekt.utils.MySql;

/**
 *
 * @author Michal
 */
public class RaportyDialog extends javax.swing.JDialog {
    
    Oblozenie oblozenie = new Oblozenie(this);

    /**
     * Creates new form RaportyDialog
     */
    public RaportyDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        initWyszukiwarka();
        ArrayList<String> hotele = MySql.getInstance().pobierzHotele();
        int size = hotele.size();
        Object[][] tabela = new Object[size][2];
        for (int i = 0; i < size; ++i) {
            tabela[i][0] = hotele.get(i);
            tabela[i][1] = false;
        }
        thotele.setModel(new javax.swing.table.DefaultTableModel(
                tabela,
                new String[]{
                    "", ""
                }
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean[]{
                false, true
            };
            
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
            
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        thotele.getColumnModel().getColumn(0).setMinWidth(50);
        thotele.getColumnModel().getColumn(0).setMaxWidth(120);
        thotele.getColumnModel().getColumn(1).setMinWidth(20);
        thotele.getColumnModel().getColumn(1).setPreferredWidth(20);
        thotele.getColumnModel().getColumn(1).setMaxWidth(20);
    }
    
    private void initWyszukiwarka() {
        List<String> dzien = new ArrayList<String>();
        for (int i = 1; i < 32; i++) {
            dzien.add("" + i);
        }
        List<String> miesiac = new ArrayList<String>();
        for (int i = 1; i < 13; i++) {
            miesiac.add("" + i);
        }
        int year = Calendar.getInstance().get(Calendar.YEAR);
        
        List<String> rok = new ArrayList<String>();
        for (int i = 0; i < 121; i++) {
            int rok2 = year - i;
            rok.add("" + rok2);
        }
        
        cbDzienDo.setModel(new DefaultComboBoxModel(dzien.toArray()));
        cbDzienOd.setModel(new DefaultComboBoxModel(dzien.toArray()));
        
        cbMiesiacDo.setModel(new DefaultComboBoxModel(miesiac.toArray()));
        cbMiesiacOd.setModel(new DefaultComboBoxModel(miesiac.toArray()));
        
        cbRokDo.setModel(new DefaultComboBoxModel(rok.toArray()));
        cbRokOd.setModel(new DefaultComboBoxModel(rok.toArray()));
        
        cbDzienOd.setToolTipText("Dzień");
        cbMiesiacOd.setToolTipText("Miesiąc");
        cbRokOd.setToolTipText("Rok");
        
        cbDzienDo.setToolTipText("Dzień");
        cbMiesiacDo.setToolTipText("Miesiąc");
        cbRokDo.setToolTipText("Rok");
    }
    
    public void sporzadzRaportOblozenie(String sort) {
        jPanel1.removeAll();
        
        Date dataOd = new Date(
                Integer.parseInt((String) cbRokOd.getSelectedItem()) - 1900,
                Integer.parseInt((String) cbMiesiacOd.getSelectedItem()) - 1,
                Integer.parseInt((String) cbDzienOd.getSelectedItem())
        );
        Date dataDo = new Date(
                Integer.parseInt((String) cbRokDo.getSelectedItem()) - 1900,
                Integer.parseInt((String) cbMiesiacDo.getSelectedItem()) - 1,
                Integer.parseInt((String) cbDzienDo.getSelectedItem())
        );
        if (dataDo.before(dataOd)) {
            JOptionPane.showMessageDialog(null,
                    "Data przyjazdu nie może być równa dacie wyjazdu!",
                    "Błędna data!",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        ArrayList<String> hotele = new ArrayList<>();
        int tabelSize = thotele.getModel().getRowCount();
        for (int i = 0; i < tabelSize; ++i) {
            if ((boolean) thotele.getValueAt(i, 1)) {
                hotele.add((String) thotele.getValueAt(i, 0));
            }
        }
        
        if (hotele.size() == 0) {
            JOptionPane.showMessageDialog(null,
                    "Nie wybrano hoteli!",
                    "Błędne dane!",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        int standard = 0;
        if (cbstandart.isSelected()) {
            standard = Integer.parseInt((String) cbstandardIle.getSelectedItem());
        }
        int goscie = 0;
        if (cbGoscie.isSelected()) {
            goscie = Integer.parseInt((String) cbgoscieIle.getSelectedItem());
        }
        oblozenie.updateJpanel(dataOd, dataDo, hotele, standard, goscie, sort);
        oblozenie.renderHotele();
        jPanel1.setLayout(new GridLayout(1, 1));
        jPanel1.add(oblozenie);
        jPanel1.validate();
        jPanel1.repaint();
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
        jLabel2 = new javax.swing.JLabel();
        cbRodzaje = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        thotele = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbstandart = new javax.swing.JCheckBox();
        cbstandardIle = new javax.swing.JComboBox();
        cbGoscie = new javax.swing.JCheckBox();
        cbgoscieIle = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jToggleButton2 = new javax.swing.JToggleButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbDzienOd = new javax.swing.JComboBox();
        cbMiesiacOd = new javax.swing.JComboBox();
        cbRokOd = new javax.swing.JComboBox();
        cbMiesiacDo = new javax.swing.JComboBox();
        cbDzienDo = new javax.swing.JComboBox();
        cbRokDo = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Rodzaj raportu:");

        jLabel2.setText("Nazwa Hotelu");

        cbRodzaje.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Obłożenie hoteli", "Niezrealizowane rezerwacje", "Popularność hoteli" }));
        cbRodzaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbRodzajeActionPerformed(evt);
            }
        });

        thotele.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(thotele);
        if (thotele.getColumnModel().getColumnCount() > 0) {
            thotele.getColumnModel().getColumn(0).setMinWidth(50);
            thotele.getColumnModel().getColumn(0).setMaxWidth(120);
            thotele.getColumnModel().getColumn(1).setMinWidth(25);
            thotele.getColumnModel().getColumn(1).setPreferredWidth(25);
            thotele.getColumnModel().getColumn(1).setMaxWidth(25);
        }

        jScrollPane1.setViewportView(jScrollPane2);

        jLabel3.setText("Dodatkowe kryteria");

        jLabel4.setText("Standard hotelu");

        cbstandart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbstandartActionPerformed(evt);
            }
        });

        cbstandardIle.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4" }));

        cbGoscie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbGoscieActionPerformed(evt);
            }
        });

        cbgoscieIle.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "", "10", "11", "12", "13", "14", "15" }));

        jLabel5.setText("Liczba gości w pokoju");

        jToggleButton2.setText("Zapisz");

        jLabel6.setText("OD:");

        jLabel7.setText("DO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 570, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jButton1.setText("Raport");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel6)
                        .addComponent(jLabel7)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(cbRodzaje, 0, 1, Short.MAX_VALUE))
                        .addComponent(cbRokDo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(cbDzienDo, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cbMiesiacDo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(cbDzienOd, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(3, 3, 3)
                            .addComponent(cbMiesiacOd, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jLabel2)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButton1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jToggleButton2))
                        .addComponent(cbRokOd, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addGap(12, 12, 12)
                            .addComponent(cbGoscie)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cbgoscieIle, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbstandart)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbstandardIle, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(cbRodzaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbDzienOd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbMiesiacOd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbRokOd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbDzienDo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbMiesiacDo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addComponent(cbRokDo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbstandardIle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(cbstandart))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(cbGoscie)
                            .addComponent(cbgoscieIle, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jToggleButton2)
                            .addComponent(jButton1)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbstandartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbstandartActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbstandartActionPerformed

    private void cbGoscieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbGoscieActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbGoscieActionPerformed

    private void cbRodzajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbRodzajeActionPerformed
        jPanel1.removeAll();
    }//GEN-LAST:event_cbRodzajeActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (cbRodzaje.getSelectedIndex() == 0) {
            sporzadzRaportOblozenie(null);
        } else {
            jPanel1.removeAll();
            jPanel1.setLayout(new GridLayout(1, 1));
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbDzienDo;
    private javax.swing.JComboBox cbDzienOd;
    private javax.swing.JCheckBox cbGoscie;
    private javax.swing.JComboBox cbMiesiacDo;
    private javax.swing.JComboBox cbMiesiacOd;
    private javax.swing.JComboBox cbRodzaje;
    private javax.swing.JComboBox cbRokDo;
    private javax.swing.JComboBox cbRokOd;
    private javax.swing.JComboBox cbgoscieIle;
    private javax.swing.JComboBox cbstandardIle;
    private javax.swing.JCheckBox cbstandart;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JTable thotele;
    // End of variables declaration//GEN-END:variables
}
