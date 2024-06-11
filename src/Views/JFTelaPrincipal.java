/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views;

/**
 *
 * @author Vinicius
 */
public class JFTelaPrincipal extends javax.swing.JFrame {

    
    private JPClientes jPClientes;
    private JPReserva jPReserva;
    private JPQuarto jPQuarto;
    private JPPagamento jpPagamento;
    private JPAtender jpAtender;

    /**
     * Creates new form jFTelaPrincipal
     */
    public JFTelaPrincipal() {
        initComponents();
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
        jBCliente = new javax.swing.JButton();
        jBCliente1 = new javax.swing.JButton();
        jBCliente2 = new javax.swing.JButton();
        jBCliente3 = new javax.swing.JButton();
        jBAtender = new javax.swing.JButton();
        jPnlMain = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("fTelaPrincipal"); // NOI18N

        jPanel1.setBackground(new java.awt.Color(0, 153, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(150, 150));

        jBCliente.setText("Clientes");
        jBCliente.setBorder(null);
        jBCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBClienteActionPerformed(evt);
            }
        });

        jBCliente1.setText("Quartos");
        jBCliente1.setBorder(null);
        jBCliente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCliente1ActionPerformed(evt);
            }
        });

        jBCliente2.setText("Reservar");
        jBCliente2.setBorder(null);
        jBCliente2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCliente2ActionPerformed(evt);
            }
        });

        jBCliente3.setText("Pagamentos");
        jBCliente3.setBorder(null);
        jBCliente3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCliente3ActionPerformed(evt);
            }
        });

        jBAtender.setText("Atender");
        jBAtender.setBorder(null);
        jBAtender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAtenderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jBCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
            .addComponent(jBCliente1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jBCliente2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jBCliente3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jBAtender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jBCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBCliente2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBCliente3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jBAtender, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.LINE_START);

        jPnlMain.setLayout(new java.awt.GridLayout(1, 0));
        getContentPane().add(jPnlMain, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(773, 530));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBClienteActionPerformed
        // TODO add your handling code here:           
        jPnlMain.removeAll();
        if (jPClientes == null) {
            jPClientes = new JPClientes();                
        }
        jPnlMain.add(jPClientes);
        //
        jPnlMain.revalidate();
        jPnlMain.repaint();

    }//GEN-LAST:event_jBClienteActionPerformed

    private void jBCliente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCliente1ActionPerformed
        // TODO add your handling code here:
        jPnlMain.removeAll();        
        if (jPQuarto == null) {
            jPQuarto = new JPQuarto();                
        }
        jPnlMain.add(jPQuarto);
        //
        jPnlMain.revalidate();
        jPnlMain.repaint();        
    }//GEN-LAST:event_jBCliente1ActionPerformed

    private void jBCliente2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCliente2ActionPerformed
        // TODO add your handling code here:
        jPnlMain.removeAll();        
        if (jPReserva == null) {
            jPReserva = new JPReserva();                
        }
        jPnlMain.add(jPReserva);
        //
        jPnlMain.revalidate();
        jPnlMain.repaint();                
    }//GEN-LAST:event_jBCliente2ActionPerformed

    private void jBCliente3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCliente3ActionPerformed
        // TODO add your handling code here:
        jPnlMain.removeAll();        
        if (jpPagamento == null) {
            jpPagamento = new JPPagamento();                
        }
        jPnlMain.add(jpPagamento);
        //
        jPnlMain.revalidate();
        jPnlMain.repaint();  
    }//GEN-LAST:event_jBCliente3ActionPerformed

    private void jBAtenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAtenderActionPerformed
        // TODO add your handling code here:
        jPnlMain.removeAll();        
        if (jpAtender == null) {
            jpAtender = new JPAtender();                
        }
        jPnlMain.add(jpAtender);
        //
        jPnlMain.revalidate();
        jPnlMain.repaint(); 
    }//GEN-LAST:event_jBAtenderActionPerformed

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
            java.util.logging.Logger.getLogger(JFTelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFTelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFTelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFTelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFTelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBAtender;
    private javax.swing.JButton jBCliente;
    private javax.swing.JButton jBCliente1;
    private javax.swing.JButton jBCliente2;
    private javax.swing.JButton jBCliente3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPnlMain;
    // End of variables declaration//GEN-END:variables
}
