package org.example.core.DAO;

import org.example.core.DataSourceProvider;
import org.example.core.entity.Match;

import javax.sql.DataSource;
import java.sql.*;

public class MatchDaoImpl {

    public void createMatchWithScore(Match match) {

//        DAO - Data Access Object
//        Méthode non optimale
        Connection conn = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            conn.setAutoCommit(false);

            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO MATCH_TENNIS (ID_EPREUVE, ID_VAINQUEUR, ID_FINALISTE) VALUES (?, ?, ?) ", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, match.getEpreuve().getId());
            preparedStatement.setLong(2, match.getVainqueur().getId());
            preparedStatement.setLong(3, match.getFinaliste().getId());

            preparedStatement.executeUpdate();

            ResultSet keys = preparedStatement.getGeneratedKeys();

            if (keys.next()) {
                match.setId(keys.getLong(1));
            }


            System.out.println("Match créé");

            PreparedStatement ps = conn.prepareStatement("INSERT INTO SCORE_VAINQUEUR (ID_MATCH, SET_1,SET_2,SET_3,SET_4,SET_5) VALUES (?,?,?,?,?,?) ", Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, match.getScore().getMatch().getId());
            ps.setByte(2, match.getScore().getSet1());
            ps.setByte(3, match.getScore().getSet2());

            if (match.getScore().getSet3() == null) {
                ps.setNull(4, Types.TINYINT);
            } else {
                ps.setByte(4, match.getScore().getSet3());
            }

            if (match.getScore().getSet4() == null) {
                ps.setNull(5, Types.TINYINT);
            } else {
                ps.setByte(5, match.getScore().getSet4());
            }

            if (match.getScore().getSet5() == null) {
                ps.setNull(6, Types.TINYINT);
            } else {
                ps.setByte(6, match.getScore().getSet5());
            }

            ps.executeUpdate();

            ResultSet keysScore = ps.getGeneratedKeys();

            if (keysScore.next()) {
                match.getScore().setId(keysScore.getLong(1));
            }

            System.out.println("Score créé");

            conn.commit();
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
