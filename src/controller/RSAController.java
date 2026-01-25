package controller;

import model.RSAModel;
import view.RSAView;

import javax.swing.*;
import java.io.*;
import java.security.PrivateKey;
import java.security.PublicKey;

public class RSAController {

    private RSAModel model;
    private RSAView view;

    public RSAController(RSAModel model, RSAView view) {
        this.model = model;
        this.view = view;
        initController();
    }

    private void initController() {
        // Mã hóa văn bản
        view.encryptBtn1.addActionListener(e -> {
            String input = view.textArea1.getText();
            String keyStr = view.keyField1.getText();
            try {
                PublicKey pubKey = model.getPublicKeyFromString(keyStr);
                String output = model.encryptText(input, pubKey);
                view.resultArea1.setText(output);
            } catch (Exception ex) {
                view.resultArea1.setText("Lỗi mã hóa văn bản.");
            }
        });

        // Giải mã văn bản
        view.decryptBtn1.addActionListener(e -> {
            String input = view.textArea1.getText();
            String keyStr = view.keyField1.getText();
            try {
                PrivateKey privKey = model.getPrivateKeyFromString(keyStr);
                String output = model.decryptText(input, privKey);
                view.resultArea1.setText(output);
            } catch (Exception ex) {
                view.resultArea1.setText("Lỗi giải mã văn bản.");
            }
        });

        // Sinh cặp khóa (public + private)
        view.generateBtn1.addActionListener(e -> {
            String[] keys = model.generateKeyPair();
            view.keyField1.setText(keys[0] + "\n" + keys[1]);;
        });

        // Lưu key văn bản
        view.copyAndSaveBtn1.addActionListener(e -> {
            String key = view.keyField1.getText();
            if (key.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Không có key để lưu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JFileChooser fc = new JFileChooser();
            fc.setDialogTitle("Lưu key");
            fc.setSelectedFile(new File("RSA_text_keys.txt"));

            if (fc.showSaveDialog(view) == JFileChooser.APPROVE_OPTION) {
                try (PrintWriter pw = new PrintWriter(fc.getSelectedFile())) {
                    pw.println(key);
                    JOptionPane.showMessageDialog(view, "Đã lưu key.", "OK", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(view, "Lỗi khi lưu key!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Load key văn bản
        view.loadBtn1.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            fc.setDialogTitle("Load key");

            if (fc.showOpenDialog(view) == JFileChooser.APPROVE_OPTION) {
                try (BufferedReader br = new BufferedReader(new FileReader(fc.getSelectedFile()))) {
                    String line, all = "";
                    while ((line = br.readLine()) != null) all += line + "\n";
                    view.keyField1.setText(all.trim());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(view, "Lỗi khi đọc key!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Mã hóa file
        view.encryptBtn2.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            if (fc.showOpenDialog(view) == JFileChooser.APPROVE_OPTION) {
                File inputFile = fc.getSelectedFile();
                String keyStr = view.keyField2.getText();
                try {
                    PublicKey pubKey = model.getPublicKeyFromString(keyStr);
                    File outputFile = new File(inputFile.getParent(), "encrypted_" + inputFile.getName());
                    model.encryptFile(inputFile, outputFile, pubKey);
                    JOptionPane.showMessageDialog(view, "Đã mã hóa file: " + outputFile.getName());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, "Lỗi mã hóa file!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Giải mã file
        view.decryptBtn2.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            if (fc.showOpenDialog(view) == JFileChooser.APPROVE_OPTION) {
                File inputFile = fc.getSelectedFile();
                String keyStr = view.keyField2.getText();
                try {
                    PrivateKey privKey = model.getPrivateKeyFromString(keyStr);
                    File outputFile = new File(inputFile.getParent(), "decrypted_" + inputFile.getName());
                    model.decryptFile(inputFile, outputFile, privKey);
                    JOptionPane.showMessageDialog(view, "Đã giải mã file: " + outputFile.getName());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, "Lỗi giải mã file!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Sinh key mới cho file
        view.generateBtn2.addActionListener(e -> {
            String[] keys = model.generateKeyPair();
            view.keyField2.setText(keys[0] + "\n" + keys[1]);
        });

        // Lưu key file
        view.copyAndSaveBtn2.addActionListener(e -> {
            String key = view.keyField2.getText();
            if (key.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Không có key để lưu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JFileChooser fc = new JFileChooser();
            fc.setDialogTitle("Lưu key file");
            fc.setSelectedFile(new File("RSA_file_keys.txt"));

            if (fc.showSaveDialog(view) == JFileChooser.APPROVE_OPTION) {
                try (PrintWriter pw = new PrintWriter(fc.getSelectedFile())) {
                    pw.println(key);
                    JOptionPane.showMessageDialog(view, "Đã lưu key file.", "OK", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(view, "Lỗi khi lưu key file!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Load key file
        view.loadBtn2.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            fc.setDialogTitle("Load key file");

            if (fc.showOpenDialog(view) == JFileChooser.APPROVE_OPTION) {
                try (BufferedReader br = new BufferedReader(new FileReader(fc.getSelectedFile()))) {
                    String line, all = "";
                    while ((line = br.readLine()) != null) all += line + "\n";
                    view.keyField2.setText(all.trim());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(view, "Lỗi khi đọc key file!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
