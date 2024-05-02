import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class unitTest {
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
}