/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basededatos2;


import ds.desktop.notify.DesktopNotify;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author hance
 */
public class pagos extends javax.swing.JFrame {
String url=Pantallap.url;
String usuario=Pantallap.usuariodb;
String contr = Pantallap.cnusuariobd;
int in=0;
int dis=0;
static Connection cn;
 static Statement s;
 static ResultSet rs;
 
    /**
     * Creates new form pagos
     */
    public pagos() {
        initComponents();
        combobancos();
        jtben.setEditable(false);
        jtmonto.setEditable(false);
        jtlugar.setEditable(false);
        acep.setVisible(false);
        jrmin.setVisible(false);
        jrmax.setVisible(false);
                    
        
    }
    
    public void combobancos()
    {
        try{
            cn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE",usuario,contr);
            s=cn.createStatement();
            rs=s.executeQuery("select nombre from administrador.lista_bancos");
            cbanco.removeAllItems();
            while(rs.next()){
            cbanco.addItem(rs.getString(1));
            }
            cn.close();
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            
        }
    }
    
    
    public void combochequeras(String nombre_banco)
    {
        try{
            
            cn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE",usuario,contr);
            s=cn.createStatement();
            rs=s.executeQuery("select No_chequera from administrador.lista_chequera " +
                               "where nombre = '"+nombre_banco+"' AND  disponible>0 ");
            cchequera.removeAllItems();
            
            while(rs.next()){
            cchequera.addItem(rs.getString(1));  
            }
            cn.close();
         
        }
        catch(Exception ex)
        {
           // DesktopNotify.showDesktopMessage("Mensaje de Error", "Rango no Permitido.", DesktopNotify.ERROR);
            
            
        }
    }
    public void calc_nochequef(String banc,int ich){
        try{    
                cn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE",usuario,contr);
                s=cn.createStatement();
                rs=s.executeQuery(" select inicio from administrador.c_final "
                                + " where nombre = '"+ banc +"' and id_chequera = "+ich+" ");
                while (rs.next()){
                        in = rs.getInt(1);
                }
                System.out.println(in);
                cn.close();
          }
          catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            
        }
    }  
    public void calc_nochequed(String banc,int ich){
        try{    int ff=25;
                int zz=0;
                cn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE",usuario,contr);
                s=cn.createStatement();
                rs=s.executeQuery(" select disponibles from administrador.c_final "
                                + " where nombre = '"+ banc +"' and id_chequera = "+ich+" ");
                while (rs.next()){
                dis = rs.getInt(1);
                }
                
                zz = ff - dis;
                in = in + zz;
                System.out.println(in+"  =+   "+ff+"  -  "+dis);
                jLabel7.setText("No de Cheque: "+in);
                cn.close();
          }
          catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            
        }
    }
    public void crear_cheques(int idbanco,String lugar,String Ben,int monto){
          try{  cn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE",usuario,contr);
                s=cn.createStatement();
                rs=s.executeQuery(" begin "
                                 +" administrador.crear_cheque ("+idbanco+", '"+lugar+"','"+Ben+"',"+monto+");"
                                + " end;");
                DesktopNotify.showDesktopMessage("Cheque Solicitado", " Revise la validacion del Cheque ", DesktopNotify.SUCCESS);    
                cn.close();
               
          }
          catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            DesktopNotify.showDesktopMessage("Mensaje de Error", "Rango no Permitido.", DesktopNotify.ERROR);
            
        }
}
    
     public void validando(){
        try{    String es ="";
                cn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","ADMINISTRADOR","12345");
                s=cn.createStatement();
                rs=s.executeQuery(" SELECT estado FROM Historial_cheques where usuario = '"+usuario+"' and rownum =1 order by id_historial desc");
                while (rs.next()){
                es=rs.getString(1);
                }
                cn.close();
                DesktopNotify.showDesktopMessage("Cheque Solicitado", " Estado : "+es+" ", DesktopNotify.INFORMATION);
          }
          catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            
        }
    }
     public void rangominimo(){
        try{    int es =0;
                cn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","ADMINISTRADOR","12345");
                s=cn.createStatement();
                rs=s.executeQuery(" SELECT r_min FROM usuarios where nombre = '"+usuario+"' ");
                while (rs.next()){
                es=rs.getInt(1);
                }
                jrmin.setText("Rango minimo: "+es);
                cn.close();
                
          }
          catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            
        }
    }
     public void rangomaximo(){
        try{    int es =0;
                cn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","ADMINISTRADOR","12345");
                s=cn.createStatement();
                rs=s.executeQuery(" SELECT r_max FROM usuarios where nombre = '"+usuario+"' ");
                while (rs.next()){
                es=rs.getInt(1);
                }
                jrmax.setText("Rango maximo: "+es);
                cn.close();
                
          }
          catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            
        }
    }
    
