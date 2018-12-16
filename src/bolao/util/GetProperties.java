/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolao.util;

import bolao.view.adm.TelaPermissao;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Classe para realizar o manuseio do arquivo Properties do sistema
 *
 * @author RAFAELDEOLIVEIRABAHI
 */
public class GetProperties {

    public static final Properties PROP = getProp();
    private static final String FILE = "./src/bolao/properties/arquivoProperties.properties";

    /**
     * Realiza a leitura do arquivo Properties
     *
     * @return o arquivo Properties lido
     */
    public static Properties getProp() {
        Properties props = new Properties();
        try {
            FileInputStream file = new FileInputStream(FILE);
            props.load(file);
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return props;
    }

    /**
     * Serve para realizar alguma alteração no arquivo properties, utilizado na
     * definição dos parâmetros do sistema
     *
     * @see TelaPermissao
     * @param key, a chave do Properties que será alterada
     * @param value, o novo valor desse properties
     * @param comments, o comentário do porquê da alteração
     */
    public static void store(String key, String value, String comments) {

        PROP.setProperty(key, value);

        try {
            try (FileOutputStream file = new FileOutputStream(FILE)) {
                PROP.store(file, comments);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
