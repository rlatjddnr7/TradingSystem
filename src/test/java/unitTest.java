import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class unitTest {
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
}