import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.assertj.core.api.Assertions.assertThat;
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
    void buyNiceTimingTest() throws InterruptedException {
        doReturn(100).when(mockDriver).getPrice("code");
        Thread.sleep(1);
        doReturn(200).when(mockDriver).getPrice("code");

        verify(mockDriver, times(1)).buy("code", 2, 3);
    }

    @Test
    void sellNiceTimingTest() throws InterruptedException {
        doReturn(200).when(mockDriver).getPrice("code");
        Thread.sleep(1);
        doReturn(100).when(mockDriver).getPrice("code");

        verify(mockDriver, times(1)).sell("code", 2, 3);

    }
}