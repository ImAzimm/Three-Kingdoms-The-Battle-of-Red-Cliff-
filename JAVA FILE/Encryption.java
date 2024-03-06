package assignment;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author User
 */
public class Encryption {
    private final String alphabet = "abcdefghijklmnopqrstuvwxyz";

    public Encryption() {
    }

    public String encrypt(String str, int shiftKey, char space) {
        String cipherText = "";
        int charPosition=0;
        for (int i = 0; i < str.length(); i++) {
            char currentChar = str.charAt(i);
            if(currentChar == '('){
                cipherText += "(";
                String reverse = "";
                while(true){
                    i++;
                    currentChar = str.charAt(i);
                    if(currentChar == ')') {
                        cipherText += reverseString(reverse) + currentChar;
                        break;
                    }
                    reverse += encrypt(Character.toString(currentChar), shiftKey, space);
                }
                continue;
            }
            else if (Character.isUpperCase(currentChar)) {
                cipherText += "^";
                charPosition = alphabet.indexOf(Character.toLowerCase(currentChar));
            }
            else if(currentChar == ' '){
                cipherText += space;
                continue;
            }
            else if(!alphabet.contains(Character.toString(currentChar))){
                cipherText += currentChar;
                continue;
            }
            else {
                charPosition = alphabet.indexOf(currentChar);
            }
            int keyVal = (charPosition + shiftKey) % 26;
            char replaceVal = alphabet.charAt(keyVal);
            cipherText += replaceVal;
        }
        return cipherText;
    }

    public String decrypt(String message, int shiftKey, char space) {
        String cipherText = "";
        int charPosition=0, keyVal;
        for (int i = 0; i < message.length(); i++) {
            char currentChar = message.charAt(i);
            if(currentChar == '('){
                String reverse = "";
                while(true){
                    i++;
                    currentChar = message.charAt(i);
                    if(currentChar == ')') {
                        cipherText += decrypt(reverseString(reverse), shiftKey, space);
                        break;
                    }
                    reverse += currentChar;
                }
            }
            else if (currentChar == '^') {
                currentChar = message.charAt(i+1);
                i++;
                charPosition = alphabet.indexOf(currentChar);
                if(charPosition < shiftKey)
                    keyVal = (26 + charPosition - shiftKey) % 26;
                else
                    keyVal = (charPosition - shiftKey) % 26;

                char replaceChar = alphabet.charAt(keyVal);
                cipherText += Character.toUpperCase(replaceChar);
            }
            else if(currentChar == space){
                cipherText += " ";
            }
            else if(!alphabet.contains(Character.toString(currentChar))){
                cipherText += currentChar;
            }
            else {
                charPosition = alphabet.indexOf(currentChar);
                if(charPosition < shiftKey)
                    keyVal = (26 + charPosition - shiftKey) % 26;
                else
                    keyVal = (charPosition - shiftKey) % 26;

                char replaceChar = alphabet.charAt(keyVal);
                cipherText += replaceChar;
            }
        }
        return cipherText;
    }

    public String reverseString(String text){
        String reversedStr = "";

        for (int i = 0; i < text.length(); i++) {
            reversedStr = text.charAt(i) + reversedStr;
        }

        return reversedStr;
    }
}
