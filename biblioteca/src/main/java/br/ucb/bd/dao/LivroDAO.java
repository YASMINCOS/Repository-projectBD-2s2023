// LivroDAO.java
package br.ucb.bd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ucb.bd.database.DatabaseConnector;

public class LivroDAO {
    private static final String INSERT_LIVRO = "INSERT INTO Livro (titulo_livro, id_autor, quantidade_disponivel) VALUES (?, ?, ?)";
    private static final String SELECT_LIVROS = "SELECT * FROM Livro";

    public static void cadastrarLivro(String tituloLivro, int idAutor, int quantidadeDisponivel) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LIVRO)) {
            preparedStatement.setString(1, tituloLivro);
            preparedStatement.setInt(2, idAutor);
            preparedStatement.setInt(3, quantidadeDisponivel);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void exibirLivros() {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LIVROS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id_livro") +
                        ", Título: " + resultSet.getString("titulo_livro") +
                        ", ID Autor: " + resultSet.getInt("id_autor") +
                        ", Quantidade Disponível: " + resultSet.getInt("quantidade_disponivel"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
