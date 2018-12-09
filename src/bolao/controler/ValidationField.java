/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolao.controler;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author RAFAELDEOLIVEIRABAHI
 */
public abstract class ValidationField {

    private static boolean validation = true;
    public static List<String> resultFields = new ArrayList<>();


    public static boolean execute() {
        validation = true;
        
        validationEmpty();
        validationSecurity();

        resultFields.removeAll(resultFields);

        return validation;
    }

    private static void validationEmpty() {
        for (String resultField : resultFields) {
            if (resultField.equals("")) {
                validation = false;
                break;
            }
        }
    }

    private static void validationSecurity() {
        for (String resultField : resultFields) {
            if (resultField.contains(";") || resultField.contains("'") || resultField.contains(",")) {
                validation = false;
                break;
            }
        }
    }
}
