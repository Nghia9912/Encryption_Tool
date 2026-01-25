package view;

import javax.swing.*;
import java.awt.*;

public class DESView extends JFrame {

    public JTextField keyField1;
    public JTextArea textArea1;
    public JTextArea resultArea1;
    public JButton generateBtn1, copyAndSaveBtn1, loadBtn1, encryptBtn1, decryptBtn1;

    public JTextField keyField2;
    public JButton encryptBtn2, decryptBtn2, copyAndSaveBtn2, loadBtn2, generateBtn2;

    public DESView() {
        setTitle("DES Encryption Tool");
        setSize(450, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        JTabbedPane tabbedPane = new JTabbedPane();

        // ===== Tab 1: Mã hóa văn bản =====
        JPanel textPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c1 = new GridBagConstraints();
        c1.insets = new Insets(5, 5, 5, 5);
        c1.fill = GridBagConstraints.HORIZONTAL;
        c1.gridx = 0;
        c1.gridy = 0;
        c1.gridwidth = 2;

        textPanel.add(new JLabel("Key:"), c1);
        c1.gridy++;
        keyField1 = new JTextField(25);
        textPanel.add(keyField1, c1);

        c1.gridy++;
        JPanel keyBtns1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        generateBtn1 = new JButton("Tạo");
        copyAndSaveBtn1 = new JButton("Lưu");
        loadBtn1 = new JButton("Load");
        keyBtns1.add(generateBtn1);
        keyBtns1.add(copyAndSaveBtn1);
        keyBtns1.add(loadBtn1);
        textPanel.add(keyBtns1, c1);

        c1.gridy++;
        textPanel.add(new JLabel("Input:"), c1);
        c1.gridy++;
        textArea1 = new JTextArea(5, 30);
        textPanel.add(new JScrollPane(textArea1), c1);

        c1.gridy++;
        textPanel.add(new JLabel("Output:"), c1);
        c1.gridy++;
        resultArea1 = new JTextArea(5, 30);
        resultArea1.setEditable(false);
        textPanel.add(new JScrollPane(resultArea1), c1);

        c1.gridy++;
        JPanel actionBtns1 = new JPanel();
        encryptBtn1 = new JButton("Mã hóa");
        decryptBtn1 = new JButton("Giải mã");
        actionBtns1.add(encryptBtn1);
        actionBtns1.add(decryptBtn1);
        textPanel.add(actionBtns1, c1);

        tabbedPane.addTab("Mã hóa văn bản", textPanel);

        // ===== Tab 2: Mã hóa file =====
        JPanel filePanel = new JPanel(new GridBagLayout());
        GridBagConstraints c2 = new GridBagConstraints();
        c2.insets = new Insets(5, 5, 5, 5);
        c2.fill = GridBagConstraints.HORIZONTAL;
        c2.gridx = 0;
        c2.gridy = 0;
        c2.gridwidth = 2;

        filePanel.add(new JLabel("Key:"), c2);
        c2.gridy++;
        keyField2 = new JTextField(25);
        filePanel.add(keyField2, c2);

        c2.gridy++;
        JPanel keyBtns2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        generateBtn2 = new JButton("Tạo");
        copyAndSaveBtn2 = new JButton("Lưu");
        loadBtn2 = new JButton("Load");
        keyBtns2.add(generateBtn2);
        keyBtns2.add(copyAndSaveBtn2);
        keyBtns2.add(loadBtn2);
        filePanel.add(keyBtns2, c2);

        c2.gridy++;
        JPanel fileBtns = new JPanel();
        encryptBtn2 = new JButton("encrypt file");
        decryptBtn2 = new JButton("decrypt file");
        fileBtns.add(encryptBtn2);
        fileBtns.add(decryptBtn2);
        filePanel.add(fileBtns, c2);

        tabbedPane.addTab("Mã hóa file", filePanel);

        // Giao diện tổng thể
        setLayout(new BorderLayout());
        add(tabbedPane, BorderLayout.CENTER);
        setVisible(true);
    }
}
