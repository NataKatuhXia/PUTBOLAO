/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolao.connection;

import bolao.model.bean.Aposta;
import bolao.model.bean.Pessoa;
import java.util.*;
import java.io.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.swing.JFrame;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RAFAELDEOLIVEIRABAHI
 */
public class MailApp {

    private static Session session;

    public static void sendMessages(List<Pessoa> pessoas) {
        MailApp.session = inicializarSecao();
        sendMessage(session, pessoas);

    }

    private static Session inicializarSecao() {
        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("putbolao@gmail.com", "putbolaotop");
            }
        });

        /**
         * Ativa Debug para sessão
         */
        session.setDebug(true);

        return session;
    }

    private static void sendMessage(Session session, List<Pessoa> pessoas) {
        try {
            // cria a mensagem
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("putbolao@gmail.com", "Não responda a esta mensagem."));

            for (Pessoa pessoa : pessoas) {
                msg.addRecipient(Message.RecipientType.BCC, new InternetAddress(pessoa.getEmail()));
            }

            msg.setSubject("Resultado do dia!");

            // anexa o arquivo no corpo da mensagem
            String html = "<html><body><div align='center'><img src='https://i.ibb.co/ySmxn8k/foto.jpg'></div></body></html>";
            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(html, "text/html");

            // cria a Multipart
            Multipart mp = new MimeMultipart();
            mp.addBodyPart(htmlPart);

            // adiciona a Multipart na mensagem
            msg.setContent(mp);

            // configura a data: cabecalho
            msg.setSentDate(new Date());

            // envia a mensagem
            Transport.send(msg);

        } catch (MessagingException mex) {
            mex.printStackTrace();
            Exception ex = null;
            if ((ex = mex.getNextException()) != null) {
                ex.printStackTrace();
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MailApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
