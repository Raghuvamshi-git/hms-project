package com.learnJDBC;

import java.sql.*;
import java.util.Scanner;

public class Patient {

    private Connection con;
    private Scanner sc;

    public Patient(Connection con, Scanner sc) {
        this.con = con;
        this.sc = sc;
    }

    public void addPatient() {

        System.out.print("Enter Patient Name: ");
        String name = sc.next();

        System.out.print("Enter Age: ");
        int age = sc.nextInt();

        System.out.print("Enter Gender: ");
        String gender = sc.next();

        String query = "INSERT INTO patients (name, age, gender) VALUES (?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, gender);

            ps.executeUpdate();
            System.out.println("Patient Added Successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewPatients() {

        String query = "SELECT * FROM patients";

        try (PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\nPatients List");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                        rs.getString("name") + " | " +
                        rs.getInt("age") + " | " +
                        rs.getString("gender"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // CHECK PATIENT EXISTS
    public boolean getPatientByID(int id) {

        String query = "SELECT id FROM patients WHERE id = ?";

        try (PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
