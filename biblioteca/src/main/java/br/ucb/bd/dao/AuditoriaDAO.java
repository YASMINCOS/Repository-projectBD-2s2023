package br.ucb.bd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ucb.bd.database.DatabaseConnector;

public class AuditoriaDAO {
    private static final String SELECT_AUDITORIA = "SELECT * FROM Auditoria";

    public static void exibirAuditoria() {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_AUDITORIA);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id_auditoria") +
                        ", ID Usuário: " + resultSet.getInt("id_usuario") +
                        ", Ação: " + resultSet.getString("acao") +
                        ", Timestamp: " + resultSet.getTimestamp("timestamp"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
