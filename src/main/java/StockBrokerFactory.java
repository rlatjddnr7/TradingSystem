public class StockBrokerFactory {
    private static KiwerDriver kiwerDriver;
    private static NemoDriver nemoDriver;

    public static StockerBrokerDriver get(String stockBroker){
        if(stockBroker.equals("Kiwer")){
            if(kiwerDriver == null)
                kiwerDriver = new KiwerDriver();
            return kiwerDriver;
        }
        else if(stockBroker.equals("Nemo")){
            if(nemoDriver == null)
                nemoDriver = new NemoDriver();
            return nemoDriver;
        }

        return null;
    }
}
