/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basededatos2;

//import static basededatos2.Nuevo_usuario.cn;
import ds.desktop.notify.DesktopNotify;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import static java.time.Clock.system;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hance
 */
public class P_administrador extends javax.swing.JFrame {

static Connection cn;
 static Statement s;
 static ResultSet rs;
 
 static Connection cnn;
 static Statement ss;
 static ResultSet rss;
 
String usuario=Pantallap.usuariodb;
String cont =Pantallap.cnusuariobd;
//String usuario= "administrador";
//String cont ="12345";
    /**
     * Creates new form P_administrador
     */
    public P_administrador() {
        initComponents();
        this.setTitle("Pagina Principal");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        inicios();
    }
    
    public void inicios (){
    llamada ();
        ver ();
        ver2 ();
        verusuarios();
        verusuariosinactivos ();
        ver_val();
        ver_chequera();
        vertabla_validaciones ();
        ver_historialc();
        combobancos();
        jmbanco.setVisible(false);
        comborol.setVisible(false);
        comboroles2.setVisible(false);
        
        comboroles1.setVisible(false);
        rol2.setVisible(false);
        pornombre.setSelected(true);
        jbajabanco.setVisible(false);
        jdepositar.setVisible(false);
        jsolicitarch.setVisible(false);
        jrol.setText(Pantallap.rol);
        vercheque ();
    }
    public void llamada(){
        Pantallap pp = new Pantallap();
        uscn.setText(pp.usuariodb);
    }
    public void ver (){
try {   
         DefaultTableModel modelo = new DefaultTableModel();
         int cantidadColumnas;
            modelo.setRowCount(0);
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
           
            cn = DriverManager.getConnection(url, usuario,cont);
            //Para ejecutar la consulta
            s = cn.createStatement();
            //Ejecutamos la consulta y los datos lo almacenamos en un ResultSet
                  
            rs = s.executeQuery("select nombre, saldo from cuenta_bancaria");
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
         tabla_bancos.setModel(modelo);   
       } catch (Exception ex) {
        System.out.println("error: "+ex);
       }

}
    public void ver_historialc(){
try {   
         DefaultTableModel modelo = new DefaultTableModel();
         int cantidadColumnas;
            modelo.setRowCount(0);
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
           
            cn = DriverManager.getConnection(url, usuario,cont);
            //Para ejecutar la consulta
            s = cn.createStatement();
            //Ejecutamos la consulta y los datos lo almacenamos en un ResultSet
                  
            rs = s.executeQuery("select * from historial_cheques order by fecha desc");
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
         HISTORIAL_CHE.setModel(modelo);   
       } catch (Exception ex) {
        System.out.println("error: "+ex);
       }

}
   public void ver_chequera (){
try {   
         DefaultTableModel modelo = new DefaultTableModel();
         int cantidadColumnas;
            modelo.setRowCount(0);
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
           
            cn = DriverManager.getConnection(url, usuario,cont);
            //Para ejecutar la consulta
            s = cn.createStatement();
            //Ejecutamos la consulta y los datos lo almacenamos en un ResultSet
                  
            rs = s.executeQuery("select cuenta_bancaria.nombre as Banco, id_chequera as No_Chequera, disponibles from chequeras "
                                + "inner join cuenta_bancaria on chequeras.id_cuentasb = cuenta_bancaria.id_cuentab where disponibles > 0 order by nombre asc");
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
         tabla_chequeras.setModel(modelo);   
       } catch (Exception ex) {
        System.out.println("error: "+ex);
        //jTabbedPane1.setEnabled(false);
       }

}
   
