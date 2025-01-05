package DB;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

public class DatabaseConnectionTest {

    private DatabaseConnection databaseConnection;

    @BeforeEach
    public void setUp() {

        databaseConnection = new DatabaseConnection();
    }

    @Test
    public void testConnexionExitosa() throws SQLException {

        Connection mockConnection = mock(Connection.class);

        try (MockedStatic<DriverManager> driverManagerMockedStatic = mockStatic(DriverManager.class)) {
            driverManagerMockedStatic.when(() -> DriverManager.getConnection(anyString(), anyString(), anyString()))
                    .thenReturn(mockConnection);

            Connection connection = databaseConnection.getConnection();

            assertNotNull(connection);

            driverManagerMockedStatic.verify(() -> DriverManager.getConnection(anyString(), anyString(), anyString()), times(1));
        }
    }

}
