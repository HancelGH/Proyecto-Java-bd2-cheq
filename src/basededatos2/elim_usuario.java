/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basededatos2;

import ds.desktop.notify.DesktopNotify;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author hance
 */
public class elim_usuario extends javax.swing.JFrame {
static Connection cn;
 static Statement s;
 static ResultSet rs;
 
 String nombredeusuario="";
 int id_deusuario=0;
 //String usuario=Pantallap.usuariodb;
//String cont =Pantallap.cnusuariobd;
 
    /**
     * Creates new form elim_usuario
     */
    public elim_usuario() {
        initComponents();
        this.setLocationRelativeTo(null);
        cargar_imagen1();
        EL_USID.setSelected(true);
        jTextField1.setEnabled(true);
        jTextField2.setEnabled(false);
    }
 public void cargar_imagen1 (){
       ImageIcon imagen = new ImageIcon ("C://Users//hance//Documents//IMAGENES PROYECTOS//fondo1.jpg");
        Icon icono = new ImageIcon (imagen.getImage().getScaledInstance(jLabel9.getWidth(), jLabel9.getHeight(), Image.SCALE_DEFAULT));
        jLabel9.setIcon(icono);
        this.repaint();
       }
 
 public void elus(){
        try{

            cn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","administrador","12345");
            s=cn.createStatement();
            
            if(ELUSNO.isSelected()){
            rs=s.executeQuery("begin "
                             + "dardebaja_usuarionombre ("+nombredeusuario+");"
                             + "end;");
             DesktopNotify.showDesktopMessage("Mensaje de Éxito", "Usuario "+nombredeusuario+" ha sido eliminado.", DesktopNotify.SUCCESS);
             }
            
            if(EL_USID.isSelected()){
            rs=s.executeQuery("begin "
                             + "dardebaja_usuario ("+id_deusuario+");"
                             + "end;");
           DesktopNotify.showDesktopMessage("Mensaje de Éxito", "Usuario con el id:"+id_deusuario+" a sido eliminado.", DesktopNotify.SUCCESS);
            }
        }
        catch(Exception ex)
        {
            DesktopNotify.showDesktopMessage("Mensaje de Fallo", "Fallo en el sistema por el cual no se ha eliminado al usuario error:"+ex.getMessage()+".", DesktopNotify.FAIL);
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

        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        EL_USID = new javax.swing.JCheckBox();
        ELUSNO = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        ok = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, 162, 30));
        getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, 240, 28));

        EL_USID.setBackground(new java.awt.Color(204, 204, 204));
        EL_USID.setForeground(new java.awt.Color(204, 255, 0));
        EL_USID.setText("ELIMINAR POR ID");
        EL_USID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EL_USIDActionPerformed(evt);
            }
        });
        getContentPane().add(EL_USID, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 141, -1));

        ELUSNO.setBackground(new java.awt.Color(204, 204, 204));
        ELUSNO.setForeground(new java.awt.Color(204, 255, 0));
        ELUSNO.setText("ELIMINAR POR NOMBRE");
        ELUSNO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ELUSNOActionPerformed(evt);
            }
        });
        getContentPane().add(ELUSNO, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 150, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cerrar_cancelar.png"))); // NOI18N
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 250, -1, -1));

        ok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/aceptar.png"))); // NOI18N
        ok.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                okMouseClicked(evt);
            }
        });
        getContentPane().add(ok, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 70, 70));
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 320));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        this.dispose();

        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    private void okMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okMouseClicked
        nombredeusuario=jTextField2.getText();
        id_deusuario=Integer.parseInt(jTextField1.getText());
        elus();    
        this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_okMouseClicked

    
    
    private void EL_USIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EL_USIDActionPerformed

        if(EL_USID.isSelected())
        {
        jTextField2.setEnabled(false);
        jTextField1.setEnabled(true);
        ELUSNO.setSelected(false);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_EL_USIDActionPerformed

    private void ELUSNOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ELUSNOActionPerformed
    if(ELUSNO.isSelected()){
        jTextField1.setEnabled(false);
        jTextField2.setEnabled(true);  
        EL_USID.setSelected(false);
    }
    // TODO add your handling code here:
    }//GEN-LAST:event_ELUSNOActionPerformed

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
            java.util.logging.Logger.getLogger(elim_usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(elim_usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(elim_usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(elim_usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new elim_usuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox ELUSNO;
    private javax.swing.JCheckBox EL_USID;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel ok;
    // End of variables declaration//GEN-END:variables
}