   public void ver_val(){
try {   
     DefaultTableModel modelo1 = new DefaultTableModel();
      int cantidadc;
            modelo1.setRowCount(0);
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
           
            cnn = DriverManager.getConnection(url, usuario,cont);
            //Para ejecutar la consulta
            ss = cnn.createStatement();
            //Ejecutamos la consulta y los datos lo almacenamos en un ResultSet
                 
            rss = ss.executeQuery("select nombre,monto,estado from control_sistema order by monto asc ");
            //Obteniendo la informacion de las columnas que estan siendo consultadas
            ResultSetMetaData rsd = rss.getMetaData();
            
            cantidadc = rsd.getColumnCount();
            for (int i = 1; i <= cantidadc; i++) {
             modelo1.addColumn(rsd.getColumnLabel(i));
            }
            Object[] fila = new Object[cantidadc];
            //Creando las filas para el JTable
            while (rss.next()) {
             
             for (int i = 0; i < cantidadc; i++) {
               fila[i]=rss.getObject(i+1);
             }
             modelo1.addRow(fila);
            }
            rss.close();
            cnn.close();
            tabla_validaciones.setModel(modelo1);
       } catch (Exception ex) {
        System.out.println("error: "+ex);
       }
}
    
   public void vertabla_validaciones (){
try {   
     DefaultTableModel modelo1 = new DefaultTableModel();
      int cantidadc;
            modelo1.setRowCount(0);
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
           
            cnn = DriverManager.getConnection(url, usuario,cont);
            //Para ejecutar la consulta
            ss = cnn.createStatement();
            //Ejecutamos la consulta y los datos lo almacenamos en un ResultSet
                 
            rss = ss.executeQuery("select nombre,monto from control_sistema where estado = 'activo' order by monto asc ");
            //Obteniendo la informacion de las columnas que estan siendo consultadas
            ResultSetMetaData rsd = rss.getMetaData();
            
            cantidadc = rsd.getColumnCount();
            for (int i = 1; i <= cantidadc; i++) {
             modelo1.addColumn(rsd.getColumnLabel(i));
            }
            Object[] fila = new Object[cantidadc];
            //Creando las filas para el JTable
            while (rss.next()) {
             
             for (int i = 0; i < cantidadc; i++) {
               fila[i]=rss.getObject(i+1);
             }
             modelo1.addRow(fila);
            }
            rss.close();
            cnn.close();
            tabla_val.setModel(modelo1);
       } catch (Exception ex) {
        System.out.println("error: "+ex);
       }
}
   // jInternalFrame1
    public void ver2 (){
try {   
     DefaultTableModel modelo1 = new DefaultTableModel();
      int cantidadc;
            modelo1.setRowCount(0);
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
           
            cnn = DriverManager.getConnection(url, usuario,cont);
            //Para ejecutar la consulta
            ss = cnn.createStatement();
            //Ejecutamos la consulta y los datos lo almacenamos en un ResultSet
                 
            rss = ss.executeQuery("select cuenta_bancaria.nombre as nombre,"
                    + "cuenta_bancaria.saldo as saldo,"
                    + "control_ineg.deposito as deposito,"
                    + "control_ineg.egreso as egreso,"
                    + "control_ineg.fecha as fecha from control_ineg " +
"inner join cuenta_bancaria on control_ineg.fk_cbanco=cuenta_bancaria.id_cuentab order by fecha desc ");
            //Obteniendo la informacion de las columnas que estan siendo consultadas
            ResultSetMetaData rsd = rss.getMetaData();
            
            cantidadc = rsd.getColumnCount();
            for (int i = 1; i <= cantidadc; i++) {
             modelo1.addColumn(rsd.getColumnLabel(i));
            }
            Object[] fila = new Object[cantidadc];
            //Creando las filas para el JTable
            while (rss.next()) {
             
             for (int i = 0; i < cantidadc; i++) {
               fila[i]=rss.getObject(i+1);
             }
             modelo1.addRow(fila);
            }
            rss.close();
            cnn.close();
            jTable1.setModel(modelo1);
       } catch (Exception ex) {
        System.out.println("error: "+ex);
       }
}
    public void verusuarios (){
try {   
     DefaultTableModel modelo2 = new DefaultTableModel();
      int cantidadc;
            modelo2.setRowCount(0);
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
           
            cnn = DriverManager.getConnection(url, usuario,cont);
            //Para ejecutar la consulta
            ss = cnn.createStatement();
            //Ejecutamos la consulta y los datos lo almacenamos en un ResultSet
                 
            rss = ss.executeQuery("select * from usuarios where estado ='activo'");
            //Obteniendo la informacion de las columnas que estan siendo consultadas
            ResultSetMetaData rsd = rss.getMetaData();
            
            cantidadc = rsd.getColumnCount();
            for (int i = 1; i <= cantidadc; i++) {
             modelo2.addColumn(rsd.getColumnLabel(i));
            }
            Object[] fila = new Object[cantidadc];
            //Creando las filas para el JTable
            while (rss.next()) {
             
             for (int i = 0; i < cantidadc; i++) {
               fila[i]=rss.getObject(i+1);
             }
             modelo2.addRow(fila);
            }
            rss.close();
            cnn.close();
            tabla_usuarios.setModel(modelo2);
       } catch (Exception ex) {
        System.out.println("error: "+ex);
       
       }
}


