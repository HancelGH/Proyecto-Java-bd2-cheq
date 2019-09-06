/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basededatos2;

import com.sun.glass.events.KeyEvent;
import ds.desktop.notify.DesktopNotify;
import java.awt.Color;
import java.awt.Image;
import java.sql.*;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author hance
 */
public class Pantallap extends javax.swing.JFrame {
        
    public static String usuariodb ="";
    public static String cnusuariobd="";
    public static String url="jdbc:oracle:thin:@localhost:1521:XE";
    public static String rol="";
    public static int id_rol=0;
    /**
     * Creates new form Pantallap
     */
    public Pantallap() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        jTextField1.setBackground(new Color(0,0,0,64) );
        jPasswordField1.setBackground(new Color(0,0,0,64) );
        jb5.setBackground(new Color(0,0,0,64) );
        jb3.setBackground(new Color(0,0,0,64) );
        cargar_imagen();
        if (conectar()){
        boton.setEnabled(true);
        }else{
        boton.setEnabled(false);
        }
    }
       static Connection cn;
       static Statement st;
       static ResultSet rs;
       
       
       public void cargar_imagen (){
       ImageIcon imagen = new ImageIcon ("C://Users//hance//Pictures//Wallp//WallpaperStudio10-98927.jpg");
        Icon icono = new ImageIcon (imagen.getImage().getScaledInstance(lblfondo.getWidth(), lblfondo.getHeight(), Image.SCALE_DEFAULT));
        lblfondo.setIcon(icono);
        this.repaint();
       }
    
       
       public boolean conectar (){
    try {
      cn = DriverManager.getConnection(url,"administrador","12345");
      st = cn.createStatement();
        System.out.println("Conectado correctamente");
    return true;  
    } catch (Exception e) {
    System.out.println("Error al Intentar Conectarse  -- "+e.toString());
    return false;
    }
    
    
}
    
    public void cerrarConexion(){
	  	try {
		   cn.close();
		}catch (SQLException  sqle) {
			JOptionPane.showMessageDialog(null,"Ha ocurrido un error al intentar cerrar la conexion con Oracle. Error:" + sqle.getMessage());                                   
		}
	}
    
    public boolean conectar_usuario (String usuario,String cont){
    try {
      cerrarConexion();
      
      cn = DriverManager.getConnection(url,usuario,cont);
      st = cn.createStatement();
      cn.close();
        System.out.println("Conectado correctamente");
        cerrarConexion();
    return true;  
    } catch (Exception e) {
    System.out.println("Error al Intentar Conectarse  -- "+e.toString());
    
    DesktopNotify.showDesktopMessage("Mensaje de Error", "Usuario o Contraseña Incorrecta.", DesktopNotify.ERROR);
             DesktopNotify.showDesktopMessage("Mensaje de Información", "Si olvido la contraseña Pongase en contacto con el Administrador.", DesktopNotify.INFORMATION);
            jTextField1.setText("USUARIO");
            jPasswordField1.setText("contraseña"); 
    return false; 
    }
    }
    
    
    public void validacion(String us,String cn){
        try {
        String [] registro = new String [6];//cadena de caracteres para almacenar columnas
        String ee;
        rs = st.executeQuery("select * from usuarios where nombre = '"+us+"' and estado = 'activo'");
        while (rs.next()){
                for (int i=0;i<6;i++){
                    registro[i] = rs.getString(i+1);
                    System.out.println(registro[i]);
                }
            }
        if (conectar_usuario(us,cn)){    
            usuariodb = us;
            cnusuariobd = cn;
            DesktopNotify.showDesktopMessage("Mensaje de Éxito", "Usuario Valido como: "+us+" \n Echo por Hancel Guzmán", DesktopNotify.SUCCESS);
            
            if (us.equalsIgnoreCase("ADMINISTRADOR")){    
            DesktopNotify.showDesktopMessage("Mensaje de Éxito", "Ingresando al Sistema como: "+us+"\n Echo por Hancel Guzmán", DesktopNotify.SUCCESS);
            rol="ADMINISTRADOR";
        P_administrador pa= new P_administrador ();
        pa.setVisible(true);
        this.dispose();
        } else {
            conectar();
            validacionrol(us);
            }
        
        //P_administrador pa= new P_administrador ();
        //pa.setVisible(true);
        //this.dispose();
        }
        
        rs.close();
        cerrarConexion();
        conectar();
    } catch (Exception e) {
            System.out.println(e.toString());
            DesktopNotify.showDesktopMessage("Mensaje de Error", "Usuario o Contraseña Incorrecta.", DesktopNotify.ERROR);
             DesktopNotify.showDesktopMessage("Mensaje de Información", "Si olvido la contraseña Pongase en contacto con el Administrador.", DesktopNotify.INFORMATION);
            jTextField1.setText("USUARIO");
            jPasswordField1.setText("contraseña");  
    }
    }
    
    public void validacionrol(String us){
        try {
        String ee="";
        rs = st.executeQuery("select rol from administrador.ver_rol where nombre = '"+us+"' ");
        while (rs.next()){
                ee = rs.getString(1);
                System.out.println(ee);
            }
        rol=ee;
        if (ee.equalsIgnoreCase("PAGOS")){    
            
        DesktopNotify.showDesktopMessage("Mensaje de Éxito", "Ingresando al Sistema como: "+us+" \n  Con el Rol de "+ee+"\n Echo por Hancel Guzmán", DesktopNotify.SUCCESS);
        pagos p= new pagos();
        p.setVisible(true);
        p.setLocationRelativeTo(null);
        this.dispose();
        }
        if (ee.equalsIgnoreCase("CAJERO")){    
            
        DesktopNotify.showDesktopMessage("Mensaje de Éxito", "Ingresando al Sistema como: "+us+" \n  Con el Rol de "+ee+"\n Echo por Hancel Guzmán", DesktopNotify.SUCCESS);
       cajero c = new cajero ();
       c.setVisible(true);
        this.dispose();
        }
        if (ee.equalsIgnoreCase("AUDITORIA")){    
            id_rol=1;
        DesktopNotify.showDesktopMessage("Mensaje de Éxito", "Ingresando al Sistema como: "+us+" \n  Con el Rol de "+ee+"\n Echo por Hancel Guzmán", DesktopNotify.SUCCESS);
        //P_administrador pa= new P_administrador ();
        //pa.setVisible(true);
        personalizado p = new personalizado();
        p.setVisible(true);
        p.setLocationRelativeTo(null);
        this.dispose();
        }
        if (ee.equalsIgnoreCase("GERENCIA")){    
            id_rol=2;
        DesktopNotify.showDesktopMessage("Mensaje de Éxito", "Ingresando al Sistema como: "+us+" \n  Con el Rol de "+ee+"\n Echo por Hancel Guzmán", DesktopNotify.SUCCESS);
        //P_administrador pa= new P_administrador ();
        //pa.setVisible(true);
        personalizado p = new personalizado();
        p.setVisible(true);
        p.setLocationRelativeTo(null);
        this.dispose();
        }
        if (ee.equalsIgnoreCase("JEFE_DE_PAGOS")){    
            
        DesktopNotify.showDesktopMessage("Mensaje de Éxito", "Ingresando al Sistema como: "+us+" \n  Con el Rol de "+ee+"\n Echo por Hancel Guzmán", DesktopNotify.SUCCESS);
        P_administrador pa= new P_administrador ();
        pa.setVisible(true);
        this.dispose();
        }
        
        
        rs.close();
    } catch (Exception e) {
            System.out.println(e.toString());
            DesktopNotify.showDesktopMessage("Mensaje de Error", "SIN ROL ASIGNADO.", DesktopNotify.ERROR);
             DesktopNotify.showDesktopMessage("Mensaje de Información", "Pongase en contacto con el Administrador.", DesktopNotify.INFORMATION);
            jTextField1.setText("USUARIO");
            jPasswordField1.setText("contraseña");  
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

        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jb5 = new javax.swing.JButton();
        jb3 = new javax.swing.JButton();
        boton = new javax.swing.JLabel();
        lblfondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 102, 102));
        setForeground(new java.awt.Color(51, 51, 255));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Yu Gothic Light", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.setText("Empresa Nueva Verapaz");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 150, 20));

        jLabel5.setText("-------------------------------------------------------------------");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 290, 280, -1));

        jPasswordField1.setBackground(new java.awt.Color(204, 204, 204));
        jPasswordField1.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jPasswordField1.setForeground(new java.awt.Color(255, 255, 255));
        jPasswordField1.setText("0000");
        jPasswordField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPasswordField1MouseClicked(evt);
            }
        });
        jPasswordField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField1ActionPerformed(evt);
            }
        });
        jPasswordField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordField1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jPasswordField1KeyTyped(evt);
            }
        });
        getContentPane().add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 260, 270, 40));

        jLabel4.setText("-------------------------------------------------------------------");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 210, 270, -1));

        jTextField1.setBackground(new java.awt.Color(204, 204, 204));
        jTextField1.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setText("USUARIO");
        jTextField1.setBorder(null);
        jTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField1MouseClicked(evt);
            }
        });
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 180, 270, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuario1.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 170, 60, 50));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/password1.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 240, 60, 50));

        jb5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/minimizar.png"))); // NOI18N
        jb5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb5ActionPerformed(evt);
            }
        });
        getContentPane().add(jb5, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 0, 60, 50));

        jb3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cerrar.png"))); // NOI18N
        jb3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb3ActionPerformed(evt);
            }
        });
        getContentPane().add(jb3, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 0, 60, 50));

        boton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar.png"))); // NOI18N
        boton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonMouseClicked(evt);
            }
        });
        getContentPane().add(boton, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 180, 50, 40));
        getContentPane().add(lblfondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 910, 470));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jb5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb5ActionPerformed
