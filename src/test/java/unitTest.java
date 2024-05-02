import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class unitTest {

    @Mock
    MockDriver mockDriver;

    AutoTradingSystem autoTradingSystem;

    @BeforeEach
    void setUp() {
        autoTradingSystem = new AutoTradingSystem();
        mockDriver = new MockDriver();
    }

    @Test
    void createMockDriver() {
        assertNotNull(mockDriver);
    }

    @Test
    void selectStockBrokerTest(){
        StockerBrokerDriver driver;
        driver = StockBrokerFactory.get("Kiwer");
        assertThat(driver.getClass()).isNotNull().isEqualTo(KiwerDriver.class);

        driver = StockBrokerFactory.get("Nemo");
        assertThat(driver.getClass()).isNotNull().isEqualTo(NemoDriver.class);

        driver = StockBrokerFactory.get("NullTest");
        assertThat(driver).isNull();
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
        StockVO stockVO = StockVO.builder()
                .code("code")
                .price(2)
                .count(3);

        assertDoesNotThrow(()-> {
            mockDriver.sell(stockVO);
        });
    }

    @Test
    void getPriceTest() {
        assertEquals(0, mockDriver.getPrice("code"));
    }

    @Test
    void sellNiceTimingTestSuccess(){
        StockerBrokerDriver mockDriver = mock(MockDriver.class);
        autoTradingSystem.selectStockBroker(mockDriver);

        when(mockDriver.getPrice("code"))
                .thenReturn(3)
                .thenReturn(2)
                .thenReturn(2)
                .thenReturn(1);

        assertThat(autoTradingSystem.sellNiceTiming("code", 1))
                .isEqualTo(false);
        assertThat(autoTradingSystem.sellNiceTiming("code", 1))
                .isEqualTo(false);
        assertThat(autoTradingSystem.sellNiceTiming("code", 1))
                .isEqualTo(true);
        assertThat(autoTradingSystem.sellNiceTiming("code", 1))
                .isEqualTo(true);
    }

    @Test
    void sellNiceTimingTestFail(){
        StockerBrokerDriver mockDriver = mock(MockDriver.class);
        autoTradingSystem.selectStockBroker(mockDriver);

        when(mockDriver.getPrice("code"))
                .thenReturn(3)
                .thenReturn(2)
                .thenReturn(3)
                .thenReturn(1);

        assertThat(autoTradingSystem.sellNiceTiming("code", 1))
                .isEqualTo(false);
        assertThat(autoTradingSystem.sellNiceTiming("code", 1))
                .isEqualTo(false);
        assertThat(autoTradingSystem.sellNiceTiming("code", 1))
                .isEqualTo(false);
        assertThat(autoTradingSystem.sellNiceTiming("code", 1))
                .isEqualTo(false);
    }
  
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