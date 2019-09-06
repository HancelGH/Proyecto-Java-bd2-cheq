/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basededatos2;

import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import ds.desktop.notify.*;
/**
 *
 * @author hancel
 */
public class Nuevo_usuario extends javax.swing.JFrame {
static Connection cn;
 static Statement s;
 static ResultSet rs;
 String urls = Pantallap.url;
 String nombredeusuario = Pantallap.usuariodb;
 String cont = Pantallap.cnusuariobd;
 String nombre_de_usuario=""; 
 String contra_de_usuario="";
 String direccion_de_usuario="";
 String email_de_usuario="";
 int telefono_de_usuario=0;
 int rangominimo_de_usuario=0;
 int rangomaximo_de_usuario=0;
 String rol_de_usuario="";
 
    /**
     * Creates new form Nuevo_usuario
     */
    public Nuevo_usuario() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        ok.setVisible(false);
        cargar_imagen1();
        comboroles();
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
        jLabel3 = new javax.swing.JLabel();
        usuario = new javax.swing.JTextField();
        tel = new javax.swing.JTextField();
        direccion = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        contra = new javax.swing.JPasswordField();
        r_minimo = new javax.swing.JTextField();
        r_maximo = new javax.swing.JTextField();
        comborol = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        ok = new javax.swing.JLabel();
        vali = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Nuevo usuario");
        setBackground(new java.awt.Color(0, 0, 102));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cerrar_cancelar.png"))); // NOI18N
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 250, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/direccion2.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 30, 40));

        usuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usuarioMouseClicked(evt);
            }
        });
        getContentPane().add(usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 230, 34));

        tel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                telMouseClicked(evt);
            }
        });
        getContentPane().add(tel, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 196, 31));

        direccion.setText("DIRECCION");
        direccion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                direccionMouseClicked(evt);
            }
        });
        getContentPane().add(direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 229, 33));

        email.setText("EMAIL");
        email.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                emailMouseClicked(evt);
            }
        });
        getContentPane().add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, 229, 33));

        contra.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                contraMouseDragged(evt);
            }
        });
        getContentPane().add(contra, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, 182, 34));

        r_minimo.setText("RANGO MINIMO");
        r_minimo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                r_minimoMouseClicked(evt);
            }
        });
        getContentPane().add(r_minimo, new org.netbeans.lib.awtextra.AbsoluteConstraints(348, 112, 182, 33));

        r_maximo.setText("RANGO MAXIMO");
        r_maximo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                r_maximoMouseClicked(evt);
            }
        });
        getContentPane().add(r_maximo, new org.netbeans.lib.awtextra.AbsoluteConstraints(348, 151, 182, 32));

        comborol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(comborol, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, 196, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuario1.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 44, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/email.png"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 40, 30));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/telefono.png"))); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 40, 40));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/diploma.png"))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 40, 30));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/key.png"))); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 50, -1, 40));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cash.png"))); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 120, -1, 50));

        ok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/aceptar.png"))); // NOI18N
        ok.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                okMouseClicked(evt);
            }
        });
        getContentPane().add(ok, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 250, 70, 70));

        vali.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/validacion.png"))); // NOI18N
        vali.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                valiMouseClicked(evt);
            }
        });
        getContentPane().add(vali, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, 30, 40));
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 590, 330));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
this.dispose();  

// TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    private void usuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usuarioMouseClicked
usuario.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_usuarioMouseClicked

    private void direccionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_direccionMouseClicked
direccion.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_direccionMouseClicked

    private void emailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_emailMouseClicked
email.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_emailMouseClicked

    private void telMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_telMouseClicked
tel.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_telMouseClicked

    private void contraMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_contraMouseDragged
contra.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_contraMouseDragged

    private void r_minimoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_r_minimoMouseClicked
r_minimo.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_r_minimoMouseClicked

    private void r_maximoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_r_maximoMouseClicked
r_maximo.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_r_maximoMouseClicked

    private void okMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okMouseClicked
