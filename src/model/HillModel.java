package model;

public class HillModel {

    public String encrypt(String text, String key) {
        int[][] keyMatrix = makeKeyMatrix(key);
        text = text.toUpperCase().replaceAll("[^A-Z]", "");
        if (text.length() % 2 != 0) {
            text += "X";
        }
        String result = "";
        for (int i = 0; i < text.length(); i += 2) {
            String block = text.substring(i, i + 2);
            result += encryptBlock(block, keyMatrix);
        }
        return result;
    }

    public String decrypt(String text, String key) {
        int[][] keyMatrix = makeKeyMatrix(key);
        int[][] inverseMatrix = getInverseMatrix(keyMatrix);
        if (text.length() % 2 != 0) {
            text += "X";
        }
        String result = "";
        for (int i = 0; i < text.length(); i += 2) {
            String block = text.substring(i, i + 2);
            result += encryptBlock(block, inverseMatrix);
        }
        return result;
    }

    private int[][] makeKeyMatrix(String key) {
        String[] parts = key.split(",");
        int[][] matrix = new int[2][2];
        matrix[0][0] = Integer.parseInt(parts[0]);
        matrix[0][1] = Integer.parseInt(parts[1]);
        matrix[1][0] = Integer.parseInt(parts[2]);
        matrix[1][1] = Integer.parseInt(parts[3]);
        return matrix;
    }

    private String encryptBlock(String block, int[][] matrix) {
        int c1 = block.charAt(0) - 'A';
        int c2 = block.charAt(1) - 'A';

        int n1 = (matrix[0][0] * c1 + matrix[0][1] * c2) % 26;
        int n2 = (matrix[1][0] * c1 + matrix[1][1] * c2) % 26;

        if (n1 < 0) n1 += 26;
        if (n2 < 0) n2 += 26;

        return "" + (char)(n1 + 'A') + (char)(n2 + 'A');
    }

    private int[][] getInverseMatrix(int[][] matrix) {
        int det = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        det = mod26(det);

        int detInv = 0;
        for (int i = 1; i < 26; i++) {
            if ((det * i) % 26 == 1) {
                detInv = i;
                break;
            }
        }

        int[][] inverse = new int[2][2];
        inverse[0][0] = mod26(matrix[1][1] * detInv);
        inverse[0][1] = mod26(-matrix[0][1] * detInv);
        inverse[1][0] = mod26(-matrix[1][0] * detInv);
        inverse[1][1] = mod26(matrix[0][0] * detInv);

        return inverse;
    }

    private int mod26(int x) {
        return (x % 26 + 26) % 26;
    }

    public String generateRandomKey() {
        int a = (int)(Math.random() * 26);
        int b = (int)(Math.random() * 26);
        int c = (int)(Math.random() * 26);
        int d = (int)(Math.random() * 26);
        return a + "," + b + "," + c + "," + d;
    }
}