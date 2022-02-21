package ru.salty.demoexam.ui;

import ru.salty.demoexam.entity.Entity;
import ru.salty.demoexam.manager.Manager;
import ru.salty.demoexam.util.BaseForm;
import ru.salty.demoexam.util.Dialog;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class ListForm extends BaseForm {

    private JPanel MainPanel;
    private JTextArea TextArea;
    private JButton backButton;

    public ListForm() {
        super(800, 600);
        setContentPane(MainPanel);
        setVisible(true);
        initButtons();
        initTextAreas();
    }

    private void initButtons() {

        backButton.addActionListener(e -> {
            dispose();
            new MainForm();
        });

    }

    private void initTextAreas() {

        TextArea.setEditable(false);

        try {
            ArrayList<Entity> list = Manager.selectall();
            String s = "";
            for (Entity entity : list) {
                s += entity;
                s += "\n";
            }
            TextArea.setText(s);
        } catch (SQLException e) {
            e.printStackTrace();
            Dialog.showError(this, "Error");
        }

    }

}