public void limpiarcheque(){
                    jtlugar.setText("");
                    jtben.setText("");
                    jtmonto.setText("");
                    jLabel7.setText("No de Cheque: ");
                    cbanco.setEnabled(true);
                    cchequera.setEnabled(true);
}
public void val (){
                    String banco = cbanco.getSelectedItem().toString();
                    int c = Integer.parseInt(cchequera.getSelectedItem().toString());
                    calc_nochequef(banco,c);
                    calc_nochequed(banco,c);
                    acep.setVisible(true);
                    cbanco.setEnabled(false);
                    cchequera.setEnabled(false);
                    
                    jtben.setEditable(true);
                    jtmonto.setEditable(true);
                    jtlugar.setEditable(true);
                    
                    
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cbanco = new javax.swing.JComboBox<>();
        cchequera = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jtben = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jtlugar = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jtmonto = new javax.swing.JTextField();
        acep = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jrmin = new javax.swing.JLabel();
        jrmax = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbanco.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Banco" }));
        cbanco.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbancoMouseClicked(evt);
            }
        });
        cbanco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbancoActionPerformed(evt);
            }
        });
        getContentPane().add(cbanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 244, -1));

        cchequera.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chequera" }));
        cchequera.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cchequeraMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cchequeraMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cchequeraMouseExited(evt);
            }
        });
        cchequera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cchequeraActionPerformed(evt);
            }
        });
        getContentPane().add(cchequera, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, 130, -1));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("NO, de chequera");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, 130, 20));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("Banco");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 50, 20));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/beneficiario.png"))); // NOI18N
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        jtben.setFont(new java.awt.Font("Lucida Console", 1, 12)); // NOI18N
        jtben.setText("Benefiiciario");
        jtben.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbenMouseClicked(evt);
            }
        });
        getContentPane().add(jtben, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 580, 40));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/direccion.png"))); // NOI18N
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        jtlugar.setFont(new java.awt.Font("Lucida Console", 1, 12)); // NOI18N
        jtlugar.setText("Lugar");
        jtlugar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtlugarMouseClicked(evt);
            }
        });
        getContentPane().add(jtlugar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 580, 50));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/q.png"))); // NOI18N
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, -1, -1));

        jtmonto.setFont(new java.awt.Font("Lucida Console", 1, 12)); // NOI18N
        jtmonto.setText("Monto");
        jtmonto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtmontoMouseClicked(evt);
            }
        });
        jtmonto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtmontoActionPerformed(evt);
            }
        });
        getContentPane().add(jtmonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 260, 327, 40));

        acep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/aceptar.png"))); // NOI18N
        acep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                acepMouseClicked(evt);
            }
        });
        getContentPane().add(acep, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 270, 70, -1));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/limpiar.png"))); // NOI18N
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 170, 59, 64));

        jLabel3.setText("Pague a la orden de:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 200, 20));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cerrar_cancelar.png"))); // NOI18N
        jLabel4.setText("jLabel4");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 270, 70, 60));

        jLabel5.setText("Lugar:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 90, -1));

        jLabel6.setText("Monto");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 60, -1));

        jLabel7.setText("No de Cheque: ");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 20, 110, 20));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mas.png"))); // NOI18N
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 10, -1, 30));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/detective.png"))); // NOI18N
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel9MouseExited(evt);
            }
        });
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 70, 50, 40));

        jrmin.setText("Rango min:");
        getContentPane().add(jrmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 70, 140, -1));

        jrmax.setText("Rango max:");
        getContentPane().add(jrmax, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 120, 140, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbancoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbancoMouseClicked
        combobancos();        // TODO add your handling code here:
    }//GEN-LAST:event_cbancoMouseClicked

    private void cbancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbancoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbancoActionPerformed

    private void cchequeraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cchequeraMouseClicked
        String nombre_banco = cbanco.getSelectedItem().toString();
        combochequeras(nombre_banco);
        // TODO add your handling code here:
    }//GEN-LAST:event_cchequeraMouseClicked

    private void cchequeraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cchequeraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cchequeraActionPerformed

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        limpiarcheque();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel15MouseClicked

    private void acepMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_acepMouseClicked

        try {
            String l = jtlugar.getText();
            String b = jtben.getText();
            int mon = Integer.parseInt(jtmonto.getText());
            int i = Integer.parseInt(cchequera.getSelectedItem().toString());
            crear_cheques(i,l,b,mon);
            validando();
            limpiarcheque();
            acep.setVisible(false);
        } catch (Exception e) {
            DesktopNotify.showDesktopMessage("Error", "En el Campo de monto Solo pueden ingresar Numeros.", DesktopNotify.ERROR);

        }

        // TODO add your handling code here:
    }//GEN-LAST:event_acepMouseClicked

    private void jtmontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtmontoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtmontoActionPerformed

    private void jtmontoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtmontoMouseClicked
        jtmonto.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_jtmontoMouseClicked

    private void jtbenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbenMouseClicked
        jtben.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_jtbenMouseClicked

    private void jtlugarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtlugarMouseClicked
        jtlugar.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_jtlugarMouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
this.dispose(); 
Pantallap p = new Pantallap ();
p.setVisible(true);
// TODO add your handling code here:
    }//GEN-LAST:event_jLabel4MouseClicked

    private void cchequeraMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cchequeraMouseEntered
     // TODO add your handling code here:
    }//GEN-LAST:event_cchequeraMouseEntered

    private void cchequeraMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cchequeraMouseExited
           // TODO add your handling code here:
    }//GEN-LAST:event_cchequeraMouseExited

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
val();



// TODO add your handling code here:
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked

rangominimo();
rangomaximo();
// TODO add your handling code here:
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLabel9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseEntered
jrmin.setVisible(true);
jrmax.setVisible(true);   
rangominimo();
rangomaximo();// TODO add your handling code here:
    }//GEN-LAST:event_jLabel9MouseEntered

    private void jLabel9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseExited
jrmin.setVisible(false);
jrmax.setVisible(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel9MouseExited

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
            java.util.logging.Logger.getLogger(pagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new pagos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel acep;
    private javax.swing.JComboBox<String> cbanco;
    private javax.swing.JComboBox<String> cchequera;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jrmax;
    private javax.swing.JLabel jrmin;
    private javax.swing.JTextField jtben;
    private javax.swing.JTextField jtlugar;
    private javax.swing.JTextField jtmonto;
    // End of variables declaration//GEN-END:variables
}
