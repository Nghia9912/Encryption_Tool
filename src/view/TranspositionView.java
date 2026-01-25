package view;

import javax.swing.*;
import java.awt.*;

public class TranspositionView extends JFrame {

    public JTextField keyField1;
    public JTextArea textArea1;
    public JTextArea resultArea1;
    public JButton generateBtn1, copyAndSaveBtn1, loadBtn1, encryptBtn1, decryptBtn1;

    public TranspositionView() {
        setTitle("HoanViX");
        setSize(400, 550);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Gợi ý: Mã hóa này có độ dài key là N ký tự");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label, BorderLayout.NORTH);
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;

        mainPanel.add(new JLabel("Nhập key:"), c);
        c.gridy++;
        keyField1 = new JTextField(25);
        mainPanel.add(keyField1, c);

        c.gridy++;
        JPanel keyBtns = new JPanel(new FlowLayout(FlowLayout.LEFT));
        generateBtn1 = new JButton("Tạo");
        copyAndSaveBtn1 = new JButton("Lưu");
        loadBtn1 = new JButton("Load");
        keyBtns.add(generateBtn1);
        keyBtns.add(copyAndSaveBtn1);
        keyBtns.add(loadBtn1);
        mainPanel.add(keyBtns, c);

        c.gridy++;
        mainPanel.add(new JLabel("Input:"), c);
        c.gridy++;
        textArea1 = new JTextArea(5, 30);
        mainPanel.add(new JScrollPane(textArea1), c);

        c.gridy++;
        mainPanel.add(new JLabel("Output:"), c);
        c.gridy++;
        resultArea1 = new JTextArea(5, 30);
        resultArea1.setEditable(false);
        mainPanel.add(new JScrollPane(resultArea1), c);

        c.gridy++;
        JPanel actionBtns = new JPanel();
        encryptBtn1 = new JButton("Mã hóa");
        decryptBtn1 = new JButton("Giải mã");
        actionBtns.add(encryptBtn1);
        actionBtns.add(decryptBtn1);
        mainPanel.add(actionBtns, c);

        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
    }
}