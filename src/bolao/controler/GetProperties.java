/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolao.controler;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author RAFAELDEOLIVEIRABAHI
 */
public class GetProperties {

    public static final Properties PROP = getProp();
    private static final String FILE = "./src/bolao/properties/arquivoProperties.properties";

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
