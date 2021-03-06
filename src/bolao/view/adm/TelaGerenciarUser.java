/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolao.view.adm;

import bolao.model.bean.Administrador;
import bolao.model.bean.Pessoa;
import bolao.model.bean.User;
import bolao.model.dao.PessoaDAO;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author RAFAELDEOLIVEIRABAHI
 */
public class TelaGerenciarUser extends javax.swing.JFrame {

    DefaultTableModel modelo;

    /**
     * Creates new form TelaGerarResultado
     */
    public TelaGerenciarUser() {
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
        jTextFieldNome = new javax.swing.JTextField();
        jLabelUsuario1 = new javax.swing.JLabel();
        jTextFieldUser = new javax.swing.JTextField();
        jLabelUsuario = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableUsers = new javax.swing.JTable();
        imageLogo = new javax.swing.JLabel();
        imageFundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gerenciamento Usuários");
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

        jTextFieldNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNomeActionPerformed(evt);
            }
        });
        jPanel1.add(jTextFieldNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 150, -1));

        jLabelUsuario1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabelUsuario1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelUsuario1.setText("Usuario:");
        jPanel1.add(jLabelUsuario1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));
        jPanel1.add(jTextFieldUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 150, -1));

        jLabelUsuario.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabelUsuario.setForeground(new java.awt.Color(255, 255, 255));
        jLabelUsuario.setText("Nome:");
        jPanel1.add(jLabelUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));

        jCheckBox1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jCheckBox1.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setText("Conta Administrativa");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        jPanel1.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

        jTableUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Usuário", "Nome", "Pontos"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableUsers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableUsersMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableUsers);
        if (jTableUsers.getColumnModel().getColumnCount() > 0) {
            jTableUsers.getColumnModel().getColumn(0).setMinWidth(50);
            jTableUsers.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTableUsers.getColumnModel().getColumn(0).setMaxWidth(50);
            jTableUsers.getColumnModel().getColumn(1).setMinWidth(100);
            jTableUsers.getColumnModel().getColumn(1).setPreferredWidth(100);
            jTableUsers.getColumnModel().getColumn(1).setMaxWidth(100);
            jTableUsers.getColumnModel().getColumn(2).setMinWidth(250);
            jTableUsers.getColumnModel().getColumn(2).setPreferredWidth(250);
            jTableUsers.getColumnModel().getColumn(2).setMaxWidth(250);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 590, 190));

        imageLogo.setIcon(new javax.swing.ImageIcon("src/view/Logo.png")); // NOI18N
        jPanel1.add(imageLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(355, 0, 270, 190));

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
        if (jTableUsers.getSelectedRow() != -1) {
            if (User.getPessoa().isContaADM()) {

                new Administrador().deleteUser((String) jTableUsers.getValueAt(jTableUsers.getSelectedRow(), 1));
            }
            readJTable();
            jTextFieldNome.setText("");
            jTextFieldUser.setText("");
            jCheckBox1.setSelected(false);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um usuário para Excluir");
        }
    }//GEN-LAST:event_jButtonExcluirActionPerformed

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
        // TODO add your handling code here:
        readJTableForDesc(jTextFieldNome.getText(), jTextFieldUser.getText());
    }//GEN-LAST:event_jButtonBuscarActionPerformed

    public void readJTableForDesc(String nome, String usuario) {
        modelo = (DefaultTableModel) jTableUsers.getModel();
        modelo.setNumRows(0);
        PessoaDAO pessoadao = new PessoaDAO();

        int cont = 1;
        for (Pessoa pessoa : pessoadao.search(nome, usuario)) {
            modelo.addRow(new Object[]{
                cont++,
                pessoa.getUsuario(),
                pessoa.getNome(),
                pessoa.getPontos()
            });
        }
    }
    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
        // TODO add your handling code here:
        if (jTableUsers.getSelectedRow() != -1) {
            PessoaDAO pessoadao = new PessoaDAO();
            pessoadao.updateAccount(jTextFieldNome.getText(), jTextFieldUser.getText(), jCheckBox1.isSelected());
            jTextFieldNome.setText("");
            jTextFieldUser.setText("");
            jCheckBox1.setSelected(false);
            readJTable();

        } else {
            JOptionPane.showMessageDialog(null, "Selecione um usuário para alterar");
        }

    }//GEN-LAST:event_jButtonAlterarActionPerformed

    private void jTextFieldNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNomeActionPerformed

    private void jTableUsersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableUsersMouseClicked
        // TODO add your handling code here:
        jTextFieldNome.setText((String) jTableUsers.getValueAt(jTableUsers.getSelectedRow(), 2));
        jTextFieldUser.setText((String) jTableUsers.getValueAt(jTableUsers.getSelectedRow(), 1));
    }//GEN-LAST:event_jTableUsersMouseClicked

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void readJTable() {

        modelo = (DefaultTableModel) jTableUsers.getModel();
        modelo.setNumRows(0);
        PessoaDAO pessoadao = new PessoaDAO();
        List<Pessoa> pessoas = pessoadao.ranking();

        int cont = 1;
        for (Pessoa pessoa : pessoas) {
            modelo.addRow(new Object[]{
                cont++,
                pessoa.getUsuario(),
                pessoa.getNome(),
                pessoa.getPontos()
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
            java.util.logging.Logger.getLogger(TelaGerenciarUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new TelaGerenciarUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imageFundo;
    private javax.swing.JLabel imageLogo;
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabelUsuario;
    private javax.swing.JLabel jLabelUsuario1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableUsers;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldUser;
    // End of variables declaration//GEN-END:variables
}
