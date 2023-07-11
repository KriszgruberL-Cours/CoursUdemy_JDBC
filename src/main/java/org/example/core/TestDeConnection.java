package org.example.core;

import java.sql.*;

public class TestDeConnection {
    public static void main(String... args) {
        Connection conn = null;
        try {

            //MySQL driver MySQL Connector
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris", "root", "root");
            conn.setAutoCommit(false);

//            SELECT
//            PreparedStatement preparedStatement = conn.prepareStatement("SELECT NOM, PRENOM, ID, SEXE FROM JOUEUR WHERE ID=?");
//            long identifiant = 32L;
//            preparedStatement.setLong(1,identifiant );
//
//            ResultSet rs = preparedStatement.executeQuery();
//
//            if (rs.next()){
//                final String nom = rs.getString("NOM");
//                final String prenom = rs.getString("PRENOM");
//                final long id = rs.getLong("ID");
//                final String sexe = rs.getString("SEXE").equals("H") ? "Le joueur representé " : "La joueuse representée ";
//
//                System.out.println(sexe + "par le numéro " +id+" est " +prenom +" "+ nom );
//            } else {
//                System.out.println("Il n'y a pas d'enregistrement à cet id") ;
//            }


//            UPDATE
//            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE JOUEUR SET NOM=?, PRENOM=?  WHERE ID=?");
//            long identifiant = 24L;
//            String prenom = "Sara";
//            String nom = "Errani";
//            preparedStatement.setString(1,nom);
//            preparedStatement.setString(2,prenom);
//            preparedStatement.setLong(3,identifiant );
//
//            int nbUpdated = preparedStatement.executeUpdate();
//
//            conn.commit();
//
//            System.out.println("nb de modification : " + nbUpdated);


//            INSERT
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO JOUEUR (NOM, PRENOM, SEXE) VALUES (?, ?, ?) ");
            String nom = "Capriati";
            String prenom = "Jennifer";
            String sexe = "F";
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, sexe);

            preparedStatement.executeUpdate();

            nom = "Johannson";
            prenom = "Thomas";
            sexe = "H";
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, sexe);

            preparedStatement.executeUpdate();

            conn.commit();

//            Ne sont pas nécessaire lorsque l'on ferme la connexion dans le finally
//            rs.close();
//            preparedStatement.close();

            System.out.println("success");
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

