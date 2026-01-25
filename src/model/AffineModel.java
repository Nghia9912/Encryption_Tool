package model;

import java.util.Random;

public class AffineModel {

    public String encrypt(String text, String key) {
        String[] parts = key.split(",");
        int a = Integer.parseInt(parts[0]);
        int b = Integer.parseInt(parts[1]);

        StringBuilder result = new StringBuilder();
        text = text.toUpperCase();

        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                int x = c - 'A';
                int encrypted = (a * x + b) % 26;
                result.append((char)(encrypted + 'A'));
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }

    public String decrypt(String text, String key) {
        String[] parts = key.split(",");
        int a = Integer.parseInt(parts[0]);
        int b = Integer.parseInt(parts[1]);

        // Tìm nghịch đảo của a
        int aInv = 0;
        for (int i = 0; i < 26; i++) {
            if ((a * i) % 26 == 1) {
                aInv = i;
                break;
            }
        }
        StringBuilder result = new StringBuilder();
        text = text.toUpperCase();

        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                int y = c - 'A';
                int decrypted = aInv * (y - b) % 26;
                if (decrypted < 0) decrypted += 26;
                result.append((char)(decrypted + 'A'));
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }
    public String generateRandomKey() {
        Random rand = new Random();
        int a, b;

        do {
            a = rand.nextInt(25) + 1;
        } while (gcd(a, 26) != 1);

        b = rand.nextInt(26);

        return a + "," + b;
    }
    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}