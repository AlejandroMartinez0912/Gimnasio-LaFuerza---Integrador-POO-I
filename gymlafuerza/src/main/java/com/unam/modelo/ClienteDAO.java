package com.unam.modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;

import com.unam.repositorio.MySqlConnection;

public class ClienteDAO {
    private static final String INSERT_CLIENTE = "INSERT INTO cliente (nombre, apellido, fecha_nacimiento, sexo, fecha_ingreso) VALUES (?, ?, ?, ?, ?)";

    public void crearCliente(Cliente cliente) {
        try (Connection connection = MySqlConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLIENTE)) {

            preparedStatement.setString(1, cliente.getNombre());
            preparedStatement.setString(2, cliente.getApellido());
            preparedStatement.setDate(3, new Date(cliente.getFechaNacimiento().getTime()));
            preparedStatement.setString(4, cliente.getSexo());
            preparedStatement.setDate(5, new Date(cliente.getFechaIngreso().getTime()));

            preparedStatement.executeUpdate();

            System.out.println("Cliente creado exitosamente.");

        } catch (SQLException e) {
            System.out.println("Error al insertar cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
