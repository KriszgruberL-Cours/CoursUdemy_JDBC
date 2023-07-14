package org.example.core.repository;

import org.apache.commons.dbcp2.BasicDataSource;
import org.example.core.DataSourceProvider;
import org.example.core.entity.Joueur;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JoueurRepositoryImpl {

    public void create(Joueur joueur) {
        Connection conn = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO JOUEUR (NOM, PRENOM, SEXE) VALUES (?, ?, ?) ", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, joueur.getNom());
            preparedStatement.setString(2, joueur.getPrenom());
            preparedStatement.setString(3, joueur.getSexe().toString());

            preparedStatement.executeUpdate();

            ResultSet keys = preparedStatement.getGeneratedKeys();

            if(keys.next()){
                joueur.setId(keys.getLong(1));
            }


            System.out.println("Joueur créé");
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

    public void update(Joueur joueur) {
        Connection conn = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
            conn = dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE JOUEUR SET NOM=?, PRENOM=?, SEXE=?  WHERE ID=? ");
            preparedStatement.setString(1, joueur.getNom());
            preparedStatement.setString(2, joueur.getPrenom());
            preparedStatement.setString(3, joueur.getSexe().toString());
            preparedStatement.setLong(4, joueur.getId());

            preparedStatement.executeUpdate();


            System.out.println("Joueur mise à jour");
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

    public void delete(Long id) {
        Connection conn = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM JOUEUR WHERE ID=? ");
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();


            System.out.println("Joueur supprimé");
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

    public Joueur getById(Long id) {
        Connection conn = null;
        Joueur joueur = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement("SELECT NOM, PRENOM,SEXE FROM JOUEUR WHERE ID=? ");
            preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                joueur = new Joueur();
                joueur.setId(id);
                joueur.setNom(rs.getString("NOM"));
                joueur.setPrenom(rs.getString("PRENOM"));
                joueur.setSexe(rs.getString("SEXE").charAt(0));
            }
            System.out.println("Joueur lu");

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

        return joueur;
    }

    public List<Joueur> list() {
        Connection conn = null;
        List<Joueur> joueurList = new ArrayList<>();
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement("SELECT NOM, PRENOM,SEXE FROM JOUEUR");

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Joueur joueur = new Joueur();
                joueur.setId(rs.getLong("ID"));
                joueur.setNom(rs.getString("NOM"));
                joueur.setPrenom(rs.getString("PRENOM"));
                joueur.setSexe(rs.getString("SEXE").charAt(0));
                joueurList.add(joueur);
            }
            System.out.println("Joueurs lus");

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

        return joueurList;
    }

}
