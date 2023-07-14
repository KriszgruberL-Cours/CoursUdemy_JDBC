package org.example.core.repository;

import org.example.core.DataSourceProvider;
import org.example.core.entity.Joueur;
import org.example.core.entity.Match;

import javax.sql.DataSource;
import java.sql.*;

public class MatchRepositoryImpl {

    public void create(Match match) {
        Connection conn = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO MATCH_TENNIS (ID_EPREUVE, ID_VAINQUEUR, ID_FINALISTE) VALUES (?, ?, ?) ", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, match.getEpreuve().getId());
            preparedStatement.setLong(2, match.getVainqueur().getId());
            preparedStatement.setLong(3, match.getFinaliste().getId());

            preparedStatement.executeUpdate();

            ResultSet keys = preparedStatement.getGeneratedKeys();

            if(keys.next()){
                match.setId(keys.getLong(1));
            }


            System.out.println("Match créé");
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
