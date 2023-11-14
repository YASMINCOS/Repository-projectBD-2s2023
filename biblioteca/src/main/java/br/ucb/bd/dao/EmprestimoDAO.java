// EmprestimoDAO.java
package br.ucb.bd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ucb.bd.database.DatabaseConnector;

public class EmprestimoDAO {
    private static final String INSERT_EMPRESTIMO = "INSERT INTO Emprestimo (id_livro, data_emprestimo, data_devolucao) VALUES (?, ?, ?)";
    private static final String DELETE_EMPRESTIMO = "DELETE FROM Emprestimo WHERE id_emprestimo = ?";
    private static final String SELECT_EMPRESTIMOS = "SELECT * FROM Emprestimo";

    public static void realizarEmprestimo(int idLivro, String dataEmprestimo, String dataDevolucao) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPRESTIMO)) {
            preparedStatement.setInt(1, idLivro);
            preparedStatement.setString(2, dataEmprestimo);
            preparedStatement.setString(3, dataDevolucao);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void devolucaoLivro(int idEmprestimo) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMPRESTIMO)) {
            preparedStatement.setInt(1, idEmprestimo);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void exibirEmprestimos() {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPRESTIMOS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id_emprestimo") +
                        ", ID Livro: " + resultSet.getInt("id_livro") +
                        ", Data Empréstimo: " + resultSet.getString("data_emprestimo") +
                        ", Data Devolução: " + resultSet.getString("data_devolucao"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
