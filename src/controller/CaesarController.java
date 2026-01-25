package controller;

import model.CaesarModel;
import view.CaesarView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Random;

public class CaesarController {

    private CaesarModel model;
    private CaesarView view;
    private Random random;

    public CaesarController(CaesarModel model, CaesarView view) {
        this.model = model;
        this.view = view;
        this.random = new Random();

        initController();
    }

    private void initController() {
        view.encryptBtn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = view.textArea1.getText();
                try {
                    int key = Integer.parseInt(view.keyField1.getText());
                    String output = model.encrypt(input, key);
                    view.resultArea1.setText(output);
                } catch (NumberFormatException ex) {
                    view.resultArea1.setText("Key phải là số nguyên");
                } catch (Exception ex) {
                    view.resultArea1.setText("Lỗi không xác định...");
                }
            }
        });

        view.decryptBtn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = view.textArea1.getText();
                try {
                    int key = Integer.parseInt(view.keyField1.getText());
                    String output = model.decrypt(input, key);
                    view.resultArea1.setText(output);
                } catch (NumberFormatException ex) {
                    view.resultArea1.setText("Key phải là số");
                }
            }
        });

        view.copyAndSaveBtn1.addActionListener(e -> {
            String key = view.keyField1.getText();

            if (key.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Không có key để lưu", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JFileChooser fc = new JFileChooser();
            fc.setDialogTitle("Lưu key");
            fc.setSelectedFile(new File("caesar_key.txt"));

            int result = fc.showSaveDialog(view);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                try (PrintWriter pw = new PrintWriter(file)) {
                    pw.println(key);
                    JOptionPane.showMessageDialog(view, "Đã lưu key!", "OK", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(view, "Không lưu được file!", "Lỗi", JOptionPane.ERROR_MESSAGE);
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

        view.generateBtn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int k = random.nextInt(100) + 1;
                view.keyField1.setText(String.valueOf(k));
            }
        });
    }
}
