package model;

public class CaesarModel {

    public String encrypt(String text, int key) {
        StringBuilder encryptedText = new StringBuilder();

        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                char newChar = (char) ((c - base + key) % 26 + base);
                encryptedText.append(newChar);
            } else {
                encryptedText.append(c);
            }
        }

        return encryptedText.toString();
    }

    public String decrypt(String text, int key) {
        int decryptKey = 26 - key % 26;
        return encrypt(text, decryptKey);
    }
}
