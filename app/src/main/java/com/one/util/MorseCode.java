package com.one.util;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kute
 * @date 2014年10月30日
 */
public class MorseCode {

    /**
     * clear text to morse code
     * @param text
     * @return
     */
    public static String toMorse(String text) {
        String morseCode = "";
        if(!isEmpty(text)) {
            text = text.toUpperCase();
            String[] textArray = text.split(SPACE_IN_WORD);
            for(String word : textArray) {
                morseCode += wordToMorseCode(word) + SPACE_BETWEEN_WORD;
            }
        }
        return morseCode.substring(0, morseCode.lastIndexOf(SPACE_BETWEEN_WORD));
    }

    /**
     * morse code to clear text
     * @param morseCode
     * @return
     */
    public static String fromMorse(String morseCode) {
        String text = "";
        if(!isEmpty(morseCode)) {
            String[] codeArray = morseCode.split(SPACE_BETWEEN_WORD);
            for(String wordCode : codeArray) {
                text += wordFromMorseCode(wordCode) + SPACE_IN_WORD;
            }
        }
        return text.substring(0, text.lastIndexOf(SPACE_IN_WORD)).toLowerCase();
    }



    private static final String SPACE_IN_WORD = " ";

    private static final String SPACE_BETWEEN_WORD = "   ";

    private static String wordToMorseCode(String word) {
        String wordMorseCode = "";
        String[] wordArray = word.split("");
        for(String letter : wordArray) {
            if(!isEmpty(letter)) {
                wordMorseCode += MORSE_CODE.get(letter)==null ? MORSE_CODE.get("NULL"):MORSE_CODE.get(letter) + SPACE_IN_WORD;
            }
        }
        return wordMorseCode.substring(0, wordMorseCode.lastIndexOf(SPACE_IN_WORD));
    }

    private static String wordFromMorseCode(String wordMorseCode) {
        String word = "";
        String[] codeArray = wordMorseCode.split(SPACE_IN_WORD);
        for(String code : codeArray) {
            for(Map.Entry<String, String> entry : MORSE_CODE.entrySet()) {
                if(entry.getValue().equals(code)) {
                    word += entry.getKey();
                    break;
                }
            }
        }
        return word;
    }

    private static final Map<String, String> MORSE_CODE = new HashMap<String, String>(){

        private static final long serialVersionUID = -8566980021074210328L;
        {
            put("A", ".-");
            put("B", "-...");
            put("C", "-.-.");
            put("D", "-..");
            put("E", ".");
            put("F", "..-.");
            put("G", "--.");
            put("H", "....");
            put("I", "..");
            put("J", ".---");
            put("K", "-.-");
            put("L", ".-..");
            put("M", "--");
            put("N", "-.");
            put("O", "---");
            put("P", ".--.");
            put("Q", "--.-");
            put("R", ".-.");
            put("S", "...");
            put("T", "-");
            put("U", "..-");
            put("V", "...-");
            put("W", ".--");
            put("X", "-..-");
            put("Y", "-.--");
            put("Z", "--..");
            put("1", ".----");
            put("2", "..---");
            put("3", "...--");
            put("4", "....-");
            put("5", ".....");
            put("6", "-....");
            put("7", "--...");
            put("8", "---..");
            put("9", "----.");
            put("0", "-----");
            put("?", "..--..");
            put("/", "-..-.");
            put("-", "-...-");
            put(".", ".-.-.-");
            put("()", "-.--.-");
            put("NULL", "----");
        }
    };

    private static boolean isEmpty(String s) {
        if(s == null || s.length() ==0) {
            return true;
        }
        return false;
    }
}