nombre_de_usuario = usuario.getText();
contra_de_usuario = contra.getText();
direccion_de_usuario = direccion.getText();
email_de_usuario = email.getText();
telefono_de_usuario = Integer.parseInt(tel.getText());
rangominimo_de_usuario = Integer.parseInt(r_minimo.getText());
rangomaximo_de_usuario = Integer.parseInt(r_maximo.getText());
rol_de_usuario = comborol.getSelectedItem().toString();
crearusuario();

        // TODO add your handling code here:
    }//GEN-LAST:event_okMouseClicked
 public void cargar_imagen1 (){
       ImageIcon imagen = new ImageIcon ("C://Users//hance//Documents//IMAGENES PROYECTOS//fondo1.jpg");
        Icon icono = new ImageIcon (imagen.getImage().getScaledInstance(jLabel9.getWidth(), jLabel9.getHeight(), Image.SCALE_DEFAULT));
        jLabel9.setIcon(icono);
        this.repaint();
       }
    
    private void valiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valiMouseClicked
    validacion();        // TODO add your handling code here:
    }//GEN-LAST:event_valiMouseClicked

    public void validacion (){
        int u=0;
        int c=0;
        int r_mi=0;
        int r_ma=0;
        int spri=0;
        String usua = usuario.getText();
       String cc = contra.getText();
       String rmi=r_minimo.getText();
       String rm = r_maximo.getText();
       String mensajes="";
       String m="";
       String v="";
       String ac="";
       String t=tel.getText();
       String dir=direccion.getText();
       int auxe=0;
       int rangominimo;
       int rangomaximo;
       try {
            if(rm.equals(""))
             {
            r_ma=1;
            v = "\n RANGO MAXIMO ";
            }else{
            rangomaximo= Integer.parseInt(rm);
            }
           
            if (rmi.equals("")){
            r_mi=1;
            m="\n RANGO MINIMO ";
            }else{
            rangominimo=Integer.parseInt(rmi);
            }
        } catch (Exception e) {
            auxe=1;
        }
       
       
        try {
            if (t.equals("")){
            
            }else{
            int tele=Integer.parseInt(t);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en Telefono: \nNo puede haber letras");
            auxe=1;
        }
       String em=email.getText();
        if (usua.equals("")){
            u=1;
            mensajes = "\n NOMBRE ";
        }
        if (cc.equals("")){
            c =1;
            ac = "\n CONTRASEÑA";
           // JOptionPane.showMessageDialog(null, "La Contraseña no puede ser vacia");
        }
        
       
        
        spri = u+c+r_mi+r_ma;
        
        if (spri ==0){
            if(auxe==1){
            ok.setVisible(false);
           
         }else{
            ok.setVisible(true);
            //DesktopNotify.showDesktopMessage("Mensaje de Advertencia", "Hay elementos Vacio. Se recomienda llenar todo el formulario ", DesktopNotify.WARNING);
            }
        }else
        {
         DesktopNotify.showDesktopMessage("Mensaje de Error", "Hay elmentos escenciales vacios. "+mensajes+" "+ac+" "+v+" "+m+" ", DesktopNotify.ERROR);
        ok.setVisible(false);
        }
    
    }
    public void comboroles()
    {
        try{
            cn=DriverManager.getConnection(urls,nombredeusuario,cont);
            s=cn.createStatement();
            rs=s.executeQuery("select nombre from roles");
            comborol.removeAllItems();
            while(rs.next()){
            comborol.addItem(rs.getString(1));
            }
            cn.close();
         
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
     public void crearusuario()
    {
        try{
            ResultSet rs1;
            ResultSet rs2;
            cn=DriverManager.getConnection(urls,nombredeusuario,cont);
            s=cn.createStatement();
            s.addBatch("create user "+nombre_de_usuario+" identified by "+contra_de_usuario);
            s.addBatch(" grant "+rol_de_usuario+" to "+nombre_de_usuario);
            s.addBatch("begin "
                            + " nuevo_usuario ('"+ nombre_de_usuario+"','"+ rol_de_usuario+"',"+rangominimo_de_usuario +","+rangomaximo_de_usuario+","+telefono_de_usuario+",'"+direccion_de_usuario+"','"+email_de_usuario+"' );"
                            + " end;");
            s.executeBatch();
            cn.close();
            this.dispose();
            DesktopNotify.showDesktopMessage("Mensaje de Éxito", "Usuario agregado con exito.", DesktopNotify.SUCCESS);
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null,ex.getMessage());
            limpiar();
            DesktopNotify.showDesktopMessage("Mensaje de Error", "No se a podido crear al usuario.", DesktopNotify.ERROR);
        
        }
    }
     
     public void limpiar(){
     usuario.setText("");
     direccion.setText("");
     contra.setText("");
     tel.setText("");
     email.setText("");
     r_minimo.setText("");
     r_maximo.setText("");
     }
      
     
     /**  + "+";"+
                            "); */
     
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
            java.util.logging.Logger.getLogger(Nuevo_usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Nuevo_usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Nuevo_usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Nuevo_usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Nuevo_usuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comborol;
    private javax.swing.JPasswordField contra;
    private javax.swing.JTextField direccion;
    private javax.swing.JTextField email;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel ok;
    private javax.swing.JTextField r_maximo;
    private javax.swing.JTextField r_minimo;
    private javax.swing.JTextField tel;
    private javax.swing.JTextField usuario;
    private javax.swing.JLabel vali;
    // End of variables declaration//GEN-END:variables
}