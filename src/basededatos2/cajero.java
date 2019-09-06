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
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hance
 */
public class cajero extends javax.swing.JFrame {
String us = Pantallap.usuariodb;
String contr =Pantallap.cnusuariobd;
String rol= Pantallap.rol;
String url =  Pantallap.url;
int ii = Pantallap.id_rol;
static Connection cn;
 static Statement s;
 static ResultSet rs;
    /**
     * Creates new form personalizado
     */
    public cajero() {
        initComponents();
        cargar_imagen1();
        listacheques();
        acep.setVisible(false);
        ccheque.setVisible(false);
        this.setLocationRelativeTo(null);
    }
    
    
    public void cargar_imagen1 (){
       ImageIcon imagen = new ImageIcon ("C://Users//hance//Documents//IMAGENES PROYECTOS//fondo1.jpg");
        Icon icono = new ImageIcon (imagen.getImage().getScaledInstance(jLabel1.getWidth(), jLabel1.getHeight(), Image.SCALE_DEFAULT));
        jLabel1.setIcon(icono);
        this.repaint();
       }
 public void listacheques (){
try {   
         DefaultTableModel modelo = new DefaultTableModel();
         int cantidadColumnas;
            modelo.setRowCount(0);
           
            cn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "administrador","12345");
            //Para ejecutar la consulta
            s = cn.createStatement();
            //Ejecutamos la consulta y los datos lo almacenamos en un ResultSet
                  
            rs = s.executeQuery("select "
                                + "id_cheque, id_chequera,lugar,beneficiario,monto,estado, fecha "
                                + " from cheques where estado = 'generado' or estado= 'disponible' order by id_cheque desc");
            //Obteniendo la informacion de las columnas que estan siendo consultadas
            ResultSetMetaData rsd = rs.getMetaData();
            cantidadColumnas = rsd.getColumnCount();
            for (int i = 1; i <= cantidadColumnas; i++) {
             modelo.addColumn(rsd.getColumnLabel(i));
            }
            Object[] fila = new Object[cantidadColumnas];
            //Creando las filas para el JTable
            while (rs.next()) {
             
             for (int i = 0; i < cantidadColumnas; i++) {
               fila[i]=rs.getObject(i+1);
             }
             modelo.addRow(fila);
            }
            rs.close();
            cn.close();
         tabla1.setModel(modelo);   
       } catch (Exception ex) {
        System.out.println("error: "+ex);
       }

}
  public void listacheques1 (String com,String com2){
try {   
         DefaultTableModel modelo = new DefaultTableModel();
         int cantidadColumnas;
            modelo.setRowCount(0);
            cn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "ADMINISTRADOR","12345");
            //Para ejecutar la consulta
            s = cn.createStatement();
            //Ejecutamos la consulta y los datos lo almacenamos en un ResultSet
                  
            rs = s.executeQuery("select * from caj where "
                                + " "+com+" like '"+com2+"%' "
                                + "OR  "+com+" like '%"+com2+"%'" 
                                   + " OR "+com+" like '%"+com2+"' "
            );
            //Obteniendo la informacion de las columnas que estan siendo consultadas
            ResultSetMetaData rsd = rs.getMetaData();
            cantidadColumnas = rsd.getColumnCount();
            for (int i = 1; i <= cantidadColumnas; i++) {
             modelo.addColumn(rsd.getColumnLabel(i));
            }
            Object[] fila = new Object[cantidadColumnas];
            //Creando las filas para el JTable
            while (rs.next()) {
             
             for (int i = 0; i < cantidadColumnas; i++) {
               fila[i]=rs.getObject(i+1);
             }
             modelo.addRow(fila);
            }
            rs.close();
            cn.close();
            tabla1.removeAll();
         tabla1.setModel(modelo);   
       } catch (Exception ex) {
        System.out.println("error: "+ex);
       }

}
 public void comboc()
    {
        try{
            cn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE",us,contr);
            s=cn.createStatement();
            rs=s.executeQuery("select id_cheque from administrador.lista_mostrarcheques where id_c = "+ii+ " and estado ='activo' ");
            ccheque.removeAllItems();
            while(rs.next()){
            ccheque.addItem(rs.getString(1));
            }
            cn.close();
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            
        }
    }
 public void combofil()
    {
        try{
            cn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","ADMINISTRADOR","12345");
            s=cn.createStatement();
            rs=s.executeQuery(" desc cheques ");
            copcion.removeAllItems();
            while(rs.next()){
            copcion.addItem(rs.getString(1));
            }
            cn.close();
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            
        }
    }
 public void liberar(int id)
    {
        try{
            cn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE",us,contr);
            s=cn.createStatement();
            rs=s.executeQuery(" BEGIN "
                            + " ADMINISTRADOR.LIBERAR_CHEQUE("+id+"); "
                            + " END; ");
            DesktopNotify.showDesktopMessage("Mensaje de Éxito", "Cheque con id: "+id+" \n Fue liberado exitosamente ", DesktopNotify.SUCCESS);
            
            cn.close();
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ccheque = new javax.swing.JComboBox<>();
        acep = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        copcion = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabla1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, 630, 700));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cerrar_cancelar.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 630, -1, 70));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/editarval.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 370, 50, 60));

        ccheque.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "id_cheque" }));
        getContentPane().add(ccheque, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 560, 240, -1));

        acep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/aceptar.png"))); // NOI18N
        acep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                acepMouseClicked(evt);
            }
        });
        getContentPane().add(acep, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 640, 70, 60));

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 129, 260, 40));

        copcion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lugar", "Fecha", "Beneficiario", "monto" }));
        getContentPane().add(copcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 70, 260, -1));

        jLabel4.setText("FILTRAR POR:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 40, 150, -1));

        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 750));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        Pantallap p = new Pantallap();
        p.setVisible(true);
        this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
ccheque.setVisible(true);
acep.setVisible(true);
listacheques();
comboc();


// TODO add your handling code here:
    }//GEN-LAST:event_jLabel3MouseClicked

    private void acepMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_acepMouseClicked
int i = Integer.parseInt(ccheque.getSelectedItem().toString());
        liberar (i);
acep.setVisible(false);
ccheque.setVisible(false);
listacheques();

// TODO add your handling code here:
    }//GEN-LAST:event_acepMouseClicked

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
String c1 = copcion.getSelectedItem().toString();
String c2 = jTextField1.getText();
listacheques1(c1,c2);


        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1KeyPressed

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
            java.util.logging.Logger.getLogger(cajero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(cajero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(cajero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(cajero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new cajero().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel acep;
    private javax.swing.JComboBox<String> ccheque;
    private javax.swing.JComboBox<String> copcion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tabla1;
    // End of variables declaration//GEN-END:variables
}
