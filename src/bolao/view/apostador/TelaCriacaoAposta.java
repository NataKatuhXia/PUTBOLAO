/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolao.view.apostador;

import bolao.view.apostador.TelaPrincipalApostador;
import bolao.controler.ControlBolao;
import bolao.controler.ControlTime;
import static bolao.controler.GetProperties.PROP;
import bolao.model.bean.Aposta;
import bolao.model.bean.Apostador;
import bolao.model.bean.User;
import bolao.view.apostador.TelaListaAposta;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author RAFAELDEOLIVEIRABAHI
 */
public class TelaCriacaoAposta extends javax.swing.JFrame {

    /**
     * Creates new form TelaCriacaoAposta
     *
     * @param identificador // Identificador da partida
     */
    public TelaCriacaoAposta(String identificador, JFrame listagem, JFrame principal) {

        this.list = listagem;
        this.principal = principal;
        initComponents();
        String[] array = identificador.split(" x ");
        jLabelTimeA.setText(array[0]);
        jLabelTimeB.setText(array[1]);
    }

    public TelaCriacaoAposta(String identificador, JFrame listagem, JFrame principal, String resultado) {

        this.list = listagem;
        this.principal = principal;
        initComponents();
        String[] array = identificador.split(" x ");
        jLabelTimeA.setText(array[0]);
        jLabelTimeB.setText(array[1]);

        array = resultado.split("x");
        jSpinnerPlacarA.setValue(Integer.parseInt(array[0]));
        jSpinnerPlacarB.setValue(Integer.parseInt(array[1]));
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
        image = new javax.swing.JLabel();
        jLabelTimeA = new javax.swing.JLabel();
        jLabelTimeB = new javax.swing.JLabel();
        SpinnerModel smA = new SpinnerNumberModel(0, 0, Integer.parseInt(PROP.getProperty("MAX_GOLS")), 1); //default value,lower bound,upper bound,increment by
        jSpinnerPlacarA = new javax.swing.JSpinner(smA);
        SpinnerModel smB = new SpinnerNumberModel(0, 0, Integer.parseInt(PROP.getProperty("MAX_GOLS")), 1); //default value,lower bound,upper bound,increment by
        jSpinnerPlacarB = new javax.swing.JSpinner(smB);
        jLabel1 = new javax.swing.JLabel();
        jButtonSalvar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Criar Aposta");
        setIconImage(new ImageIcon("src\\view\\frame.png").getImage());
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        image.setIcon(new javax.swing.ImageIcon("src/view/Logo.png")); // NOI18N

        jLabelTimeA.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabelTimeA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTimeA.setText("Time A");

        jLabelTimeB.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabelTimeB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTimeB.setText("Time B");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("X");

        jButtonSalvar.setText("Salvar");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabelTimeA, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addComponent(jButtonSalvar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jSpinnerPlacarA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jSpinnerPlacarB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelTimeB, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(image, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(image, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTimeB)
                    .addComponent(jLabelTimeA)
                    .addComponent(jSpinnerPlacarA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinnerPlacarB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSalvar)
                    .addComponent(jButtonCancelar))
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        // TODO add your handling code here:

        String identificador = ControlTime.parseIdentificador(jLabelTimeA.getText(), jLabelTimeB.getText());

        if (ControlBolao.validationAposta(identificador, User.getPessoa().getUsuario())) {
            if (!User.getPessoa().isContaADM() || PROP.getProperty("ADM_APOSTA").equals("true")) {

                String placar = jSpinnerPlacarA.getValue().toString() + "x" + jSpinnerPlacarB.getValue().toString();

                Aposta aposta = new Aposta(User.getPessoa().getUsuario(), identificador, placar, "A definir");

                Apostador.createAposta(aposta, User.getPessoa());

                /* Atualiza a tela Principal, por conta da nova Aposta */
                TelaPrincipalApostador framePrincipalApostador = (TelaPrincipalApostador) principal;
                framePrincipalApostador.setInformacoes();// Atribui os valores atuais
                framePrincipalApostador.setVisible(true);

                /* Atualiza a lista da aposta, para acrescentar a nova aposta */
                TelaListaAposta frame = (TelaListaAposta) list;
                frame.setInformacoes();// Atribui os valores atuais
                frame.setVisible(true);

                this.dispose();

            } else {

                JOptionPane.showMessageDialog(null, "Você não tem permissão para fazer uma aposta!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Você já realizou uma aposta neste jogo!");
        }
    }//GEN-LAST:event_jButtonSalvarActionPerformed

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
            java.util.logging.Logger.getLogger(TelaCriacaoAposta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCriacaoAposta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCriacaoAposta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCriacaoAposta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCriacaoAposta("", new javax.swing.JFrame(), new javax.swing.JFrame()).setVisible(true);
            }
        });
    }

    private javax.swing.JFrame list;
    private javax.swing.JFrame principal;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel image;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelTimeA;
    private javax.swing.JLabel jLabelTimeB;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSpinner jSpinnerPlacarA;
    private javax.swing.JSpinner jSpinnerPlacarB;
    // End of variables declaration//GEN-END:variables
}