this.setExtendedState(1);        // TODO add your handling code here:
    }//GEN-LAST:event_jb5ActionPerformed

    private void jTextField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MouseClicked
jTextField1.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1MouseClicked

    private void jPasswordField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPasswordField1MouseClicked
jPasswordField1.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField1MouseClicked

    private void jPasswordField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField1KeyPressed

        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField1KeyPressed

    private void jb3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb3ActionPerformed
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jb3ActionPerformed

    private void botonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonMouseClicked
        String usuar;
        String cont;
        usuar = jTextField1.getText();
        cont = jPasswordField1.getText();
        validacion(usuar,cont);
               // TODO add your handling code here:
    }//GEN-LAST:event_botonMouseClicked

    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField1ActionPerformed

    private void jPasswordField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField1KeyTyped
    
        char cTprecionada = evt.getKeyChar();
        if(cTprecionada == KeyEvent.VK_ENTER){
            String usuar;
            String cont;
            usuar = jTextField1.getText();
            cont = jPasswordField1.getText();
            validacion(usuar,cont);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField1KeyTyped

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
            java.util.logging.Logger.getLogger(Pantallap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pantallap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pantallap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pantallap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pantallap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel boton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    public static javax.swing.JPasswordField jPasswordField1;
    public static javax.swing.JTextField jTextField1;
    private javax.swing.JButton jb3;
    private javax.swing.JButton jb5;
    private javax.swing.JLabel lblfondo;
    // End of variables declaration//GEN-END:variables
}
