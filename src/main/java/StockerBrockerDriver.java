public interface StockerBrockerDriver {

    void login(String id, String pass);
    void buy(String code, int count , int price);
    void sell( String code , int count , int price);
    int getPrice (String code);
}