/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolao.view.apostador;

import bolao.controler.ControlTime;
import bolao.model.bean.Aposta;
import bolao.model.bean.Apostador;
import bolao.model.bean.User;
import bolao.model.dao.ApostaDAO;
import bolao.model.dao.JogoDAO;
import bolao.util.ValidationField;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author RAFAELDEOLIVEIRABAHI
 */
public class TelaPalpites extends javax.swing.JFrame {

    DefaultTableModel modelo;
    JFrame parent;

    /**
     * Creates new form TelaGerarResultado
     */
    public TelaPalpites(javax.swing.JFrame p) {
        this.parent = p;
        initComponents();
        readJTable();
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
        jButtonBuscar = new javax.swing.JButton();
        jButtonAlterar = new javax.swing.JButton();
        jButtonExcluir = new javax.swing.JButton();
        jLabelIdentificador = new javax.swing.JLabel();
        jTextFieldIdentificador = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePalpite = new javax.swing.JTable();
        imageLogo = new javax.swing.JLabel();
        jLabelPlacar = new javax.swing.JLabel();
        jTextFieldPlacar = new javax.swing.JTextField();
        imageFundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Palpites");
        setIconImage(new ImageIcon("src\\view\\frame.png").getImage());
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButtonBuscar.setText("Buscar");
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, 160, 40));

        jButtonAlterar.setText("Alterar");
        jButtonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonAlterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 160, 160, 40));

        jButtonExcluir.setText("Excluir");
        jButtonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 160, 160, 40));

        jLabelIdentificador.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabelIdentificador.setForeground(new java.awt.Color(255, 255, 255));
        jLabelIdentificador.setText("Identificador:");
        jPanel1.add(jLabelIdentificador, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));
        jPanel1.add(jTextFieldIdentificador, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 150, -1));

        jTablePalpite.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Identificador", "Jogo", "Placar", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTablePalpite.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablePalpiteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTablePalpite);
        if (jTablePalpite.getColumnModel().getColumnCount() > 0) {
            jTablePalpite.getColumnModel().getColumn(0).setMinWidth(50);
            jTablePalpite.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTablePalpite.getColumnModel().getColumn(0).setMaxWidth(50);
            jTablePalpite.getColumnModel().getColumn(1).setMinWidth(100);
            jTablePalpite.getColumnModel().getColumn(1).setPreferredWidth(100);
            jTablePalpite.getColumnModel().getColumn(1).setMaxWidth(100);
            jTablePalpite.getColumnModel().getColumn(2).setMinWidth(250);
            jTablePalpite.getColumnModel().getColumn(2).setPreferredWidth(250);
            jTablePalpite.getColumnModel().getColumn(2).setMaxWidth(250);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 590, 190));

        imageLogo.setIcon(new javax.swing.ImageIcon("src/view/Logo.png")); // NOI18N
        jPanel1.add(imageLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(355, 0, 270, 190));

        jLabelPlacar.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabelPlacar.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPlacar.setText("Placar:");
        jPanel1.add(jLabelPlacar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, -1, -1));
        jPanel1.add(jTextFieldPlacar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 150, -1));

        imageFundo.setIcon(new javax.swing.ImageIcon("src/view/ImageFundo.jpg")); // NOI18N
        jPanel1.add(imageFundo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 624, 410));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed
        // TODO add your handling code here:

        String status = (String) jTablePalpite.getValueAt(jTablePalpite.getSelectedRow(), 4);

        if (jTablePalpite.getSelectedRow() != -1) {

            if (status.equals("A definir")) {

                new Apostador().deleteAposta((String) jTablePalpite.getValueAt(jTablePalpite.getSelectedRow(), 1));

                readJTable();

                TelaPrincipalApostador frame = (TelaPrincipalApostador) parent;
                frame.setInformacoes();
                frame.setVisible(true);

                this.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Este palpite não pode ser alterado, pois já tem resultado!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um palpite para Excluir");
        }
    }//GEN-LAST:event_jButtonExcluirActionPerformed

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
        // TODO add your handling code here:
        readJTableForDesc(jTextFieldIdentificador.getText(), User.getPessoa().getUsuario());
    }//GEN-LAST:event_jButtonBuscarActionPerformed

    public void readJTableForDesc(String nome, String usuario) {
        modelo = (DefaultTableModel) jTablePalpite.getModel();
        modelo.setNumRows(0);

        ApostaDAO apostadao = new ApostaDAO();

        int cont = 1;
        for (Aposta aposta : apostadao.readForUser(nome, usuario)) {
            modelo.addRow(new Object[]{
                cont++,
                aposta.getIdentificador(),
                ControlTime.parseTime(aposta.getIdentificador()),
                aposta.getPalpite(),
                aposta.getStatus()
            });
        }
    }
    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
        // TODO add your handling code here:
        String status = (String) jTablePalpite.getValueAt(jTablePalpite.getSelectedRow(), 4);

        if (jTablePalpite.getSelectedRow() != -1) {

            if (status.equals("A definir")) {

                if (new Apostador().modifyAposta((String) jTablePalpite.getValueAt(jTablePalpite.getSelectedRow(), 1), jTextFieldPlacar.getText())) {

                    readJTable();

                    TelaPrincipalApostador frame = (TelaPrincipalApostador) parent;
                    frame.setInformacoes();
                    frame.setVisible(true);

                    this.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Preencha o placar corretamente!");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Este palpite não pode ser alterado, pois já tem resultado!");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Selecione um palpite para alterar");
        }

    }//GEN-LAST:event_jButtonAlterarActionPerformed

    private void jTablePalpiteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePalpiteMouseClicked
        // TODO add your handling code here:
        jTextFieldIdentificador.setText((String) jTablePalpite.getValueAt(jTablePalpite.getSelectedRow(), 1));
        jTextFieldPlacar.setText((String) jTablePalpite.getValueAt(jTablePalpite.getSelectedRow(), 3));
    }//GEN-LAST:event_jTablePalpiteMouseClicked

    private void readJTable() {

        modelo = (DefaultTableModel) jTablePalpite.getModel();
        modelo.setNumRows(0);

        ApostaDAO apostadao = new ApostaDAO();
        List<Aposta> apostas = apostadao.readForUser(User.getPessoa().getUsuario());

        int cont = 1;
        for (Aposta aposta : apostas) {
            modelo.addRow(new Object[]{
                cont++,
                aposta.getIdentificador(),
                ControlTime.parseTime(aposta.getIdentificador()),
                aposta.getPalpite(),
                aposta.getStatus()

            });
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPalpites.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPalpites(new JFrame()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imageFundo;
    private javax.swing.JLabel imageLogo;
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JLabel jLabelIdentificador;
    private javax.swing.JLabel jLabelPlacar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablePalpite;
    private javax.swing.JTextField jTextFieldIdentificador;
    private javax.swing.JTextField jTextFieldPlacar;
    // End of variables declaration//GEN-END:variables
}
