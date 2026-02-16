package com.learnJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctor {

    private Connection con;

    public Doctor(Connection con) {
        this.con = con;
    }

    // VIEW DOCTORS
    public void viewDoctors() {

        String query = "SELECT * FROM doctors";

        try (PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\nDoctors List");
            System.out.println("+------------+--------------------+------------------+");
            System.out.println("| Doctor ID  | Name               | Specialization   |");
            System.out.println("+------------+--------------------+------------------+");

            while (rs.next()) {
                System.out.printf("| %-10d | %-18s | %-16s |\n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("specialization"));
            }

            System.out.println("+------------+--------------------+------------------+");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // CHECK DOCTOR EXISTS
    public boolean getDoctorByID(int id) {

        String query = "SELECT id FROM doctors WHERE id = ?";

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
