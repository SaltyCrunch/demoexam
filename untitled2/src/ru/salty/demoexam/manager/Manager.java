package ru.salty.demoexam.manager;

import ru.salty.demoexam.Main;
import ru.salty.demoexam.entity.Entity;

import java.sql.*;
import java.util.ArrayList;

public class Manager {

    public static void insert(Entity entity) throws SQLException {

        try (Connection c = Main.getConnection()) {

            String sql = "INSERT INTO client (LastName, FirstName, Patronymic, Birthday, RegistrationDate, Email, Phone, GenderCode) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getLastName());
            ps.setString(2, entity.getFirstName());
            ps.setString(3, entity.getPatronymic());
            ps.setTimestamp(4, new Timestamp(entity.getBirthday().getTime()));
            ps.setTimestamp(5, new Timestamp(entity.getRegistrationDate().getTime()));
            ps.setString(6, entity.getEmail());
            ps.setString(7, entity.getPhone());
            ps.setString(8, entity.getGenderCode());

            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();

            if (keys.next()) {
                entity.setId(keys.getInt(1));
                return;
            }
            throw new SQLException("Error");
        }

    }

    public static Entity selectbyid(int id) throws SQLException {

        try (Connection c = Main.getConnection()) {

            String sql = "SELECT * FROM client WHERE id = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return new Entity(
                        resultSet.getInt("id"),
                        resultSet.getString("LastName"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("Patronymic"),
                        resultSet.getTimestamp("Birthday"),
                        resultSet.getTimestamp("RegistrationDate"),
                        resultSet.getString("Email"),
                        resultSet.getString("Phone"),
                        resultSet.getString("GenderCode")
                );
            }
            return null;

        }

    }

    public static ArrayList<Entity> selectall() throws SQLException {

        try (Connection c = Main.getConnection()) {

            String sql = "SELECT * FROM client";
            Statement s = c.createStatement();

            ResultSet resultSet = s.executeQuery(sql);

            ArrayList<Entity> list = new ArrayList<>();

            while (resultSet.next()) {
                list.add(new Entity(
                        resultSet.getInt("id"),
                        resultSet.getString("LastName"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("Patronymic"),
                        resultSet.getTimestamp("Birthday"),
                        resultSet.getTimestamp("RegistrationDate"),
                        resultSet.getString("Email"),
                        resultSet.getString("Phone"),
                        resultSet.getString("GenderCode")
                ));
            }
            return list;

        }

    }

    public static void update(Entity entity) throws SQLException {

        try (Connection c = Main.getConnection()) {

            String sql = "UPDATE client SET LastName = ?, FirstName = ?, Patronymic = ?, Birthday = ?, RegistrationDate = ?, Email = ?, Phone = ?, GenderCode = ? WHERE id = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, entity.getLastName());
            ps.setString(2, entity.getFirstName());
            ps.setString(3, entity.getPatronymic());
            ps.setTimestamp(4, new Timestamp(entity.getBirthday().getTime()));
            ps.setTimestamp(5, new Timestamp(entity.getRegistrationDate().getTime()));
            ps.setString(6, entity.getEmail());
            ps.setString(7, entity.getPhone());
            ps.setString(8, entity.getGenderCode());
            ps.setInt(9, entity.getId());

            ps.executeUpdate();

        }

    }

    public static void delete(int id) throws SQLException {

        try (Connection c = Main.getConnection()) {

            String sql = "DELETE FROM client WHERE id = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();

        }

    }

    public static void deletebyclient(Entity entity) throws SQLException {

            delete(entity.getId());

    }

}
