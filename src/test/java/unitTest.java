import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class unitTest {

    @Mock
    MockDriver mockDriver;

    @BeforeEach
    void setUp() {
        mockDriver = new MockDriver();
    }

    @Test
    void createMockDriver() {
        assertNotNull(mockDriver);
    }

    @Test
    void loginTest() {
        mockDriver.login("id", "pass");

    }

    @Test
    void buyTest() {
        mockDriver.buy("code", 2, 3);
    }

    @Test
    void sellTest() {
        mockDriver.sell("code", 2, 3);
    }

    @Test
    void getPriceTest() {
        assertEquals(0, mockDriver.getPrice("code"));
    }

    @Test
    void buyNiceTimingTest() throws InterruptedException {
        doReturn(100).when(mockDriver).getPrice("code");
        Thread.sleep(1);
        doReturn(200).when(mockDriver).getPrice("code");
        mockDriver.buyNiceTiming("code", 200);

        verify(mockDriver, times(1)).buy("code", 2, 200);
    }

    @Test
    void sellNiceTimingTest() throws InterruptedException {
        doReturn(200).when(mockDriver).getPrice("code");
        Thread.sleep(1);
        doReturn(100).when(mockDriver).getPrice("code");
        mockDriver.buyNiceTiming("code", 200);

        verify(mockDriver, times(1)).sell("code", 2, 100);

    }
}