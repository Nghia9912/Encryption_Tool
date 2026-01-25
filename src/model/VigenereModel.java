package model;

import java.util.Random;

public class VigenereModel {
    public String encrypt(String plainText, String keyText) {
        StringBuilder encryptedText = new StringBuilder();
        plainText = plainText.toUpperCase();
        keyText = keyText.toUpperCase();
        int keyIndex = 0;

        for (int i = 0; i < plainText.length(); i++) {
            char currentChar = plainText.charAt(i);
            if (Character.isLetter(currentChar)) {
                char encryptedChar = (char) ((currentChar + keyText.charAt(keyIndex) - 2 * 'A') % 26 + 'A');
                encryptedText.append(encryptedChar);
                keyIndex++;
                if (keyIndex == keyText.length()) keyIndex = 0;
            } else {
                encryptedText.append(currentChar);
            }
        }

        return encryptedText.toString();
    }

    public String decrypt(String cipherText, String keyText) {
        StringBuilder decryptedText = new StringBuilder();
        cipherText = cipherText.toUpperCase();
        keyText = keyText.toUpperCase();
        int keyIndex = 0;

        for (int i = 0; i < cipherText.length(); i++) {
            char currentChar = cipherText.charAt(i);
            if (Character.isLetter(currentChar)) {
                char decryptedChar = (char) ((currentChar - keyText.charAt(keyIndex) + 26) % 26 + 'A');
                decryptedText.append(decryptedChar);
                keyIndex++;
                if (keyIndex == keyText.length()) keyIndex = 0;
            } else {
                decryptedText.append(currentChar);
            }
        }

        return decryptedText.toString();
    }

    public String generateRandomKey(int keyLength) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder randomKey = new StringBuilder();
        Random randomGenerator = new Random();

        for (int i = 0; i < keyLength; i++) {
            randomKey.append(alphabet.charAt(randomGenerator.nextInt(26)));
        }

        return randomKey.toString();
    }
}
