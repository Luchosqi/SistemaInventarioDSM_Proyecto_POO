package Usuarios;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import DB.DatabaseConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteTest {

    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        cliente = new Cliente();
        cliente.setId(1); 
        cliente.setSaldo(1000);
    }

    @Test
    public void testActualizarSaldoEnBaseDeDatos_Exitoso() throws SQLException {

        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockStatement = mock(PreparedStatement.class);

        try (MockedStatic<DatabaseConnection> mockedDatabaseConnection = mockStatic(DatabaseConnection.class)) {

            mockedDatabaseConnection.when(DatabaseConnection::getConnection).thenReturn(mockConnection);

            when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
            when(mockStatement.executeUpdate()).thenReturn(1);

            boolean resultado = cliente.actualizarSaldoEnBaseDeDatos();

            assertTrue(resultado, "El saldo debería haberse actualizado exitosamente.");

            verify(mockStatement).setInt(1, cliente.getSaldo());
            verify(mockStatement).setInt(2, cliente.getId());
            verify(mockStatement).executeUpdate();
        }
    }


    @Test
    public void verificarCredenciales_Test() throws SQLException {

        String testNombreUsuario = "validUser";
        String testContrasenia = "validPassword";

        ResultSet mockedResultSet = mock(ResultSet.class);
        when(mockedResultSet.next()).thenReturn(true);
        when(mockedResultSet.getInt("id")).thenReturn(1);
        when(mockedResultSet.getString("nombre_usuario")).thenReturn(testNombreUsuario);
        when(mockedResultSet.getString("contrasenia")).thenReturn(testContrasenia);
        when(mockedResultSet.getString("correo")).thenReturn("test@example.com");
        when(mockedResultSet.getString("rol")).thenReturn("Cliente");
        when(mockedResultSet.getInt("saldo")).thenReturn(100);

        PreparedStatement mockedPreparedStatement = mock(PreparedStatement.class);
        when(mockedPreparedStatement.executeQuery()).thenReturn(mockedResultSet);

        Connection mockedConnection = mock(Connection.class);
        when(mockedConnection.prepareStatement(anyString())).thenReturn(mockedPreparedStatement);

        try (MockedStatic<DatabaseConnection> mockedDatabaseConnection = mockStatic(DatabaseConnection.class)) {
            when(DatabaseConnection.getConnection()).thenReturn(mockedConnection);

            Cliente cliente = new Cliente();

            boolean result = cliente.verificarCredenciales(testNombreUsuario, testContrasenia);

            assertTrue(result);
            assertEquals(1, cliente.getId());
            assertEquals(testNombreUsuario, cliente.getNombreUsuario());
            assertEquals(testContrasenia, cliente.getContrasenia());
            assertEquals("test@example.com", cliente.getCorreo());
            assertEquals("Cliente", cliente.getRol());
            assertEquals(100, cliente.getSaldo());

            verify(mockedPreparedStatement).setString(1, testNombreUsuario);
            verify(mockedPreparedStatement).setString(2, testContrasenia);
            verify(mockedPreparedStatement).executeQuery();
            verify(mockedResultSet).next();
        }
    }

}
