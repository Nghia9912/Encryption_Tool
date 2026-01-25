package view;

import javax.swing.*;
import java.awt.*;

public class SelectView extends JFrame {
    public JButton caesarBtn, thayTheBtn, vigenereBtn, transpositionBtn, hillBtn, affineBtn;
    public JButton aesBtn, desBtn, rsaBtn, blowfishBtn, twofishBtn;

    public SelectView() {
        setTitle("CipherX");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        JPanel mainPanel = new JPanel(new GridLayout(1, 2)); // 1 hàng, 2 cột

        //CỔ ĐIỂN
        JPanel classicPanel = new JPanel();
        classicPanel.setLayout(new BoxLayout(classicPanel, BoxLayout.Y_AXIS));
        classicPanel.setBorder(BorderFactory.createTitledBorder("cổ điển"));

        caesarBtn = new JButton("Caesar");
        thayTheBtn = new JButton("Thay Thế");
        vigenereBtn = new JButton("Vigenère");
        transpositionBtn = new JButton("Hoán Vị");
        hillBtn = new JButton("Hill");
        affineBtn = new JButton("Affine");

        addButtonToPanel(classicPanel, caesarBtn);
        addButtonToPanel(classicPanel, thayTheBtn);
        addButtonToPanel(classicPanel, vigenereBtn);
        addButtonToPanel(classicPanel, transpositionBtn);
        addButtonToPanel(classicPanel, hillBtn);
        addButtonToPanel(classicPanel, affineBtn);

        //HIỆN ĐẠI
        JPanel modernPanel = new JPanel();
        modernPanel.setLayout(new BoxLayout(modernPanel, BoxLayout.Y_AXIS));
        modernPanel.setBorder(BorderFactory.createTitledBorder("hiện đại"));

        aesBtn = new JButton("AES");
        desBtn = new JButton("DES");
        rsaBtn = new JButton("RSA");
        blowfishBtn = new JButton("Blowfish");
        twofishBtn = new JButton("Twofish");

        addButtonToPanel(modernPanel, aesBtn);
        addButtonToPanel(modernPanel, desBtn);
        addButtonToPanel(modernPanel, rsaBtn);
        addButtonToPanel(modernPanel, blowfishBtn);
        addButtonToPanel(modernPanel, twofishBtn);

        mainPanel.add(classicPanel);
        mainPanel.add(modernPanel);

        add(mainPanel);
        setVisible(true);
    }

    private void addButtonToPanel(JPanel panel, JButton button) {
        button.setMaximumSize(new Dimension(120, 30));
        panel.add(button);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
    }
}
