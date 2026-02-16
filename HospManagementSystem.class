package com.learnJDBC;

import java.sql.*;
import java.util.Scanner;

public class HospitalManagementSystem {

    private static String url = "jdbc:mysql://localhost:3306/Hospital";
    private static String username = "root";
    private static String password = "password";

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);

        try {
            Connection connection = DriverManager.getConnection(url, username, password);

            Patient patient = new Patient(connection, scanner);
            Doctor doctor = new Doctor(connection);

            while (true) {
                System.out.println("\nHOSPITAL MANAGEMENT SYSTEM");
                System.out.println("1. Add Patient");
                System.out.println("2. View Patients");
                System.out.println("3. View Doctors");
                System.out.println("4. Book Appointment");
                System.out.println("5. Exit");
                System.out.print("Enter Your Choice: ");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        patient.addPatient();
                        break;

                    case 2:
                        patient.viewPatients();
                        break;

                    case 3:
                        doctor.viewDoctors();   // ✅ fixed
                        break;

                    case 4:
                        bookAppointment(patient, doctor, connection, scanner);
                        break;

                    case 5:
                        System.out.println("Thank You For Using Hospital Management System");
                        return;

                    default:
                        System.out.println("Invalid Choice");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ================= BOOK APPOINTMENT =================
    public static void bookAppointment(Patient patient, Doctor doctor,
                                       Connection connection, Scanner scanner) {

        System.out.print("Enter Patient ID: ");
        int patientId = scanner.nextInt();

        System.out.print("Enter Doctor ID: ");
        int doctorId = scanner.nextInt();

        System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
        String appointmentDate = scanner.next();

        // ✅ FIXED METHOD NAMES
        if (patient.getPatientByID(patientId) && doctor.getDoctorByID(doctorId)) {

            if (checkDoctorAvailability(doctorId, appointmentDate, connection)) {

                String query = "INSERT INTO appointments (patient_id, doctor_id, appointment_date) VALUES (?, ?, ?)";

                try (PreparedStatement ps = connection.prepareStatement(query)) {

                    ps.setInt(1, patientId);
                    ps.setInt(2, doctorId);
                    ps.setString(3, appointmentDate);

                    if (ps.executeUpdate() > 0) {
                        System.out.println("Appointment Booked Successfully");
                    } else {
                        System.out.println("Failed to Book Appointment");
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else {
                System.out.println("Doctor not available on this date");
            }

        } else {
            System.out.println("Doctor or Patient does not exist");
        }
    }

    public static boolean checkDoctorAvailability(int doctorId, String date, Connection connection) {

        String query = "SELECT COUNT(*) FROM appointments WHERE doctor_id = ? AND appointment_date = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, doctorId);
            ps.setString(2, date);

            ResultSet rs = ps.executeQuery();
            rs.next();

            return rs.getInt(1) == 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
