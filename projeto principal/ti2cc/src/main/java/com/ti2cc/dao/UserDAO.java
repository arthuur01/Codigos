package com.ti2cc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ti2cc.model.User;

public class UserDAO extends DAO {
    public UserDAO() {
        super();
        conectar();
    }

    public boolean insert(User user) {
        boolean status = false;
        try {
            if (conexao != null) {
                String sql = "INSERT INTO Pessoa (Login, Email, Password, Status) VALUES (?, ?, ?, ?)";
                PreparedStatement st = conexao.prepareStatement(sql);
                // Define os parâmetros usando os métodos setXXX apropriados
                st.setString(1, user.getLogin());
                st.setString(2, user.getEmail());
                st.setString(3, user.getPassword());
                st.setBoolean(4, user.getStatus());
                st.executeUpdate();
                st.close();
                status = true;
            } else {
                System.err.println("A conexão não foi estabelecida corretamente.");
            }
        } catch (SQLException u) {
            status = false;
            u.printStackTrace(); // Adicione isso para imprimir o stack trace
        }
        return status;
    }

    public User get(String login) {
        User user = null;

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT Id, Login, Email, Status FROM Pessoa WHERE Login LIKE '" + login + "'";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                user = new User(rs.getLong("Id"), rs.getString("Login"), rs.getString("Email"), "NULL",
                        rs.getBoolean("Status"));
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return user;
    }

    public User get(Long id) {
        User user = null;

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT Id, Login, Email, Status FROM Pessoa WHERE id=" + id;
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                user = new User(rs.getLong("Id"), rs.getString("Login"), rs.getString("Email"), "NULL",
                        rs.getBoolean("Status"));
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return user;
    }

    public boolean autenticate(String login, String password) {
        boolean resp = false;

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM Pessoa WHERE Login LIKE '" + login + "' AND Password LIKE '" + password + "'";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            resp = rs.next();
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return resp;
    }

}