    public void verusuariosinactivos (){
try {   
     DefaultTableModel modelo2 = new DefaultTableModel();
      int cantidadc;
            modelo2.setRowCount(0);
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
           
            cnn = DriverManager.getConnection(url, usuario,cont);
            //Para ejecutar la consulta
            ss = cnn.createStatement();
            //Ejecutamos la consulta y los datos lo almacenamos en un ResultSet
                 
            rss = ss.executeQuery("select * from usuarios where estado = 'inactivo'");
            //Obteniendo la informacion de las columnas que estan siendo consultadas
            ResultSetMetaData rsd = rss.getMetaData();
            
            cantidadc = rsd.getColumnCount();
            for (int i = 1; i <= cantidadc; i++) {
             modelo2.addColumn(rsd.getColumnLabel(i));
            }
            Object[] fila = new Object[cantidadc];
            //Creando las filas para el JTable
            while (rss.next()) {
             
             for (int i = 0; i < cantidadc; i++) {
               fila[i]=rss.getObject(i+1);
             }
             modelo2.addRow(fila);
            }
            rss.close();
            cnn.close();
            tabla_ui.setModel(modelo2);
       } catch (Exception ex) {
        System.out.println("error: "+ex);
        tabla_ui.setEnabled(false);
       }
}
    public void vercheque (){
try {   
     DefaultTableModel modelo2 = new DefaultTableModel();
      int cantidadc;
            modelo2.setRowCount(0);
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
           
            cnn = DriverManager.getConnection(url, usuario,cont);
            //Para ejecutar la consulta
            ss = cnn.createStatement();
            //Ejecutamos la consulta y los datos lo almacenamos en un ResultSet
                 
            rss = ss.executeQuery("select * from cheques order by id_cheque asc ");
            //Obteniendo la informacion de las columnas que estan siendo consultadas
            ResultSetMetaData rsd = rss.getMetaData();
            
            cantidadc = rsd.getColumnCount();
            for (int i = 1; i <= cantidadc; i++) {
             modelo2.addColumn(rsd.getColumnLabel(i));
            }
            Object[] fila = new Object[cantidadc];
            //Creando las filas para el JTable
            while (rss.next()) {
             
             for (int i = 0; i < cantidadc; i++) {
               fila[i]=rss.getObject(i+1);
             }
             modelo2.addRow(fila);
            }
            rss.close();
            cnn.close();
            tcheque.setModel(modelo2);
       } catch (Exception ex) {
        System.out.println("error: "+ex);
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

        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        uscn = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        HISTORIAL_CHE = new javax.swing.JTable();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tabla_validaciones = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jtlugar = new javax.swing.JTextField();
        cbanco = new javax.swing.JComboBox<>();
        cchequera = new javax.swing.JComboBox<>();
        jtben = new javax.swing.JTextField();
        jtmonto = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tcheque = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tabla_chequeras = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jsolicitarch = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jmbanco = new javax.swing.JLabel();
        tabla_historial = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_bancos = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jbajabanco = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jdepositar = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabla_usuarios = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabla_ui = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        comborol = new javax.swing.JComboBox<>();
        comboroles2 = new javax.swing.JComboBox<>();
        rol1 = new javax.swing.JCheckBox();
        direcc = new javax.swing.JCheckBox();
        comboroles1 = new javax.swing.JComboBox<>();
        nombrejt = new javax.swing.JTextField();
        pornombre = new javax.swing.JCheckBox();
        rol2 = new javax.swing.JCheckBox();
        cdireccion = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tabla_val = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jrol = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        jLabel3.setText("jLabel3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/us.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 730, 90, 80));

        uscn.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        uscn.setForeground(new java.awt.Color(0, 153, 102));
        uscn.setText("USUARIO");
        getContentPane().add(uscn, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 780, 360, 30));

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseEntered(evt);
            }
        });

        jTabbedPane4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane4MouseClicked(evt);
            }
        });

        HISTORIAL_CHE.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane10.setViewportView(HISTORIAL_CHE);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 976, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 628, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("HISTORIAL", jPanel15);

        tabla_validaciones.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane8.setViewportView(tabla_validaciones);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 616, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 82, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Validaciones", jPanel14);

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cheques.jpg"))); // NOI18N

        jtlugar.setText("Lugar");
        jtlugar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtlugarMouseClicked(evt);
            }
        });

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

        cchequera.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chequera" }));
        cchequera.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cchequeraMouseClicked(evt);
            }
        });
        cchequera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cchequeraActionPerformed(evt);
            }
        });

        jtben.setText("Benefiiciario");
        jtben.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbenMouseClicked(evt);
            }
        });

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

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/direccion.png"))); // NOI18N

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/beneficiario.png"))); // NOI18N

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/q.png"))); // NOI18N

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/aceptar.png"))); // NOI18N
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/limpiar.png"))); // NOI18N
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbanco, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cchequera, 0, 244, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel12)
                                .addComponent(jLabel11)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtlugar)
                            .addComponent(jtben, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
                            .addComponent(jtmonto))))
                .addGap(0, 211, Short.MAX_VALUE))
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(cbanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(cchequera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtlugar))
                .addGap(37, 37, 37)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtben))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jtmonto, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane4.addTab("Crear Cheques", jPanel13);

        tcheque.setModel(new javax.swing.table.DefaultTableModel(
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
        tcheque.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tchequeMouseEntered(evt);
            }
        });
        jScrollPane7.setViewportView(tcheque);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 976, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Lista de cheques", jPanel12);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1005, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 32, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane4)
        );

        jTabbedPane1.addTab("CHEQUES", new javax.swing.ImageIcon(getClass().getResource("/imagenes/cheque.png")), jPanel4); // NOI18N

        tabla_chequeras.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane6.setViewportView(tabla_chequeras);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 936, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 585, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 113, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Ver Chequeras", jPanel11);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 948, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 698, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("Habilitar Administracion ", jPanel10);

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/addchequera.png"))); // NOI18N
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel18MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel18MouseExited(evt);
            }
        });

        jsolicitarch.setText("Solicitar ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 953, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jsolicitarch))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jsolicitarch)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("CHEQUERAS", new javax.swing.ImageIcon(getClass().getResource("/imagenes/folder.png")), jPanel3); // NOI18N

        jPanel2.setBackground(new java.awt.Color(0, 51, 51));
        jPanel2.setForeground(new java.awt.Color(0, 204, 0));

        jmbanco.setBackground(new java.awt.Color(0, 255, 51));
        jmbanco.setFont(new java.awt.Font("Lucida Console", 1, 14)); // NOI18N
        jmbanco.setForeground(new java.awt.Color(51, 204, 0));
        jmbanco.setText("Nuevo Banco");

        tabla_historial.setBackground(new java.awt.Color(0, 51, 51));
        tabla_historial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_historialMouseClicked(evt);
            }
        });

        tabla_bancos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabla_bancos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tabla_bancosMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_bancos);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 911, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 78, Short.MAX_VALUE))
        );

        tabla_historial.addTab("BANCOS", jPanel6);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 906, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 5, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
        );

        tabla_historial.addTab("DEPOSTIOS - EGRESOS", jPanel5);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/banco2.png"))); // NOI18N
        jLabel4.setToolTipText("");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel4MouseExited(evt);
            }
        });

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bajabanco.png"))); // NOI18N
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel16MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel16MouseExited(evt);
            }
        });

        jbajabanco.setBackground(new java.awt.Color(204, 0, 51));
        jbajabanco.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jbajabanco.setForeground(new java.awt.Color(204, 0, 0));
        jbajabanco.setText("Baja Banco");

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/banco.png"))); // NOI18N
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel17MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel17MouseExited(evt);
            }
        });

        jdepositar.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jdepositar.setForeground(new java.awt.Color(0, 204, 0));
        jdepositar.setText("Depositar");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(tabla_historial, javax.swing.GroupLayout.PREFERRED_SIZE, 916, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jmbanco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbajabanco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jdepositar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(28, 28, 28))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(21, 21, 21))))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabla_historial)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jmbanco)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(117, 117, 117)
                .addComponent(jbajabanco)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jdepositar, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(154, 154, 154))
        );

        jTabbedPane1.addTab("BANCOS", new javax.swing.ImageIcon(getClass().getResource("/imagenes/banco1.png")), jPanel2); // NOI18N

        jTabbedPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTabbedPane2MouseEntered(evt);
            }
        });

        tabla_usuarios.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tabla_usuarios);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 909, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Lista de usuarios", jPanel7);

        tabla_ui.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(tabla_ui);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/editarusuario.png"))); // NOI18N
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(394, 394, 394)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 922, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("usuarios inactivos", jPanel8);

        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane5.setViewportView(jTable2);

        jPanel9.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 0, 717, 529));

        comborol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ninguno" }));
        comborol.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        comborol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comborolActionPerformed(evt);
            }
        });
        jPanel9.add(comborol, new org.netbeans.lib.awtextra.AbsoluteConstraints(745, 57, 174, -1));

        comboroles2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel9.add(comboroles2, new org.netbeans.lib.awtextra.AbsoluteConstraints(745, 135, 174, -1));

        rol1.setText("POR ROLES");
        rol1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rol1ActionPerformed(evt);
            }
        });
        jPanel9.add(rol1, new org.netbeans.lib.awtextra.AbsoluteConstraints(737, 7, 182, -1));

        direcc.setText("POR DIRECCION ");
        direcc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                direccActionPerformed(evt);
            }
        });
        jPanel9.add(direcc, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 510, 174, -1));

        comboroles1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ninguno" }));
        jPanel9.add(comboroles1, new org.netbeans.lib.awtextra.AbsoluteConstraints(745, 99, 174, -1));

        nombrejt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombrejtActionPerformed(evt);
            }
        });
        jPanel9.add(nombrejt, new org.netbeans.lib.awtextra.AbsoluteConstraints(188, 535, 435, 32));

        pornombre.setText("POR NOMBRE");
        pornombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pornombreActionPerformed(evt);
            }
        });
        jPanel9.add(pornombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 547, 174, -1));

        rol2.setText("Multiples Roles");
        rol2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rol2ActionPerformed(evt);
            }
        });
        jPanel9.add(rol2, new org.netbeans.lib.awtextra.AbsoluteConstraints(737, 33, -1, -1));
        jPanel9.add(cdireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 540, 260, 30));

        jTabbedPane2.addTab("CONSULTAS", jPanel9);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/add_user.png"))); // NOI18N
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/edit_user.png"))); // NOI18N
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar_usuario.png"))); // NOI18N
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 923, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(116, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(133, 133, 133))
        );

        jTabbedPane1.addTab("USUARIOS", new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuariocirc.png")), jPanel1); // NOI18N

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 1230, 730));
        jTabbedPane1.getAccessibleContext().setAccessibleName("Bancos");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cerrar1.png"))); // NOI18N
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1560, 760, 50, 40));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/validacion.png"))); // NOI18N
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(1590, 10, 30, 40));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/question.png"))); // NOI18N
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(1550, 670, 70, 60));

        tabla_val.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla_val.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_valMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tabla_valMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tabla_valMouseExited(evt);
            }
        });
        jScrollPane9.setViewportView(tabla_val);

        getContentPane().add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1390, 60, 230, -1));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/validaciones.png"))); // NOI18N
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel21MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(1550, 500, 70, 50));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/editarval.png"))); // NOI18N
        jLabel22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel22MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(1470, 500, 60, 50));

        jLabel23.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(51, 153, 0));
        jLabel23.setText("ROL:");
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 770, 100, 40));

        jrol.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jrol.setForeground(new java.awt.Color(51, 153, 0));
        jrol.setText("ROL");
        getContentPane().add(jrol, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 770, 380, 40));

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ver.png"))); // NOI18N
        jLabel24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel24MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 760, 60, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondo1.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -5, 1640, 820));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabla_historialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_historialMouseClicked
    ver();
