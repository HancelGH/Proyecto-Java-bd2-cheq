/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basededatos2;

import ds.desktop.notify.DesktopNotify;
import java.awt.Color;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author hance
 */
public class Inicio extends javax.swing.JFrame {

    /**
     * Creates new form Inicio
     */
    public Inicio() {
        initComponents();
        this.setLocationRelativeTo(null);
        cargar_imagen1 ();
        barra();
    }
     public void cargar_imagen1 (){
       ImageIcon imagen = new ImageIcon ("C://Users//hance//Documents//IMAGENES PROYECTOS//nueva_verapaz.png");
        Icon icono = new ImageIcon (imagen.getImage().getScaledInstance(lblfondo1.getWidth(), lblfondo1.getHeight(), Image.SCALE_DEFAULT));
        lblfondo1.setIcon(icono);
        this.repaint();
       }
    
    
   
    
    public void salir(){
    this.dispose();
    }
    
    public void barra (){
    Thread t = new Thread (new Runnable () {
              @Override
              public void run() {
          try{
          int tiempo=0;
          DesktopNotify.showDesktopMessage("Hancel Guzmán , Septimo semestre Ingenieria en Sistemas", "Sistema de Cheques Digitales. Empreza Nueva Verapaz, ", DesktopNotify.TIP, 14000L);

          while(tiempo!=100){
              tiempo = tiempo+1;      
              if (tiempo<40){jLabel2.setText("Cargando");}
                    Thread.sleep(100);
                    jProgressBar1.setValue(tiempo);
                    
                    if(tiempo>=40){
                    jLabel2.setText("Cargando...");
                    
                    if(tiempo >75){
                        jLabel2.setText("-- Ingresando --");
                    }}
          }
          if (tiempo >= 100){
              Pantallap pp = new Pantallap ();
              pp.setVisible(true);
              pp.setLocationRelativeTo(null);
              UsuarioFinal uf = new UsuarioFinal ();
              uf.setVisible(true);
              salir ();
              
          }
          } catch (InterruptedException ex) {
              System.out.println("error : ");
        }
              }
          });
      t.start();
      
}
    
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel2 = new javax.swing.JLabel();
        lblfondo1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jProgressBar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(jProgressBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 510, 30));

        jLabel2.setBackground(new java.awt.Color(204, 204, 204));
        jLabel2.setFont(new java.awt.Font("Museo Sans For Dell", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Cargando");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, 330, 50));
        getContentPane().add(lblfondo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, 420));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JLabel lblfondo1;
    // End of variables declaration//GEN-END:variables
}