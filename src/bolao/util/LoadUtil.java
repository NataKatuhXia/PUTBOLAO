/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolao.util;

import bolao.model.bean.Equipe;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe para fazer o carregamento do Arquivo Json no Sistema
 *
 * @author RAFAELDEOLIVEIRABAHI
 */
public class LoadUtil {

    /**
     * Classe que carregar uma lista de um Tipo de Classe a partir de um Json
     *
     * @param <T>, o array do Tipo da Classe que ele deverá devolver
     * @param fileName, o nome do arquivo Json que está no projeto
     * @param classOfT, o nome da Classe para qual o Json será transformando
     * @return, a lista do Tipo da Classe que foi solicitada
     */
    public static <T extends Object> List<T> loadListObjects(String fileName, Class<T[]> classOfT) {
        List<T> objects = null;

        try {
            objects = Arrays.asList(new Gson().fromJson(LoadUtil.readFile(fileName + ".json", Charset.defaultCharset()), classOfT));
        } catch (JsonSyntaxException | IOException ex) {
            Logger.getLogger(Equipe.class.getName()).log(Level.SEVERE, null, ex);
            return objects;
        }

        return objects;
    }

    /**
     * Classe para abrir e realizar a leitura do Json
     *
     * @param path, o nome do arquivo Json
     * @param encoding
     * @return, retorna em String o Json lido
     * @throws IOException
     */
    public static String readFile(String path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    /**
     * Classe para escrever em um arquivo json
     *
     * @param <E>, uma coleção de Objetos que será gravado no arquivo
     * @param nomeArquivo, o nome do arquivo que será escrito
     * @param lista, o nome da Coleção de Objetos
     * @throws IOException
     */
    public static <E> void writerFile(String nomeArquivo, Collection<E> lista) throws IOException {
        Gson gson = new Gson();
        FileWriter writeFile = null;
        String json = gson.toJson(lista);
        try {
            writeFile = new FileWriter(nomeArquivo);
            //Escreve no arquivo conteudo do Objeto JSON
            writeFile.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        writeFile.close();
    }
}