// ver2();// TODO add your handling code here:
    }//GEN-LAST:event_tabla_historialMouseClicked
private void tchequeMouseEntered(java.awt.event.MouseEvent evt){
vercheque();
}
    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
Nuevo_banco nb = new Nuevo_banco ();
nb.setVisible(true);
nb.setLocationRelativeTo(null);
nb.setTitle("Nuevo Bacnco");
// TODO add your handling code here:
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
    
    this.dispose();
    System.exit (0);

// TODO add your handling code here:
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
Nuevo_usuario nu = new Nuevo_usuario ();
nu.setVisible(true);
nu.setLocationRelativeTo(null);
nu.setResizable(false);
// TODO add your handling code here:
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseEntered
  jmbanco.setVisible(true);
    }//GEN-LAST:event_jLabel4MouseEntered

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
elim_usuario eu = new elim_usuario ();
eu.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseExited
   jmbanco.setVisible(false);     // TODO add your handling code here:
    }//GEN-LAST:event_jLabel4MouseExited

    private void jTabbedPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane2MouseClicked
comboroles();        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane2MouseClicked

    private void rol1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rol1ActionPerformed
if(rol1.isSelected()){
comborol.setVisible(true);
rol2.setVisible(true);
direcc.setSelected(false);
pornombre.setSelected(false);
} else{
comborol.setVisible(false);
comboroles1.setVisible(false);
comboroles2.setVisible(false);
rol2.setVisible(false);
}
// TODO add your handling code here:
    }//GEN-LAST:event_rol1ActionPerformed

    private void comborolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comborolActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comborolActionPerformed

    private void rol2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rol2ActionPerformed
