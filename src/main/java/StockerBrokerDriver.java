public interface StockerBrokerDriver {
    void login(String id, String pass);
    void buy(String code, int count , int price);
    void sell(StockVO stockVO);
    int getPrice (String code);
    void buyNiceTiming(String code, int price);
    void sellNiceTiming(String code, int count);
}