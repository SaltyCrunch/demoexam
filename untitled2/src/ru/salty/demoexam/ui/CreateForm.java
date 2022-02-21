package ru.salty.demoexam.ui;

import ru.salty.demoexam.Main;
import ru.salty.demoexam.entity.Entity;
import ru.salty.demoexam.manager.Manager;
import ru.salty.demoexam.util.BaseForm;
import ru.salty.demoexam.util.Dialog;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Date;

public class CreateForm  extends BaseForm {
    private JPanel MainPanel;
    private JTextField LastNameField;
    private JTextField FirstNameField;
    private JTextField PatronymicField;
    private JTextField BirthdayField;
    private JTextField RegistrationDateField;
    private JTextField EmailField;
    private JTextField PhoneField;
    private JTextField GenderCodeField;
    private JButton saveButton;
    private JButton backButton;

    public CreateForm() {
        super(500, 300);
        setVisible(true);
        setContentPane(MainPanel);
        initButtons();
        initFields();
    }

    private void initFields() {



    }

    private void initButtons() {

        saveButton.addActionListener(e -> {

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
                return;
            }

            Date date2 = null;
            try {
                date2 = Main.DATE_FORMAT.parse(RegistrationDateField.getText());
            } catch (Exception ex) {
                Dialog.showError(this, "Error");
                return;
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

            Entity entity = new Entity(LastName, FirstName, Patronymic, date1, date2, Email, Phone, GenderCode);

            try {
                Manager.insert(entity);
            } catch (SQLException ex) {
                ex.printStackTrace();
                Dialog.showError(this, "Error");
                return;
            }

            Dialog.showInfo(this, "nice");
            dispose();
            new MainForm();

        });

        backButton.addActionListener(e -> {
            dispose();
            new MainForm();
        });

    }

}
