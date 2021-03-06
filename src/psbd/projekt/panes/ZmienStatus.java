/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psbd.projekt.panes;

import java.awt.GridLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import psbd.projekt.classes.Pracownik;
import psbd.projekt.model.Hotel;
import psbd.projekt.model.SiecHoteli;
import psbd.projekt.utils.MySql;

/**
 *
 * @author Michal
 */
public class ZmienStatus extends javax.swing.JPanel {

    JPanel p = new JPanel();

    /**
     * Creates new form ZmienStatus
     */
    public ZmienStatus() {
        initComponents();
    }

    public void updatePanel() {

    }

    public void renderujHotele() {
        Hotel[] hotele = SiecHoteli.getInstance().getHotele();
        int size = hotele.length;
        p.removeAll();
        p.setLayout(new GridLayout(size, 1));
        for (int i = 0; i < size; ++i) {
            hotele[i].drawPokoje();
            p.add(hotele[i]);
        }
        jScrollPane1.setViewportView(p);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fieldNrRewizji = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();

        jLabel1.setText("Numer rezerwacji:");

        jButton1.setText("Wyszukaj");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Wszystkie");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fieldNrRewizji, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 235, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldNrRewizji, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (Pracownik.getInstance().getUpr() > 0) {
            SiecHoteli.getInstance().clear();
            String nr_rezerwacji = fieldNrRewizji.getText();
            int id_rezerwacji;
            if (!nr_rezerwacji.equals("")) {
                id_rezerwacji = Integer.parseInt(nr_rezerwacji);
            } else {
                id_rezerwacji = 0;
            }
            MySql.getInstance().wyszukajRezerwacje(id_rezerwacji);
            renderujHotele();
        } else {
            JOptionPane.showMessageDialog(null, "Niezalogowany użytkownik!");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (Pracownik.getInstance().getUpr() > 0) {
            MySql.getInstance().wyszukajRezerwacje(0);
            renderujHotele();
        } else {
            JOptionPane.showMessageDialog(null, "Niezalogowany użytkownik!");
        }

    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField fieldNrRewizji;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    public void clear() {
        p.removeAll();
        p.validate();
        p.repaint();
    }
}
