package controller;

import model.SubstitutionModel;
import view.SubstitutionView;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class SubstitutionController {
    private SubstitutionModel model;
    private SubstitutionView view;

    public SubstitutionController(SubstitutionModel model, SubstitutionView view) {
        this.model = model;
        this.view = view;
        setupSubstitutionActions();
    }

    private void setupSubstitutionActions() {
        // Mã hóa
        view.encryptBtn1.addActionListener(e -> {
            String inputText = view.textArea1.getText();
            String inputKey = view.keyField1.getText();
            String encryptedText = model.encrypt(inputText, inputKey);
            view.resultArea1.setText(encryptedText);
        });

        // Giải mã
        view.decryptBtn1.addActionListener(e -> {
            String encryptedText = view.textArea1.getText();
            String decryptionKey = view.keyField1.getText();
            String decryptedText = model.decrypt(encryptedText, decryptionKey);
            view.resultArea1.setText(decryptedText);
        });

        view.generateBtn1.addActionListener(e -> {
            view.keyField1.setText(model.generateRandomKey());
        });

        // Lưu key
        view.copyAndSaveBtn1.addActionListener(e -> {
            String key = view.keyField1.getText();
            if (!model.isValidKey(key)) {
                JOptionPane.showMessageDialog(view, "Không có key để lưu", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setSelectedFile(new File("SubstitutionKey.txt"));

            int userChoice = fileChooser.showSaveDialog(view);
            if (userChoice == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                try (PrintWriter writer = new PrintWriter(fileToSave)) {
                    writer.println(key);
                    JOptionPane.showMessageDialog(view, "Lưu key thành công!", "Xong", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(view, "Không lưu được file: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Tải key từ file
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
    }

}
