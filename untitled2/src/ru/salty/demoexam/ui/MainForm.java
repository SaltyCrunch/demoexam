package ru.salty.demoexam.ui;

import ru.salty.demoexam.entity.Entity;
import ru.salty.demoexam.manager.Manager;
import ru.salty.demoexam.util.BaseForm;
import ru.salty.demoexam.util.Dialog;

import javax.swing.*;
import java.sql.SQLException;

public class MainForm extends BaseForm {

    private JPanel MainPanel;
    private JButton listButton;
    private JButton addButton;
    private JButton editButton;


    public MainForm() {
        super(600, 400);
        setVisible(true);
        setContentPane(MainPanel);
        initButtons();
    }

    private void initButtons() {

        listButton.addActionListener(e -> {
            dispose();
            new ListForm();
        });

        addButton.addActionListener(e -> {
            dispose();
            new CreateForm();
        });

        editButton.addActionListener(e -> {

            String s = JOptionPane.showInputDialog(this, "vvedite id", "edit", JOptionPane.QUESTION_MESSAGE);
            if (s == null) {
                return;
            }

            int id = -1;
            try {
                id = Integer.parseInt(s);
            } catch (Exception ex) {
                Dialog.showError(this, "Error");
                return;
            }

            Entity entity = null;
            try {
                entity = Manager.selectbyid(id);
            } catch (SQLException ex) {
                ex.printStackTrace();
                Dialog.showError(this, "Error");
                return;
            }

            if (entity == null) {
                Dialog.showError(this, "Error");
                return;
            }

            dispose();
            new EditForm(entity);

        });

    }

}
