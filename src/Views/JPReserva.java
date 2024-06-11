/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Views;

import Controller.AtendenteController;
import Controller.ClienteController;
import Controller.QuartosController;
import Controller.ReservasController;
import Models.Atendente;
import Models.Cliente;
import Models.Quarto;
import Utils.ComboItem;
import Models.Reserva;
import Views.FormState;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Vinicius
 */
public class JPReserva extends javax.swing.JPanel {

    /**
     * Creates new form JPClientes
     */
    private final ClienteController clienteController;
    private final ReservasController reservasController;
    private final AtendenteController atendenteController;
    private final QuartosController quartosController;

    private FormState formState;

    public JPReserva() {
        initComponents();
        this.reservasController = new ReservasController();
        this.clienteController = new ClienteController();
        this.atendenteController = new AtendenteController();
        this.quartosController = new QuartosController();

                
        this.formState = FormState.SEARCH;
        //
        atualizarTabela();        
        updateButtons();
        //
        addListenerSelectionTable();
    }
    
    public void addListenerSelectionTable() {
        jTableReserva.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!jTableReserva.getSelectionModel().isSelectionEmpty()) {
                    updateEdits();
                }
            }
        });
    }
    
    public void updateButtons() {
        if (FormState.SEARCH == formState) {
            jBNovo.setEnabled(true);
            jBEditar.setEnabled(true);
            jBCancelar.setEnabled(false);
            jBGravar.setEnabled(false); 
        } else if (FormState.INSERT == formState) {
            jBNovo.setEnabled(false);
            jBEditar.setEnabled(false);
            jBCancelar.setEnabled(true);
            jBGravar.setEnabled(true);
        } else if (FormState.EDIT == formState) {
            jBNovo.setEnabled(false);
            jBEditar.setEnabled(false);
            jBCancelar.setEnabled(true);
            jBGravar.setEnabled(true);            
        }
    }
    
    public void updateEdits() {        
        jTFCodigo.setText("");
        //jCCliente
        //jCAtendente
        //jCQuarto
        jTFNumHospedes.setText("");
        jTFDataChekIn.setText("");
        jTFDataChekOut.setText("");
        //jCPagamento
        jTFValorPagamento.setText("");
        //JCPago
        
               
        Object cliente = jTableReserva.getValueAt(jTableReserva.getSelectedRow(), 1);
        Object atendente = jTableReserva.getValueAt(jTableReserva.getSelectedRow(), 2);
        Object quarto = jTableReserva.getValueAt(jTableReserva.getSelectedRow(), 3);
        Object numHospedes = jTableReserva.getValueAt(jTableReserva.getSelectedRow(), 4);
        Object dataCheckIn = jTableReserva.getValueAt(jTableReserva.getSelectedRow(), 5);
        Object dataCheckOut = jTableReserva.getValueAt(jTableReserva.getSelectedRow(), 6);
        Object tipoPagamento = jTableReserva.getValueAt(jTableReserva.getSelectedRow(), 7);
        Object valorPago = jTableReserva.getValueAt(jTableReserva.getSelectedRow(), 8);        
        Object pago = jTableReserva.getValueAt(jTableReserva.getSelectedRow(), 9);        
        //
        jTFCodigo.setText(jTableReserva.getValueAt(jTableReserva.getSelectedRow(), 0).toString());
        if (cliente != null) {
            jCCliente.setSelectedItem(cliente);            
        }
        if (atendente != null) {
            jCAtendente.setSelectedItem(atendente);            
        }
        if (quarto != null) {
            jCQuarto.setSelectedItem(quarto);
        }
        if (numHospedes != null) {
            jTFNumHospedes.setText(numHospedes.toString());
        }
        if (dataCheckIn != null) {
            jTFDataChekIn.setText(dataCheckIn.toString());
        }
        if (dataCheckOut != null) {
            jTFDataChekOut.setText(dataCheckOut.toString());
        }           
        if (tipoPagamento != null) {
            jCPagamento.setSelectedItem(tipoPagamento);
        } 
        if (valorPago != null) {
            jTFValorPagamento.setText(valorPago.toString());
        }
        if (pago != null) {
            JCPago.setSelectedItem(pago);
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

        jTFDataNasc = new javax.swing.JFormattedTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableReserva = new javax.swing.JTable();
        jBGravar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jBEditar = new javax.swing.JButton();
        jBNovo = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTFCodigo = new javax.swing.JTextField();
        jBCancelar = new javax.swing.JButton();
        jCQuarto = new javax.swing.JComboBox<>();
        jCAtendente = new javax.swing.JComboBox<>();
        jCCliente = new javax.swing.JComboBox<>();
        jCPagamento = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jTFDataChekIn = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTFDataChekOut = new javax.swing.JFormattedTextField();
        jTFNumHospedes = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        JCPago = new javax.swing.JComboBox<>();
        jTFValorPagamento = new javax.swing.JFormattedTextField();
        jLabel11 = new javax.swing.JLabel();

        try {
            jTFDataNasc.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jTFDataNasc.setToolTipText("");

        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(0, 153, 255));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Tela Reservas");
        jPanel1.add(jLabel4);

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setToolTipText("");
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTableReserva.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Num.", "Cliente", "Atendente", "Quarto", "Num Hospedes", "Data Chekin", "Data Checkout", "Pagamento", "Valor Pagamento", "Pago"
            }
        ));
        jTableReserva.setMinimumSize(new java.awt.Dimension(0, 0));
        jTableReserva.setShowGrid(false);
        jScrollPane1.setViewportView(jTableReserva);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 570, 170));

        jBGravar.setText("Gravar");
        jBGravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBGravarActionPerformed(evt);
            }
        });
        jPanel2.add(jBGravar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, 290, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Data ChekIn:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 100, -1, 20));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Atendente:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, -1, 20));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Cliente:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 50, 20));

        jBEditar.setText("Editar");
        jBEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEditarActionPerformed(evt);
            }
        });
        jPanel2.add(jBEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 100, -1));

        jBNovo.setText("Novo");
        jBNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBNovoActionPerformed(evt);
            }
        });
        jPanel2.add(jBNovo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Código:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 50, 20));

        jTFCodigo.setEditable(false);
        jTFCodigo.setToolTipText("");
        jPanel2.add(jTFCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 210, 30));

        jBCancelar.setText("Cancelar");
        jBCancelar.setActionCommand("");
        jBCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCancelarActionPerformed(evt);
            }
        });
        jPanel2.add(jBCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 100, -1));

        jCQuarto.setEditable(true);
        jCQuarto.setToolTipText("");
        jPanel2.add(jCQuarto, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, 110, 30));

        jCAtendente.setEditable(true);
        jCAtendente.setToolTipText("");
        jCAtendente.setEnabled(false);
        jPanel2.add(jCAtendente, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 110, 30));

        jCCliente.setEditable(true);
        jCCliente.setToolTipText("");
        jPanel2.add(jCCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, 110, 30));

        jCPagamento.setEditable(true);
        jCPagamento.setToolTipText("");
        jPanel2.add(jCPagamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 170, 110, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Pagamento:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 180, -1, 20));

        try {
            jTFDataChekIn.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jTFDataChekIn.setToolTipText("");
        jPanel2.add(jTFDataChekIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 90, 110, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Quarto:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, 20));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Data ChekOut:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 140, -1, 20));

        try {
            jTFDataChekOut.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jTFDataChekOut.setToolTipText("");
        jPanel2.add(jTFDataChekOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 130, 110, 30));

        try {
            jTFNumHospedes.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jTFNumHospedes.setToolTipText("");
        jPanel2.add(jTFNumHospedes, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 210, 110, 30));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Num. Hospedes:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, -1, 20));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Pago:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 220, -1, 20));

        JCPago.setEditable(true);
        JCPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Não", "Sim" }));
        JCPago.setToolTipText("");
        jPanel2.add(JCPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 210, 110, 30));

        jTFValorPagamento.setToolTipText("");
        jPanel2.add(jTFValorPagamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 250, 360, 30));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Valor Pagamento:");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, -1, 20));

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jBGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBGravarActionPerformed
     
        if (formState == FormState.INSERT) { 
            Object cliente = jCCliente.getSelectedItem();
            reservasController.registrarReserva(((ComboItem) cliente).getKey(),
                                                (Integer) jCAtendente.getSelectedItem(),
                                                (Integer) jCQuarto.getSelectedItem(),
                                                (Integer) jCPagamento.getSelectedItem(),
                                                Integer.valueOf(jTFNumHospedes.getText()),
                                                Double.valueOf(jTFValorPagamento.getText()));               
        } else if (formState == FormState.EDIT) {
           /*
            quartoController.editarQuarto(new Quarto(Integer.valueOf(jTFCodigo.getText()),
                    Integer.valueOf(jTFNumQuarto.getText()),
                    jCTamanho.getItemAt(jCTamanho.getSelectedIndex()).toCharArray()[0]));
*/
        }
        //
        
        atualizarTabela();
        //
        formState = FormState.SEARCH;
        updateButtons();
    }//GEN-LAST:event_jBGravarActionPerformed

    private void jBEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEditarActionPerformed
        // TODO add your handling code here:    
        updateEdits();
        //
        formState = FormState.EDIT;
        //
        updateButtons();
    }//GEN-LAST:event_jBEditarActionPerformed

    private void jBNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBNovoActionPerformed
        jTFCodigo.setText("");
        //jTFNumQuarto.setText("");        
        //
        ArrayList<Cliente> clientes = clienteController.consultarCliente();
        for (Cliente c : clientes) {            
            jCCliente.addItem(new ComboItem(c.getId(), c.getNome()));                         
        }
        
        ArrayList<Atendente> atendentes = atendenteController.acessarAtendente();
        for (Atendente a : atendentes) {            
            jCAtendente.addItem(new ComboItem(a.getId(), a.getNome()));                         
        }
        
        ArrayList<Quarto> quartos = quartosController.conultarQuartosVazios();
        for (Quarto q : quartos) {   
            Integer num = q.getNum();
            String aaa = num.toString();
            jCQuarto.addItem(new ComboItem(q.getId(), aaa ));                         
        }
        
        formState = FormState.INSERT;
        //
        updateButtons();
    }//GEN-LAST:event_jBNovoActionPerformed

    private void jBCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCancelarActionPerformed
        // TODO add your handling code here:       
        formState = FormState.SEARCH;
        //
        updateButtons();       
    }//GEN-LAST:event_jBCancelarActionPerformed

    private void atualizarTabela() {
        DefaultTableModel model = (DefaultTableModel) jTableReserva.getModel();
        model.setRowCount(0);
        ArrayList<Reserva> lista = reservasController.acessarReservas();

        for (Reserva r : lista) {
            Cliente c = clienteController.acessarCliente(r.getIdCliente());
            Atendente a = atendenteController.acessarAtendente(r.getIdAtendente());            
            //       
            Object[] dados = {r.getNum(), 
                                c.getId().toString()+" - "+c.getNome(), 
                                a.getId().toString()+" - "+a.getNome(),
                                r.getIdQuarto(),
                                r.getNumHospedes(),
                                r.getDataCheckin(),
                                r.getDataCheckout(),
                                r.getIdFormaPagamento(),
                                r.getValorPagamento(),
                                r.estaPago()};
            model.addRow(dados);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> JCPago;
    private javax.swing.JButton jBCancelar;
    private javax.swing.JButton jBEditar;
    private javax.swing.JButton jBGravar;
    private javax.swing.JButton jBNovo;
    private javax.swing.JComboBox<ComboItem> jCAtendente;
    private javax.swing.JComboBox<ComboItem> jCCliente;
    private javax.swing.JComboBox<String> jCPagamento;
    private javax.swing.JComboBox<ComboItem> jCQuarto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTFCodigo;
    private javax.swing.JFormattedTextField jTFDataChekIn;
    private javax.swing.JFormattedTextField jTFDataChekOut;
    private javax.swing.JFormattedTextField jTFDataNasc;
    private javax.swing.JFormattedTextField jTFNumHospedes;
    private javax.swing.JFormattedTextField jTFValorPagamento;
    private javax.swing.JTable jTableReserva;
    // End of variables declaration//GEN-END:variables
}