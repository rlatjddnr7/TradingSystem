public class AutoTradingSystem {
    StockerBrockerDriver driver;

    void buy(String code, int count, int price) {
        driver.buy(code, count, price);
    }
}