if(rol2.isSelected()){
comboroles1.setVisible(true);
comboroles2.setVisible(true);
direcc.setSelected(false);
pornombre.setSelected(false);
} else{
comboroles1.setVisible(false);
comboroles2.setVisible(false);
}        // TODO add your handling code here:
    }//GEN-LAST:event_rol2ActionPerformed

    private void nombrejtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombrejtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombrejtActionPerformed

    private void pornombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pornombreActionPerformed
if(pornombre.isSelected()){
nombrejt.setVisible(true);
rol1.setSelected(false);
direcc.setSelected(false);
rol2.setSelected(false);
}
else{
nombrejt.setVisible(false);

}// TODO add your handling code here:
    }//GEN-LAST:event_pornombreActionPerformed

    private void direccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_direccActionPerformed
       if(direcc.isSelected()){
            cdireccion.setVisible(true);
            rol1.setSelected(false);
            pornombre.setSelected(false);
            rol2.setSelected(false);
        }else{
        cdireccion.setVisible(false);
       
       }
// TODO add your handling code here:
    }//GEN-LAST:event_direccActionPerformed

    private void cbancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbancoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbancoActionPerformed

    private void cbancoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbancoMouseClicked
combobancos();        // TODO add your handling code here:
    }//GEN-LAST:event_cbancoMouseClicked

    private void cchequeraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cchequeraMouseClicked
