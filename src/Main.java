import com.formdev.flatlaf.FlatIntelliJLaf;
import controller.*;
import model.*;
import view.*;

import javax.swing.*;

public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            SelectView selectView = new SelectView();

            // CỔ ĐIỂN
            selectView.caesarBtn.addActionListener(e -> {
                CaesarModel model = new CaesarModel();
                CaesarView view = new CaesarView();
                new CaesarController(model, view);
            });

            selectView.thayTheBtn.addActionListener(e -> {
                SubstitutionModel model = new SubstitutionModel();
                SubstitutionView view = new SubstitutionView();
                new SubstitutionController(model, view);
            });
            selectView.vigenereBtn.addActionListener(e -> {
                VigenereModel model = new VigenereModel();
                VigenereView view = new VigenereView();
                new VigenereController(model, view);
            });
            selectView.transpositionBtn.addActionListener(e -> {
                TranspositionModel model = new TranspositionModel();
                TranspositionView view = new TranspositionView();
                new TranspositionController(model, view);
            });
            selectView.hillBtn.addActionListener(e -> {
                HillModel model = new HillModel();
                HillView view = new HillView();
                new HillController(model, view);
            });
            selectView.affineBtn.addActionListener(e -> {
                AffineModel model = new AffineModel();
                AffineView view = new AffineView();
                new AffineController(model, view);
            });
            //HIỆN ĐẠI
            selectView.aesBtn.addActionListener(e -> {
                AESModel model = new AESModel();
                AESView view = new AESView();
                new AESController(model, view);
            });
            selectView.desBtn.addActionListener(e -> {
                DESModel model = new DESModel();
                DESView view = new DESView();
                new DESController(model, view);
            });
//            selectView.affineBtn.addActionListener(e -> {
//                AffineModel model = new AffineModel();
//                AffineView view = new AffineView();
//                new AffineController(model, view);
//            });


        });
    }


