import com.sun.xml.internal.ws.util.StringUtils;

public class AutoTradingSystem {
    StockerBrockerDriver driver;

    public void login(String id, String pass) {
        if (this.driver != null && isCorrectAuthData(id, pass)) {
            driver.login(id, pass);
        }
    }

    private static boolean isCorrectAuthData(String id, String pass) {
        return id != null && pass != null && !id.isEmpty() && !pass.isEmpty();
    }
}