String nombre_banco = cbanco.getSelectedItem().toString();
        combochequeras(nombre_banco);        // TODO add your handling code here:
    }//GEN-LAST:event_cchequeraMouseClicked

    private void jtmontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtmontoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtmontoActionPerformed

    private void jLabel16MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseEntered
jbajabanco.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel16MouseEntered

    private void jLabel16MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseExited
jbajabanco.setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel16MouseExited

    private void jLabel17MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseEntered
jdepositar.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel17MouseEntered

    private void jLabel17MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseExited
jdepositar.setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel17MouseExited

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked


        try {
            String l = jtlugar.getText();
            String b = jtben.getText();
            int mon = Integer.parseInt(jtmonto.getText());
            int i = Integer.parseInt(cchequera.getSelectedItem().toString());
                    crear_cheques(i,l,b,mon);
                    limpiarcheque();
        } catch (Exception e) {
            DesktopNotify.showDesktopMessage("Error", "En el Campo de monto Solo pueden ingresar Numeros.", DesktopNotify.ERROR);
                    
        }


        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel14MouseClicked
public void limpiarcheque(){
                    jtlugar.setText("");
                    jtben.setText("");
                    jtmonto.setText("");
}
    private void cchequeraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cchequeraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cchequeraActionPerformed

    private void jTabbedPane4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane4MouseClicked
