package Mensajeria;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import Cartel.Producto;
import DB.DatabaseConnection;
import Usuarios.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

public class PedidoTest {

    private Pedido pedido;
    private Cliente mockCliente;
    private List<Producto> mockProductos;

    @BeforeEach
    public void setUp() {

        mockCliente = mock(Cliente.class);
        when(mockCliente.getNombreUsuario()).thenReturn("Juan");
        when(mockCliente.getId()).thenReturn(1);
        when(mockCliente.getSaldo()).thenReturn(5000);

        Producto producto1 = mock(Producto.class);
        when(producto1.getNombre()).thenReturn("Empanada");
        when(producto1.getPrecio()).thenReturn(2000);

        Producto producto2 = mock(Producto.class);
        when(producto2.getNombre()).thenReturn("Jugo");
        when(producto2.getPrecio()).thenReturn(1500);

        mockProductos = new ArrayList<>();
        mockProductos.add(producto1);
        mockProductos.add(producto2);

        pedido = new Pedido();
        pedido.setFecha(java.time.LocalDate.now());
        pedido.setCliente(mockCliente);
        pedido.setProductos(mockProductos);
        pedido.setTotalPedido(3500);
    }

    @Test
    public void testGuardarPedidoEnBaseDeDatos() throws SQLException {

        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockStmtPedido = mock(PreparedStatement.class);
        PreparedStatement mockStmtProductos = mock(PreparedStatement.class);
        ResultSet mockGeneratedKeys = mock(ResultSet.class);

        try (MockedStatic<DatabaseConnection> mockedDatabaseConnection = mockStatic(DatabaseConnection.class)) {

            mockedDatabaseConnection.when(DatabaseConnection::getConnection).thenReturn(mockConnection);
            when(mockConnection.prepareStatement(anyString(), eq(PreparedStatement.RETURN_GENERATED_KEYS)))
                    .thenReturn(mockStmtPedido);
            when(mockConnection.prepareStatement(anyString())).thenReturn(mockStmtProductos);

            when(mockStmtPedido.getGeneratedKeys()).thenReturn(mockGeneratedKeys);
            when(mockGeneratedKeys.next()).thenReturn(true);
            when(mockGeneratedKeys.getInt(1)).thenReturn(1);

            pedido.guardarPedidoEnBaseDeDatos();

            verify(mockConnection).setAutoCommit(false);
            verify(mockStmtPedido).setDate(1, Date.valueOf(pedido.getFecha()));
            verify(mockStmtPedido).setInt(2, mockCliente.getId());
            verify(mockStmtPedido).setInt(3, pedido.getTotalPedido());
            verify(mockStmtPedido).executeUpdate();

            verify(mockStmtProductos, times(mockProductos.size())).setInt(eq(1), anyInt());
            verify(mockStmtProductos, times(mockProductos.size())).addBatch();
            verify(mockStmtProductos).executeBatch();

            verify(mockConnection).commit();

            assertNotNull(pedido.getId());
            assertEquals(1, pedido.getId());
        }
    }

    @Test
    public void testGenerarDetalles() {

        int saldoAnterior = 5000;

        when(mockCliente.getSaldo()).thenReturn(saldoAnterior - pedido.getTotalPedido());

        String detalles = pedido.generarDetalles(saldoAnterior);

        assertTrue(detalles.contains("Fecha del Pedido: " + pedido.getFecha()), "La fecha debe estar presente en los detalles.");
        assertTrue(detalles.contains("Cliente: Juan"), "El nombre del cliente debe estar presente en los detalles.");
        assertTrue(detalles.contains("Productos:"), "Debe incluirse la sección de productos.");
        assertTrue(detalles.contains("- Empanada: $2000"), "El producto 'Empanada' debe estar listado con su precio.");
        assertTrue(detalles.contains("- Jugo: $1500"), "El producto 'Jugo' debe estar listado con su precio.");
        assertTrue(detalles.contains("Total: $3500"), "El total del pedido debe estar correcto.");
        assertTrue(detalles.contains("Saldo anterior: $5000"), "El saldo anterior debe estar correcto.");
        assertTrue(detalles.contains("Saldo restante: $1500"), "El saldo restante debe coincidir con el cálculo correcto.");
    }

}