public class StockVO {
    private String code;
    private int count;
    private int price;

    public String getCode() {
        return code;
    }

    public int getCount() {
        return count;
    }

    public int getPrice() {
        return price;
    }

    public static StockVO builder(){
        return new StockVO();
    }

    public StockVO code(String code){
        this.code = code;
        return this;
    }

    public StockVO count(int count){
        this.count = count;
        return this;
    }

    public StockVO price(int price){
        this.price = price;
        return this;
    }
}