vercheque ();        // TODO add your handling code here:
ver_val();
vertabla_validaciones();
inicios();
    }//GEN-LAST:event_jTabbedPane4MouseClicked

    private void jLabel18MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseEntered
jsolicitarch.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel18MouseEntered

    private void jLabel18MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseExited
jsolicitarch.setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel18MouseExited

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
depositar d = new depositar ();
d.setVisible(true);
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel17MouseClicked

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
    int aa = this.getExtendedState();
        if (aa == 0){
            this.setExtendedState(6);
}    else
{
    this.setExtendedState(0);
}
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel19MouseClicked

    private void jTabbedPane1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseEntered
ver ();
ver2();// TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane1MouseEntered

    private void tabla_bancosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_bancosMouseEntered
ver ();
ver2();// TODO add your handling code here:
    }//GEN-LAST:event_tabla_bancosMouseEntered

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
schequera sc = new schequera();
sc.setVisible(true);
// TODO add your handling code here:
    }//GEN-LAST:event_jLabel18MouseClicked

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
  ver_chequera();      // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
limpiarcheque();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel15MouseClicked

    private void jtlugarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtlugarMouseClicked
jtlugar.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_jtlugarMouseClicked

    private void jtbenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbenMouseClicked
jtben.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_jtbenMouseClicked

    private void jtmontoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtmontoMouseClicked
jtmonto.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_jtmontoMouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
editar_usuario ed = new editar_usuario ();
ed.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jTabbedPane2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane2MouseEntered
verusuariosinactivos();        // TODO add your handling code here:
verusuarios();
    }//GEN-LAST:event_jTabbedPane2MouseEntered

    private void tabla_valMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_valMouseEntered
vertabla_validaciones();        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_valMouseEntered

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
//validaciones
validacion vl = new validacion();
vl.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel21MouseClicked

    private void jLabel22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel22MouseClicked
mvalidacion mv = new mvalidacion();
mv.setVisible(true);
// TODO add your handling code here:
    }//GEN-LAST:event_jLabel22MouseClicked

    private void tabla_valMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_valMouseClicked
vertabla_validaciones();        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_valMouseClicked

    private void tabla_valMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_valMouseExited
