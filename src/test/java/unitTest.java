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
        AutoTradingSystem autoTradingSystem = spy(AutoTradingSystem.class);
        MockDriver mockDriver = mock(MockDriver.class);
        autoTradingSystem.selectStockBroker(mockDriver);
        String code = "code";
        int myPrice = 500;
        int checkFirstPrice = 200;
        int checkSecondPrice = 1000;
        doReturn(checkFirstPrice, checkSecondPrice).when(mockDriver).getPrice(code);

        autoTradingSystem.buyNiceTiming(code, myPrice);

        verify(mockDriver, times(2)).getPrice(code);
        verify(mockDriver, times(1)).buy(code, checkSecondPrice / myPrice, myPrice);
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