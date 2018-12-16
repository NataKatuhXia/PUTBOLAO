/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolao.util;

import static bolao.util.GetProperties.PROP;
import bolao.view.apostador.TelaPalpites;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe para validar os textos que os usuários preenchem nas Views. Ela possui
 * um atributo estático que saõ utilizados pelas Views que necessitam dessas
 * validações
 *
 * @author RAFAELDEOLIVEIRABAHI
 */
public abstract class ValidationField {

    private static boolean validation = true;
    public static List<String> resultFields = new ArrayList<>();

    /**
     * Método público chamado pela maioria dos JButonn das Views que chamam os
     * métodos: Validação do campo vazio; Validação de de caracter especial nos
     * textos
     *
     * @return da Boolean validação para caso os textos tenham sido preenchidos
     * da maneira correta, ele retorna verdadeiro
     */
    public static boolean execute() {
        validation = true;

        validationEmpty();
        validationSecurity();

        resultFields.removeAll(resultFields);

        return validation;
    }

    /**
     * Validação de algum campo da View está vazia
     */
    private static void validationEmpty() {
        for (String resultField : resultFields) {
            if (resultField.equals("")) {
                validation = false;
                break;
            }
        }
    }

    /**
     * Validação para verificar se os textos possuem um caracter especial
     */
    private static void validationSecurity() {
        for (String resultField : resultFields) {
            if (resultField.contains(";") || resultField.contains("'") || resultField.contains(",")) {
                validation = false;
                break;
            }
        }
    }

    /**
     * Validação do placar, valida se o placar preenchido pelo usuário, está no
     * formato correto
     *
     * @see TelaPalpites
     * @param placar, o texto que vem do JTextField preenchido pelo usuário na
     * View de Palpites
     * @return booleano, após a validação do placar, se estiver no padrão
     * correto, NumeroxNumero e se esta no intervalo correto 0 < Numero < Numero
     * maximo de Gols
     */
    public static boolean validationPlacar(String placar) {
        boolean validacao = true;

        try {
            if (placar.contains("x") && (!placar.contains(" "))) {
                String[] array = placar.split("x");
                Integer placarA = Integer.parseInt(array[0]);
                Integer placarB = Integer.parseInt(array[1]);
                int max_gols = Integer.parseInt(PROP.getProperty("MAX_GOLS"));
                if (placarA > max_gols || placarB > max_gols || placarA < 0 | placarB < 0) {
                    validacao = false;
                }

            } else {
                validacao = false;
            }
        } catch (NumberFormatException e) {
            validacao = false;
        }

        return validacao;
    }
}
