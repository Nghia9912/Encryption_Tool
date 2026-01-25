package model;

import javax.crypto.Cipher;
import java.io.*;
import java.security.*;
import java.security.spec.*;
import java.util.Base64;

public class RSAModel {

    public String encryptText(String plainText, PublicKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cipher.doFinal(plainText.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public String decryptText(String cipherText, PrivateKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decoded = Base64.getDecoder().decode(cipherText);
        byte[] decrypted = cipher.doFinal(decoded);
        return new String(decrypted, "UTF-8");
    }

    public void encryptFile(File inputFile, File outputFile, PublicKey key) throws Exception {
        byte[] data = readFile(inputFile);
        byte[] encrypted = encryptLarge(data, key);
        writeFile(outputFile, encrypted);
    }

    public void decryptFile(File inputFile, File outputFile, PrivateKey key) throws Exception {
        byte[] data = readFile(inputFile);
        byte[] decrypted = decryptLarge(data, key);
        writeFile(outputFile, decrypted);
    }

    private byte[] encryptLarge(byte[] data, PublicKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        int blockSize = 117; // for 1024-bit key
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        for (int i = 0; i < data.length; i += blockSize) {
            int len = Math.min(blockSize, data.length - i);
            bos.write(cipher.doFinal(data, i, len));
        }
        return bos.toByteArray();
    }

    private byte[] decryptLarge(byte[] data, PrivateKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, key);
        int blockSize = 128; // for 1024-bit key
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        for (int i = 0; i < data.length; i += blockSize) {
            int len = Math.min(blockSize, data.length - i);
            bos.write(cipher.doFinal(data, i, len));
        }
        return bos.toByteArray();
    }

    private byte[] readFile(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file)) {
            return fis.readAllBytes();
        }
    }

    private void writeFile(File file, byte[] data) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(data);
        }
    }

    public String[] generateKeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(1024);
            KeyPair pair = keyGen.generateKeyPair();
            this.publicKey = pair.getPublic();
            this.privateKey = pair.getPrivate();
            String publicKeyStr = Base64.getEncoder().encodeToString(publicKey.getEncoded());
            String privateKeyStr = Base64.getEncoder().encodeToString(privateKey.getEncoded());
            return new String[]{publicKeyStr, privateKeyStr};
        } catch (Exception e) {
            return new String[]{"", ""};
        }
    }


    private PublicKey publicKey;
    private PrivateKey privateKey;

    public String getPrivateKeyString() {
        return Base64.getEncoder().encodeToString(privateKey.getEncoded());
    }

    public PublicKey getPublicKeyFromString(String keyStr) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(keyStr);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }

    public PrivateKey getPrivateKeyFromString(String keyStr) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(keyStr);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }
}
