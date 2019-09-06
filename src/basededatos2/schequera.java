/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basededatos2;

import ds.desktop.notify.DesktopNotify;
import java.sql.*;

/**
 *
 * @author hance
 */
public class schequera extends javax.swing.JFrame {
static Connection cn;
static Statement s;
static ResultSet rs;
String urls = Pantallap.url;
String nombredeusuario= Pantallap.usuariodb;
String cont =Pantallap.cnusuariobd;

    /**
     * Creates new form schequera
     */
    public schequera() {
        initComponents();
        jtcantidad.setVisible(false);
        this.setLocationRelativeTo(null);
    }
    
    public void comboban()
    {
        try{
            cn=DriverManager.getConnection(urls,nombredeusuario,cont);
            s=cn.createStatement();
            rs=s.executeQuery("select nombre from cuenta_bancaria where estado = 'activo'");
            combobanco.removeAllItems();
            while(rs.next()){
            combobanco.addItem(rs.getString(1));
            }
            cn.close();
         
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
public void solicitar_chequera(String nb){
try{
            cn=DriverManager.getConnection(urls,nombredeusuario,cont);
            s=cn.createStatement();
            rs=s.executeQuery("begin "
                            + "solicitar_chequera ('"+nb+"',25);"
                            + "end;");
            
            DesktopNotify.showDesktopMessage("Mensaje de Información", " Banco: "+ nb +" A sido agregado una chequera nueva con 25 cheques.", DesktopNotify.INFORMATION);
            cn.close();
         
        }
        catch(Exception ex)
        {
          DesktopNotify.showDesktopMessage("Mensaje de Información", " Error al solicitar Chequera.", DesktopNotify.INFORMATION);
            
        }
}
public void solicitar_chequera1(String nb,int c){
try{
            cn=DriverManager.getConnection(urls,nombredeusuario,cont);
            s=cn.createStatement();
            rs=s.executeQuery("begin "
                            + "solicitar_chequera ('"+nb+"',"+c+");"
                            + "end;");
            
            DesktopNotify.showDesktopMessage("Mensaje de Información", " Banco: "+ nb +" A sido agregado una chequera nueva con "+c+" cheques.", DesktopNotify.INFORMATION);
            cn.close();
         
        }
        catch(Exception ex)
        {
            DesktopNotify.showDesktopMessage("Mensaje de Información", "Error al solicitar Chequera", DesktopNotify.INFORMATION);
            
        }
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        combobanco = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        vcantidad = new javax.swing.JCheckBox();
        jtcantidad = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        combobanco.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bancos" }));
        combobanco.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                combobancoMouseClicked(evt);
            }
        });
        getContentPane().add(combobanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 149, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/aceptar.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 70, -1));

        vcantidad.setBackground(new java.awt.Color(51, 51, 51));
        vcantidad.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        vcantidad.setForeground(new java.awt.Color(51, 204, 0));
        vcantidad.setText("Enviar Cantidad");
        vcantidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vcantidadMouseClicked(evt);
            }
        });
        getContentPane().add(vcantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 150, -1));

        jtcantidad.setText("Cantidad de cheques");
        jtcantidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtcantidadMouseClicked(evt);
            }
        });
        getContentPane().add(jtcantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 150, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cerrar_cancelar.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 60));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/sche.jpg"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 180));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void combobancoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_combobancoMouseClicked
comboban();        // TODO add your handling code here:
    }//GEN-LAST:event_combobancoMouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
    if (vcantidad.isSelected()){
        try {
            int cant = Integer.parseInt(jtcantidad.getText());
            String nombre = combobanco.getSelectedItem().toString();
            solicitar_chequera1(nombre,cant); 
            this.dispose();;
        } catch (Exception e) {
        }
    }else{
    String nombre = combobanco.getSelectedItem().toString();
    solicitar_chequera(nombre); 
    this.dispose();
    }
        
           // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel3MouseClicked

    private void vcantidadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vcantidadMouseClicked
if(vcantidad.isSelected()){
jtcantidad.setVisible(true);
}else{
jtcantidad.setVisible(false);
}        // TODO add your handling code here:
    }//GEN-LAST:event_vcantidadMouseClicked

    private void jtcantidadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtcantidadMouseClicked
jtcantidad.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_jtcantidadMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(schequera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(schequera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(schequera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(schequera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new schequera().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> combobanco;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jtcantidad;
    private javax.swing.JCheckBox vcantidad;
    // End of variables declaration//GEN-END:variables
}
