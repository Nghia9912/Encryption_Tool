package controller;

import model.VigenereModel;
import view.VigenereView;

import javax.swing.*;
import java.io.*;

public class VigenereController {
    private VigenereModel model;
    private VigenereView view;

    public VigenereController(VigenereModel vigenereModel, VigenereView vigenereView) {
        this.model = vigenereModel;
        this.view = vigenereView;
        setupActions();
    }

    private void setupActions() {
        view.encryptBtn1.addActionListener(e -> {
            String originalText = view.textArea1.getText();
            String inputKey = view.keyField1.getText();
            view.resultArea1.setText(model.encrypt(originalText, inputKey));
        });

        view.decryptBtn1.addActionListener(e -> {
            String encryptedText = view.textArea1.getText();
            String decryptionKey = view.keyField1.getText();
            view.resultArea1.setText(model.decrypt(encryptedText, decryptionKey));
        });

        view.copyAndSaveBtn1.addActionListener(e -> {
            String keyToSave = view.keyField1.getText();
            if (keyToSave.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Không có key để lưu", "lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Lưu key");
            fileChooser.setSelectedFile(new File("VigenereKey.txt"));

            int result = fileChooser.showSaveDialog(view);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try (PrintWriter writer = new PrintWriter(selectedFile)) {
                    writer.println(keyToSave);
                    JOptionPane.showMessageDialog(view, "Đã lưu key ", "OK", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(view, "Lỗi khi lưu: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        view.loadBtn1.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            fc.setDialogTitle("Load key");

            if (fc.showOpenDialog(view) == JFileChooser.APPROVE_OPTION) {
                try (BufferedReader br = new BufferedReader(new FileReader(fc.getSelectedFile()))) {
                    view.keyField1.setText(br.readLine());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, "Lỗi khi đọc file!");
                }
            }
        });

        view.generateBtn1.addActionListener(e -> {
            try {
                int keyLength = Integer.parseInt(view.keyLengthField1.getText());
                if (keyLength <= 0) {
                    view.keyField1.setText("Vui lòng nhập độ dài lớn hơn 0");
                    return;
                }
                view.keyField1.setText(model.generateRandomKey(keyLength));
            } catch (NumberFormatException ex) {
                view.keyField1.setText("Phải nhập số nguyên!");
            }
        });
    }
}
