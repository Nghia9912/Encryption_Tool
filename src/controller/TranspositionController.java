package controller;

import model.TranspositionModel;
import view.TranspositionView;
import javax.swing.*;
import java.io.*;

public class TranspositionController {
    private TranspositionModel model;
    private TranspositionView view;

    public TranspositionController(TranspositionModel transpositionModel, TranspositionView transpositionView) {
        this.model = transpositionModel;
        this.view = transpositionView;
        setupTranspositionActions();
    }

    private void setupTranspositionActions() {
        //mã hóa
        view.encryptBtn1.addActionListener(e -> {
            String inputText = view.textArea1.getText();
            String inputKey = view.keyField1.getText();
            view.resultArea1.setText(model.encrypt(inputText, inputKey));
        });

        //giải mã
        view.decryptBtn1.addActionListener(e -> {
            String encryptedText = view.textArea1.getText();
            String decryptionKey = view.keyField1.getText();
            view.resultArea1.setText(model.decrypt(encryptedText, decryptionKey));
        });

        //tạo key
        view.generateBtn1.addActionListener(e -> {
            view.keyField1.setText(model.generateRandomKey());
        });

        //lưu key
        view.copyAndSaveBtn1.addActionListener(e -> {
            String key = view.keyField1.getText();
            if (key.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Không có key để lưu", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setSelectedFile(new File("TranspotitionKey.txt"));

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

        // load key từ file
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
