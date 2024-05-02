public class MockDriver implements StockerBrokerDriver {

    @Override
    public void login(String id, String pass) {

    }

    @Override
    public void buy(String code, int count, int price) {

    }

    @Override
    public void sell(String code, int count, int price) {

    }

    @Override
    public int getPrice(String code) {
        return 0;
    }
}