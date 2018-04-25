/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import yahoofinance.Stock;
import API.ApiTrader;
import controlers.AccionsController;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.JOptionPane;

import static java.lang.Thread.sleep;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.table.DefaultTableModel;
import models.Accion;

/**
 *
 * @author kamii
 */
public class Inicio extends javax.swing.JFrame implements Runnable {

    //..................................................................................................................
    // ATRIBUTOS........................................................................................................
    //..................................................................................................................

    // CONTROLADORAS
    private AccionsController ac;

    // CLIENTE ID
    private String cliente;

    // CONTROLADORA API
    private ApiTrader apiTrader;

    // ATRIBUTOS DEL HILO QUE REFRESCA LA API.
    private int miliseconds;
    private Thread apiRefresherThread;

    //..................................................................................................................
    // FIN ATRIBUTOS....................................................................................................
    //..................................................................................................................



    //..................................................................................................................
    // CONSTRUCTORES....................................................................................................
    //..................................................................................................................

    // CONSTRUCTOR POR DEFECTO
    public Inicio() {

        ac= new AccionsController();
        cliente="cami";
        try {
            apiTrader = new ApiTrader(/*"GAM.MC",*//*"POP.MC",*/"ABE.MC","ELE.MC","ANA.MC","MTS.MC","ITX.MC","REE.MC","DIA.MC","TEF.MC","SAN.MC","VIS.MC","IBE.MC","GAS.MC","MAP.MC","BKT.MC","ACS.MC","GRF.MC","MRL.MC",
                    "ACX.MC","SAB.MC","AMS.MC","CABK.MC","FER.MC","BBVA.MC","ENG.MC","CLNX.MC","AENA.MC","TRE.MC","FCC.MC");
        } catch (IOException e) {
            e.printStackTrace();
        }
        initComponents(); // GUI INIT
        jComboBox1.disable();
        actualizarControles();
        this.setLocationRelativeTo(null);
        setResizable(false);
        miliseconds = 300000; // 300 seg / 5 min
        apiRefresherThread = new Thread(this);
        apiRefresherThread.start();
    }


    // CONSTRUCTOR POR PARAMETRO.
    public Inicio(String cli) {
        //jTextField9.enable();
        //jTextField11.enable();
        //jTextField1.enable();
        ac= new AccionsController();
        cliente=cli;
        try {
            apiTrader = new ApiTrader(/*"GAM.MC",*//*"POP.MC",*/"ABE.MC","ELE.MC","ANA.MC","MTS.MC","ITX.MC","REE.MC","DIA.MC","TEF.MC","SAN.MC","VIS.MC","IBE.MC","GAS.MC","MAP.MC","BKT.MC","ACS.MC","GRF.MC","MRL.MC",
                    "ACX.MC","SAB.MC","AMS.MC","CABK.MC","FER.MC","BBVA.MC","ENG.MC","CLNX.MC","AENA.MC","TRE.MC","FCC.MC");
        } catch (IOException e) {
            e.printStackTrace();
        }

        initComponents(); // GUI INIT
        jTextField10.enable();
        actualizarControles();
        this.setLocationRelativeTo(null);
        setResizable(false);
        miliseconds = 300000;  // 300 seg / 5 min
        apiRefresherThread = new Thread(this);
        apiRefresherThread.start();
    }


    // CONSTRUCTOR GUI
    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Actions"));
        jPanel2.setToolTipText("");
        jPanel2.setName("Actions"); // NOI18N

        jLabel3.setText("Company");

        jTextField2.setEditable(false);

        jLabel4.setText("Transaction");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Buy", "Sell" }));

        jLabel5.setText("Price");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Market", "Below", "Above" }));
        jComboBox2.setEnabled(false);

        jLabel6.setText("Quantity");