vertabla_validaciones();        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_valMouseExited

    private void jLabel24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel24MouseClicked
personalizado p1 = new personalizado ();//Vista especial para validaciones segun rol
p1.setVisible(true);
// TODO add your handling code here:
    }//GEN-LAST:event_jLabel24MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
Modificarusuario mu = new Modificarusuario();
mu.setVisible(true);
mu.setLocationRelativeTo(null);
// TODO add your handling code here:
    }//GEN-LAST:event_jLabel7MouseClicked

    public void crear_cheques(int idbanco,String lugar,String Ben,int monto){
          try{  cn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE",usuario,cont);
                s=cn.createStatement();
                rs=s.executeQuery(" begin "
                                 +" administrador.crear_cheque ("+idbanco+", '"+lugar+"','"+Ben+"',"+monto+");"
                                + " end;");
                DesktopNotify.showDesktopMessage("Cheque Creado con Éxito", " Revise la validacion del Cheque ", DesktopNotify.SUCCESS);    
                cn.close();
          }
          catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            
        }
}
    public void comboroles()
    {
        try{
            cn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE",usuario,cont);
            s=cn.createStatement();
            rs=s.executeQuery("select nombre from roles");
            comborol.removeAllItems();
            comboroles1.removeAllItems();
            comboroles2.removeAllItems();
            comboroles2.addItem("Ninguno");
            comboroles1.addItem("Ninguno");
            while(rs.next()){
            comborol.addItem(rs.getString(1));
            comboroles1.addItem(rs.getString(1));
            comboroles2.addItem(rs.getString(1));
            }
            cn.close();
         
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            
        }
    }
    public void combobancos()
    {
        try{
            cn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","ADMINISTRADOR","12345");
            s=cn.createStatement();
            rs=s.executeQuery("select nombre from cuenta_bancaria where saldo > 0");
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
            
            cn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","ADMINISTRADOR","12345");
            s=cn.createStatement();
            rs=s.executeQuery("select chequeras.id_chequera as No_chequera  from chequeras " +
                              "inner join  cuenta_bancaria on chequeras.id_cuentasb = cuenta_bancaria.id_cuentab " +
                               "where nombre = '"+nombre_banco+"'");
            cchequera.removeAllItems();
            
            while(rs.next()){
            cchequera.addItem(rs.getString(1));
            
            }
            cn.close();
         
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            
        }
    }
    
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
            java.util.logging.Logger.getLogger(P_administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(P_administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(P_administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(P_administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new P_administrador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable HISTORIAL_CHE;
    private javax.swing.JComboBox<String> cbanco;
    private javax.swing.JComboBox<String> cchequera;
    private javax.swing.JTextField cdireccion;
    private javax.swing.JComboBox<String> comborol;
    private javax.swing.JComboBox<String> comboroles1;
    private javax.swing.JComboBox<String> comboroles2;
    private javax.swing.JCheckBox direcc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel jbajabanco;
    private javax.swing.JLabel jdepositar;
    private javax.swing.JLabel jmbanco;
    private javax.swing.JLabel jrol;
    private javax.swing.JLabel jsolicitarch;
    private javax.swing.JTextField jtben;
    private javax.swing.JTextField jtlugar;
    private javax.swing.JTextField jtmonto;
    private javax.swing.JTextField nombrejt;
    private javax.swing.JCheckBox pornombre;
    private javax.swing.JCheckBox rol1;
    private javax.swing.JCheckBox rol2;
    private javax.swing.JTable tabla_bancos;
    private javax.swing.JTable tabla_chequeras;
    private javax.swing.JTabbedPane tabla_historial;
    private javax.swing.JTable tabla_ui;
    private javax.swing.JTable tabla_usuarios;
    private javax.swing.JTable tabla_val;
    private javax.swing.JTable tabla_validaciones;
    private javax.swing.JTable tcheque;
    private javax.swing.JLabel uscn;
    // End of variables declaration//GEN-END:variables
}
