package controller;

import model.AffineModel;
import view.AffineView;
import javax.swing.*;
import java.io.*;

public class AffineController {
    private AffineModel model;
    private AffineView view;

    public AffineController(AffineModel model, AffineView view) {
        this.model = model;
        this.view = view;
        initController();
    }

    private void initController() {
        view.encryptBtn1.addActionListener(e -> {
            String text = view.textArea1.getText();
            String key = view.keyField1.getText();
            view.resultArea1.setText(model.encrypt(text, key));
        });

        view.decryptBtn1.addActionListener(e -> {
            String text = view.textArea1.getText();
            String key = view.keyField1.getText();
            view.resultArea1.setText(model.decrypt(text, key));
        });

        view.generateBtn1.addActionListener(e -> {
            view.keyField1.setText(model.generateRandomKey());
        });

        view.copyAndSaveBtn1.addActionListener(e -> {
            String key = view.keyField1.getText();
            if (key.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Không có key để lưu", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JFileChooser fc = new JFileChooser();
            fc.setDialogTitle("Lưu key");
            fc.setSelectedFile(new File("affine_key.txt"));

            if (fc.showSaveDialog(view) == JFileChooser.APPROVE_OPTION) {
                try (PrintWriter pw = new PrintWriter(fc.getSelectedFile())) {
                    pw.print(key);
                    JOptionPane.showMessageDialog(view, "Đã lưu key!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, "Lỗi khi lưu file!");
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
    }
}