        jTextField3.setText("0");
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField3KeyReleased(evt);
            }
        });

        jRadioButton1.setText("Stop Loss");
        jRadioButton1.setEnabled(false);

        jRadioButton2.setText("Take Profit");
        jRadioButton2.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Total");

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("Accept");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextField1.setText("%");
        jTextField1.setEnabled(false);

        jTextField9.setText("%");
        jTextField9.setEnabled(false);

        jTextField10.setText("$");
        jTextField10.setEnabled(false);

        jTextField11.setText("$");
        jTextField11.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField11)
                                    .addComponent(jTextField10, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)))
                            .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jRadioButton1)
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton2)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel2.setText("Identificador de cliente:");

        jLabel8.setText("jLabel8");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(113, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel8))
                .addGap(21, 21, 21))
        );

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("History"));
        jScrollPane1.setToolTipText("");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "COMPANY", "PRICE", "HIGH", "LOW", "CHANGE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(26);
        jTable1.setShowHorizontalLines(false);
        jTable1.setShowVerticalLines(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "COMPANY", "PRICE", "QUANTITY", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setRowHeight(26);
        jTable2.setShowHorizontalLines(false);
        jTable2.setShowVerticalLines(false);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("BOLSA");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(13, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(351, 351, 351)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 646, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel2.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }

    //..................................................................................................................
    // FIN CONSTRUCTORES................................................................................................
    //..................................................................................................................



    //..................................................................................................................
    // EVENTOS..........................................................................................................
    //..................................................................................................................

    //ONCLICK TABLA API
    private void jTable1MouseKeyReleased(java.awt.event.MouseEvent evt) {
        jTable2.getSelectionModel().clearSelection();
        int fila = jTable1.getSelectedRow(); // FILA
        String company = (String)jTable1.getModel().getValueAt(fila, 0); // NOMBRE
        BigDecimal price = (BigDecimal)jTable1.getModel().getValueAt(fila, 1); // PRECIO
        mostrarDatos(company,"1",price.toString(),"Market","Buy"); // LLENA EL FORM
    }


    //ONCLICK TABLA CLIENTE
    //FAlta obtener price de la api
    private void jTable2MouseKeyReleased(java.awt.event.MouseEvent evt) {
        jTable1.getSelectionModel().clearSelection();
        int fila = jTable2.getSelectedRow();
        String company = (String)jTable2.getModel().getValueAt(fila, 0);
        BigDecimal qua = new BigDecimal((String.valueOf(jTable2.getModel().getValueAt(fila, 2))));
        BigDecimal price = apiTrader.getPriceByCompany(company);
        mostrarDatos(company,String.valueOf(qua),String.valueOf(price.multiply(qua)),"Market","Sell");   
    }


    // CLICK RELEASED TEXTBOX CANTIDAD
    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {
        String company = jTextField2.getText();
        int quantity =  Integer.valueOf(((String)jTextField3.getText()).equals("") ? "0" :(String)jTextField3.getText());
        BigDecimal price = apiTrader.getPriceByCompany(company);
        jTextField8.setText(String.valueOf(price.multiply(new BigDecimal(quantity))));
    }


    // BOTON ACEPTAR CLICK EVENTO
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {

        String company = jTextField2.getText();
        int quantity =  Integer.valueOf( ((String)jTextField3.getText()).equals("") ? "0" :(String)jTextField3.getText());

        if(company.equals("") || quantity <= 0 || jTextField3.getText().equals("") ){
             showMessageDialog(null, "Debe completar Compania y Cantidad");
        }
        else{
            BigDecimal price = apiTrader.getPriceByCompany(company);
            //Todavia no se usa String priceType = (String)jComboBox2.getSelectedItem();//MARKET ,BELOW ,ABOVE
            String transaction = (String)jComboBox1.getSelectedItem();//BUY, SELL
            if(mensajeConfirmacion(transaction, company, price, quantity)){
                BigDecimal stopLoss = ((String)jTextField10.getText()).equals("") ? null :new BigDecimal((String)jTextField10.getText());

                if(transaction.equals("Buy")){
                    // ACA ES DONDE REDONDEA EL PRECIO DE LAS ACCIONES , CAMBIAR A BIGDECIMAL EL ATRIBUTO PRICE Y HACER QUE LA TABLA LO ACEPTE.
                    //QU PASA SI QUIERO VENDER UNA ACCION QUE COMPRE CON STOPLOSS?PUEDO?
                    ac.comprarAccion(cliente, company, price,quantity,stopLoss);
                } else if(transaction.equals("Sell")){
                    price = new BigDecimal((String.valueOf(jTable2.getModel().getValueAt(jTable2.getSelectedRow(), 1))));
                    if(quantity <= (ac.traerAccion(cliente, company, price)).getQuantity()){
                        quantity = quantity * -1; // YO NO PUEDO CREER LA MARAVILLA QUE ACABO DE HACER EN UNA LINEA.TODO MAL
                        ac.comprarAccion(cliente, company, price, quantity,stopLoss);
                    }else{
                         showMessageDialog(null, "No tiene suficientes acciones para vender.");
                    }
                        
                }
            }
            //VACIAR EL FORM = MOSTRAR DATOS VACIOS al comprar o vender?
            mostrarDatos("","","","Buy","Market");
            actualizarControles();
        }        
    }

    //..................................................................................................................
    // FIN EVENTOS......................................................................................................
    //..................................................................................................................
    
    public boolean mensajeConfirmacion(String transaction, String company,BigDecimal price, int quantity){
        return(JOptionPane.showConfirmDialog(null,"¿Confirma la siguiente "+(transaction.equals("Buy")?"Compra":"Venta")+"?\n\n"+
                " | Company: "+company+"\n | Price: $"+price+ "\n | Quantity: "+quantity+"\n | Total: $"+jTextField8.getText()    
                        , "", JOptionPane.YES_NO_OPTION)==0); 
    }

    public void mostrarDatos(String company, String quantity, String total, String price, String transaction){
        jTextField2.setText(company);
        jTextField3.setText(quantity);
        jTextField8.setText(total);
        jComboBox2.setSelectedItem(price);
        jComboBox1.setSelectedItem(transaction);
        
        jTextField9.setText("");
        jTextField10.setText("");
        jTextField11.setText("");
        jTextField1.setText("");
        
    }

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
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicio().setVisible(true);
            }
        });
    }
    private void actualizarControles(){
            jLabel8.setText(cliente);
            setTablaAPI();
            setTablaCliente();
    }


    //********************************************************************************************************
    // SETTERS DE LAS TABLAS *********************************************************************************
    //********************************************************************************************************


    private void setTablaAPI() {
        List<Stock> acciones = apiTrader.getMyExchanges();
        jTable1.getTableHeader().setReorderingAllowed(false);
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(acciones.size());
        int fila = 0;
        for(Stock s:acciones){
            jTable1.getModel().setValueAt(s.getName(), fila, 0);
            jTable1.getModel().setValueAt(s.getQuote().getPrice(), fila, 1);
            jTable1.getModel().setValueAt(s.getQuote().getDayHigh(), fila, 2);
            jTable1.getModel().setValueAt(s.getQuote().getDayLow() , fila, 3);
            jTable1.getModel().setValueAt(s.getQuote().getChange(), fila, 4);
            fila++;
        }
    }

    // SET TABLA CLIENTE
    private void setTablaCliente() {
        try {
            List<Accion> accionesCliente = ac.traerAccionesCliente(cliente);
            jTable2.getTableHeader().setReorderingAllowed(false);
            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            model.setRowCount(accionesCliente.size());
            int fila = 0;
            for(Accion a:accionesCliente){
                jTable2.getModel().setValueAt(a.getCompany(), fila, 0);
                jTable2.getModel().setValueAt(a.getPrice(), fila, 1);
                jTable2.getModel().setValueAt(a.getQuantity(), fila, 2);
                jTable2.getModel().setValueAt((a.getPrice().multiply(new BigDecimal(a.getQuantity()))) , fila, 3);
               // jTable2.getModel().setValueAt(a.getCompany() , fila, 3);
                fila++;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    //********************************************************************************************************
    // FIN SETTERS DE LAS TABLAS *****************************************************************************
    //********************************************************************************************************
    
    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;


    @Override
    public void run() {
        while(true){
            try {
                sleep(miliseconds);
                System.out.println("REFRESCO");
                this.setTablaAPI();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
