public class NemoDriver implements StockerBrockerDriver {
    private final NemoAPI nemoAPI;

    public NemoDriver() {
        this.nemoAPI = new NemoAPI();
    }

    @Override
    public void login(String id, String pass) {
        nemoAPI.certification(id, pass);
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
