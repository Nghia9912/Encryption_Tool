package controller;

import model.DESModel;
import view.DESView;

import javax.crypto.SecretKey;
import javax.swing.*;
import java.io.*;

public class DESController {

    private DESModel model;
    private DESView view;

    public DESController(DESModel model, DESView view) {
        this.model = model;
        this.view = view;
        initController();
    }

    private void initController() {
        view.encryptBtn1.addActionListener(e -> {
            String input = view.textArea1.getText();
            String keyStr = view.keyField1.getText();
            try {
                SecretKey key = model.getKeyFromString(keyStr);
                String output = model.encryptText(input, key);
                view.resultArea1.setText(output);
            } catch (Exception ex) {
                view.resultArea1.setText("Lỗi mã hóa văn bản.");
            }
        });

        view.decryptBtn1.addActionListener(e -> {
            String input = view.textArea1.getText();
            String keyStr = view.keyField1.getText();
            try {
                SecretKey key = model.getKeyFromString(keyStr);
                String output = model.decryptText(input, key);
                view.resultArea1.setText(output);
            } catch (Exception ex) {
                view.resultArea1.setText("Lỗi giải mã văn bản.");
            }
        });

        view.generateBtn1.addActionListener(e -> {
            String key = model.generateRandomKey();
            view.keyField1.setText(key);
        });

        view.copyAndSaveBtn1.addActionListener(e -> {
            String key = view.keyField1.getText();
            if (key.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Không có key để lưu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JFileChooser fc = new JFileChooser();
            fc.setDialogTitle("Lưu key");
            fc.setSelectedFile(new File("des_key.txt"));
            if (fc.showSaveDialog(view) == JFileChooser.APPROVE_OPTION) {
                try (PrintWriter pw = new PrintWriter(fc.getSelectedFile())) {
                    pw.println(key);
                    JOptionPane.showMessageDialog(view, "Đã lưu key.", "OK", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(view, "Lỗi khi lưu key!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        view.loadBtn1.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            fc.setDialogTitle("Load key");

            if (fc.showOpenDialog(view) == JFileChooser.APPROVE_OPTION) {
                try (BufferedReader br = new BufferedReader(new FileReader(fc.getSelectedFile()))) {
                    view.keyField1.setText(br.readLine());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(view, "Lỗi khi đọc key!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        view.encryptBtn2.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            if (fc.showOpenDialog(view) == JFileChooser.APPROVE_OPTION) {
                File inputFile = fc.getSelectedFile();
                String keyStr = view.keyField2.getText();
                try {
                    SecretKey key = model.getKeyFromString(keyStr);
                    File outputFile = new File(inputFile.getParent(), "encrypted_" + inputFile.getName());
                    model.encryptFile(inputFile, outputFile, key);
                    JOptionPane.showMessageDialog(view, "Đã mã hóa file: " + outputFile.getName());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, "Lỗi mã hóa file!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        view.decryptBtn2.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            if (fc.showOpenDialog(view) == JFileChooser.APPROVE_OPTION) {
                File inputFile = fc.getSelectedFile();
                String keyStr = view.keyField2.getText();
                try {
                    SecretKey key = model.getKeyFromString(keyStr);
                    File outputFile = new File(inputFile.getParent(), "decrypted_" + inputFile.getName());
                    model.decryptFile(inputFile, outputFile, key);
                    JOptionPane.showMessageDialog(view, "Đã giải mã file: " + outputFile.getName());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, "Lỗi giải mã file!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        view.generateBtn2.addActionListener(e -> {
            String key = model.generateRandomKey();
            view.keyField2.setText(key);
        });

        view.copyAndSaveBtn2.addActionListener(e -> {
            String key = view.keyField2.getText();
            if (key.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Không có key để lưu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JFileChooser fc = new JFileChooser();
            fc.setDialogTitle("Lưu key file");
            fc.setSelectedFile(new File("des_file_key.txt"));

            if (fc.showSaveDialog(view) == JFileChooser.APPROVE_OPTION) {
                try (PrintWriter pw = new PrintWriter(fc.getSelectedFile())) {
                    pw.println(key);
                    JOptionPane.showMessageDialog(view, "Đã lưu key file.", "OK", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(view, "Lỗi khi lưu key file!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        view.loadBtn2.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            fc.setDialogTitle("Load key file");

            if (fc.showOpenDialog(view) == JFileChooser.APPROVE_OPTION) {
                try (BufferedReader br = new BufferedReader(new FileReader(fc.getSelectedFile()))) {
                    view.keyField2.setText(br.readLine());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(view, "Lỗi khi đọc key!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
