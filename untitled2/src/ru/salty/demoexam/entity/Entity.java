package ru.salty.demoexam.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor

public class Entity {

    private int id;
    private String LastName;
    private String FirstName;
    private String Patronymic;
    private Date Birthday;
    private Date RegistrationDate;
    private String Email;
    private String Phone;
    private String GenderCode;

    public Entity(String LastName, String FirstName, String Patronymic, Date Birthday, Date RegistrationDate, String Email, String Phone, String GenderCode) {
        this (-1, LastName, FirstName, Patronymic, Birthday, RegistrationDate, Email, Phone, GenderCode);
    }

}
