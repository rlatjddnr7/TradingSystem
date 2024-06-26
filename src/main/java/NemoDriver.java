
public class NemoDriver implements StockerBrokerDriver {
    private final NemoAPI nemoAPI = new NemoAPI();

    @Override
    public void login(String id, String pass) {
        nemoAPI.certification(id, pass);
    }

    @Override
    public void buy(String code, int count, int price) {
        nemoAPI.purchasingStock(code, count, price);
    }

    @Override
    public void sell(StockVO stockVO) {
        String code = stockVO.getCode();
        int price = stockVO.getPrice();
        int count = stockVO.getCount();

        nemoAPI.sellingStock(code, count, price);
    }

    @Override
    public int getPrice(String stockCode) {
        try {
            return nemoAPI.getMarketPrice(stockCode, 0);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void buyNiceTiming(String code, int price) {

    }

    @Override
    public void sellNiceTiming(String code, int count) {
    }
}
