package ru.salty.demoexam.ui;

import ru.salty.demoexam.Main;
import ru.salty.demoexam.entity.Entity;
import ru.salty.demoexam.manager.Manager;
import ru.salty.demoexam.util.BaseForm;
import ru.salty.demoexam.util.Dialog;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Date;

public class EditForm extends BaseForm {

    private JTextField idField;
    private JPanel MainPanel;
    private JTextField LastNameField;
    private JTextField FirstNameField;
    private JTextField PatronymicField;
    private JTextField BirthdayField;
    private JTextField RegistrationDateField;
    private JTextField EmailField;
    private JTextField PhoneField;
    private JTextField GenderCodeField;
    private JButton editButton;
    private JButton deleteButton;
    private JButton backButton;
    private Entity entity;

    public EditForm(Entity entity) {
        super(600, 400);
        this.entity = entity;
        setVisible(true);
        setContentPane(MainPanel);
        initButtons();
        initFields();
    }

    private void initFields() {
        idField.setEditable(false);
        idField.setText(String.valueOf(entity.getId()));
        LastNameField.setText(entity.getLastName());
        FirstNameField.setText(entity.getFirstName());
        PatronymicField.setText(entity.getPatronymic());
        BirthdayField.setText(String.valueOf(entity.getBirthday()));
        RegistrationDateField.setText(String.valueOf(entity.getRegistrationDate()));
        EmailField.setText(entity.getEmail());
        PhoneField.setText(entity.getPhone());
        GenderCodeField.setText(entity.getGenderCode());

    }

    private void initButtons() {
        editButton.addActionListener(e -> {

            String LastName = LastNameField.getText();
            if (LastName.isEmpty() || LastName.length() > 50) {
                Dialog.showError(this, "Error");
                return;
            }

            String FirstName = FirstNameField.getText();
            if (FirstName.isEmpty() || FirstName.length() > 50) {
                Dialog.showError(this, "Error");
                return;
            }

            String Patronymic = PatronymicField.getText();
            if (Patronymic.isEmpty() || Patronymic.length() > 50) {
                Dialog.showError(this, "Error");
                return;
            }

            Date date1 = null;
            try {
                date1 = Main.DATE_FORMAT.parse(BirthdayField.getText());
            } catch (Exception ex) {
                Dialog.showError(this, "Error");
            }

            Date date2 = null;
            try {
                date2 = Main.DATE_FORMAT.parse(RegistrationDateField.getText());
            } catch (Exception ex) {
                Dialog.showError(this, "Error");
            }

            String Email = EmailField.getText();
            if (Email.isEmpty() || Email.length() > 50) {
                Dialog.showError(this, "Error");
                return;
            }

            String Phone = PhoneField.getText();
            if (Phone.isEmpty() || Phone.length() > 50) {
                Dialog.showError(this, "Error");
                return;
            }

            String GenderCode = GenderCodeField.getText();
            if (GenderCode.isEmpty() || GenderCode.length() > 50) {
                Dialog.showError(this, "Error");
                return;
            }

            entity.setLastName(LastName);
            entity.setFirstName(FirstName);
            entity.setPatronymic(Patronymic);
            entity.setBirthday(date1);
            entity.setRegistrationDate(date2);
            entity.setEmail(Email);
            entity.setPhone(Phone);
            entity.setGenderCode(GenderCode);

            try {
                Manager.update(entity);
                Dialog.showInfo(this, "nice");
                dispose();
                new MainForm();
            } catch (SQLException ex) {
                ex.printStackTrace();
                Dialog.showError(this, "Error");
            }

        });

        deleteButton.addActionListener(e -> {

            if (JOptionPane.showConfirmDialog(this, "sure?", "delete", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                try {
                    Manager.deletebyclient(entity);
                    Dialog.showInfo(this, "nice");
                    dispose();
                    new MainForm();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    Dialog.showError(this, "Error");
                }
            }

        });

        backButton.addActionListener(e -> {
            dispose();
            new MainForm();
        });

    }

}
