package model;

import java.util.Random;
import java.util.Arrays;

public class TranspositionModel {

    public String encrypt(String inputText, String keyText) {
            int[] columnOrder = getKeyOrderFromKeyText(keyText);
            int numColumns = columnOrder.length;
            int numRows = (int) Math.ceil((double) inputText.length() / numColumns);

            // Tạo bảng chữ
            char[][] encryptionGrid = new char[numRows][numColumns];
            int charIndex = 0;

            // Điền vào từng ô theo từng hàng
            for (int row = 0; row < numRows; row++) {
                for (int col = 0; col < numColumns; col++) {
                    if (charIndex < inputText.length()) {
                        encryptionGrid[row][col] = inputText.charAt(charIndex++);
                    } else {
                        encryptionGrid[row][col] = ' ';
                    }
                }
            }

            // Đọc từng cột theo thứ tự key
            StringBuilder encryptedText = new StringBuilder();
            for (int columnIndex : columnOrder) {
                for (int row = 0; row < numRows; row++) {
                    encryptedText.append(encryptionGrid[row][columnIndex]);
                }
            }

            return encryptedText.toString();

    }

    public String decrypt(String encryptedText, String keyText) {
            int[] columnOrder = getKeyOrderFromKeyText(keyText);
            int numColumns = columnOrder.length;
            int numRows = (int) Math.ceil((double) encryptedText.length() / numColumns);

            // Tạo bảng rỗng để giải mã
            char[][] decryptionGrid = new char[numRows][numColumns];
            int charIndex = 0;

            for (int columnIndex : columnOrder) {
                for (int row = 0; row < numRows; row++) {
                    if (charIndex < encryptedText.length()) {
                        decryptionGrid[row][columnIndex] = encryptedText.charAt(charIndex++);
                    } else {
                        decryptionGrid[row][columnIndex] = ' ';
                    }
                }
            }

            // Đọc theo từng hàng để lấy text gốc
            StringBuilder decryptedText = new StringBuilder();
            for (int row = 0; row < numRows; row++) {
                for (int col = 0; col < numColumns; col++) {
                    decryptedText.append(decryptionGrid[row][col]);
                }
            }

            return decryptedText.toString().trim();
    }

    private int[] getKeyOrderFromKeyText(String keyText) {
        String cleanedKey = keyText.toUpperCase().replaceAll("\\s+", "");
        KeyChar[] keyCharacters = new KeyChar[cleanedKey.length()];

        for (int i = 0; i < cleanedKey.length(); i++) {
            keyCharacters[i] = new KeyChar(cleanedKey.charAt(i), i);
        }

        Arrays.sort(keyCharacters);

        int[] orderResult = new int[cleanedKey.length()];
        for (int i = 0; i < keyCharacters.length; i++) {
            orderResult[i] = keyCharacters[i].originalIndex;
        }

        return orderResult;
    }

    private static class KeyChar implements Comparable<KeyChar> {
        char character;
        int originalIndex;

        KeyChar(char character, int originalIndex) {
            this.character = character;
            this.originalIndex = originalIndex;
        }

        @Override
        public int compareTo(KeyChar other) {
            return Character.compare(this.character, other.character);
        }
    }

    public String generateRandomKey() {
        Random randomGenerator = new Random();
        int randomKeyLength = randomGenerator.nextInt(10) + 5;
        StringBuilder randomKeyBuilder = new StringBuilder();

        for (int i = 0; i < randomKeyLength; i++) {
            randomKeyBuilder.append((char) (randomGenerator.nextInt(26) + 'A'));
        }

        return randomKeyBuilder.toString();
    }
}
