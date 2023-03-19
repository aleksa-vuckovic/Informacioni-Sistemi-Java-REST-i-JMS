/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helper;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 *
 * @author Aleksa
 */
public class Helper {
    public static String format(String input) {
        int tabCount = 0;
        boolean insideVal = false;

        StringBuilder inputBuilder = new StringBuilder();
        char[] inputChar = input.toCharArray();

        for (int i = 0; i < inputChar.length; i++) {
            char c = inputChar[i];
            char p = i > 0 ? inputChar[i-1] : 0;
            char n = i < inputChar.length - 1 ? inputChar[i+1] : 0;
            
            if ((c == '}' || c == ']') && !insideVal) {
                tabCount--;
                if (inputChar[i-1] != '[' && inputChar[i-1] != '{')
                    inputBuilder.append(noviRed(tabCount));
            }
            
            if (c == ':' && !insideVal) inputBuilder.append(" : ");
            else inputBuilder.append(c);

            if ((c == '{' || c == '[') && !insideVal) {
                tabCount++;
                if (inputChar[i+1] == ']' || inputChar[i+1] == '}')
                    continue;

                inputBuilder.append(noviRed(tabCount));
            }

            if (c == ',') {
                inputBuilder.append(noviRed(tabCount));
            }
            
            if (c == '"') insideVal = !insideVal;
        }

        return inputBuilder.toString();
    }
    public static String kodiraj(String kime, String sifra) {
        String t = kime + ":" + sifra;
        byte[] kodirano = Base64.getEncoder().encode(t.getBytes(StandardCharsets.UTF_8));
        String res = new String(kodirano, StandardCharsets.UTF_8);
        return "Basic " + res;
    }
    private static String noviRed(int tabCount) {
        StringBuilder builder = new StringBuilder();

        builder.append("\n");
        for (int j = 0; j < tabCount; j++)
            builder.append("      ");

        return builder.toString();
    }
}
