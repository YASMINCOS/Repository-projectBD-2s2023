// AutorDAO.java
package br.ucb.bd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ucb.bd.database.DatabaseConnector;

public class AutorDAO {
    private static final String INSERT_AUTOR = "INSERT INTO Autor (nome_autor) VALUES (?)";
    private static final String SELECT_AUTORES = "SELECT * FROM Autor";

    public static void cadastrarAutor(String nomeAutor) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_AUTOR)) {
            preparedStatement.setString(1, nomeAutor);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void exibirAutores() {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_AUTORES);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id_autor") +
                        ", Nome: " + resultSet.getString("nome_autor"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
