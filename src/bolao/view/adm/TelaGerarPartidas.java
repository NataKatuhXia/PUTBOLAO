/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolao.view.adm;

import bolao.controler.ControlTime;
import bolao.model.bean.Administrador;
import bolao.model.bean.Aposta;
import bolao.model.bean.Jogo;
import bolao.model.bean.User;
import bolao.model.dao.ApostaDAO;
import bolao.model.dao.JogoDAO;
import bolao.view.apostador.TelaListaAposta;
import java.util.List;
import java.util.ListIterator;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author RAFAELDEOLIVEIRABAHI
 */
public class TelaGerarPartidas extends javax.swing.JFrame {

    DefaultTableModel modelo;
    private JFrame parent;

    /**
     * Creates new form TelaGerarResultado
     */
    public TelaGerarPartidas(JFrame p) {
        this.parent = p;
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
        jButtonGerarPartida = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableUser = new javax.swing.JTable();
        imageLogo = new javax.swing.JLabel();
        imageFundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButtonGerarPartida.setText("Gerar novas Partidas");
        jButtonGerarPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGerarPartidaActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonGerarPartida, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, 160, 40));

        jTableUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Identificador", "Jogo", "Data"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableUser);
        if (jTableUser.getColumnModel().getColumnCount() > 0) {
            jTableUser.getColumnModel().getColumn(0).setMinWidth(50);
            jTableUser.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTableUser.getColumnModel().getColumn(0).setMaxWidth(50);
            jTableUser.getColumnModel().getColumn(1).setMinWidth(100);
            jTableUser.getColumnModel().getColumn(1).setPreferredWidth(100);
            jTableUser.getColumnModel().getColumn(1).setMaxWidth(100);
            jTableUser.getColumnModel().getColumn(2).setMinWidth(250);
            jTableUser.getColumnModel().getColumn(2).setPreferredWidth(250);
            jTableUser.getColumnModel().getColumn(2).setMaxWidth(250);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 590, 190));

        imageLogo.setIcon(new javax.swing.ImageIcon("view\\Logo.png"));
        jPanel1.add(imageLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(355, 0, 270, 190));

        imageFundo.setIcon(new javax.swing.ImageIcon("view\\ImageFundo.jpg"));
        jPanel1.add(imageFundo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 624, 410));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonGerarPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGerarPartidaActionPerformed
        // TODO add your handling code here:

        Administrador.generateNewPartidas();
        readJTable();

        TelaPrincipalAdministrador frame = (TelaPrincipalAdministrador) parent;
        frame.setInformacoes();// Atribui os valores atuais
        this.dispose();
        frame.setVisible(true);
    }//GEN-LAST:event_jButtonGerarPartidaActionPerformed

    private void readJTable() {

        modelo = (DefaultTableModel) jTableUser.getModel();
        modelo.setNumRows(0);
        JogoDAO jogodao = new JogoDAO();
        ApostaDAO apostadao = new ApostaDAO();

        List<Jogo> jogosAberto = jogodao.searchAll("Gerar Abertos para Usuario", null);

        for (ListIterator<Jogo> iterator = jogosAberto.listIterator(); iterator.hasNext();) {
            Jogo jogo = iterator.next();
            List<Aposta> apostas = apostadao.readForDesc(jogo.getIdentificador(), "A definir");
            for (Aposta aposta : apostas) {
                if (User.getPessoa().getUsuario().equals(aposta.getUsuario())) {
                    iterator.remove();
                    break;
                }
            }
        }

        int cont = 0;

        for (Jogo jogo : jogosAberto) {
            modelo.addRow(new Object[]{
                ++cont,
                jogo.getIdentificador(),
                ControlTime.parseTime(jogo.getIdentificador()),
                jogo.getData()
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaGerarPartidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaGerarPartidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaGerarPartidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaGerarPartidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaGerarPartidas(new JFrame()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imageFundo;
    private javax.swing.JLabel imageLogo;
    private javax.swing.JButton jButtonGerarPartida;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableUser;
    // End of variables declaration//GEN-END:variables
}
