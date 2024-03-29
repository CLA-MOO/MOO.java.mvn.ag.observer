package miPrincipal;

import wsStockMarket.*;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class AppTest {

    StockMarket sm;
    Stock s1, s2, s3, s4;
    Trader t1, t2, t3;

    @Before public void setUp() {

// Creando el mercado de valores (acciones) ----------------------------
        sm = new StockMarket();

// Creando 4 acciones (Stocks) -----------------------------------------        

        s1 = new Stock("MSFT", 1.00, sm);
        s2 = new Stock("GOOG", 2.00, sm);
        s3 = new Stock("AAPL", 3.00, sm);
        s4 = new Stock("GOOG", 4.00, sm);

// Creando 3 accionistas (Trader) ---------------------------------------

        t1 = new Trader("t1", sm);
        t2 = new Trader("t2", sm);
        t3 = new Trader("t3", sm);

// Vinculando al accionista con la acción que le interesa observar --------        

        sm.register(t1, s1);
        sm.register(t1, s2);

        sm.register(t2, s1);
        sm.register(t2, s2);
        sm.register(t2, s4);

        sm.register(t3, s2);
        sm.register(t3, s3);
        sm.register(t3, s4);
    }

// t1 compra la acción s1 en $1.00 y se les notifica a los interesados en esa acción ------------
    @Test public void testTrading1() {
        String logTrade1 ="t1:The latest trade is Trader:t1 buy $1.0 Stock: MSFT\n";
        String logTrade2 ="t2:The latest trade is Trader:t1 buy $1.0 Stock: MSFT";

        boolean condicion = (logTrade1+logTrade2).equals(sm.trade(t1,s1, "buy",1.00));

        assertTrue(condicion);
    }

// t2 vende la acción s2 en $3.00 y se les notifica a los interesados en esa acción ------------
    @Test public void testTrading2() {
        String logTrade1 ="t1:The latest trade is Trader:t2 sell $3.0 Stock: GOOG\n";
        String logTrade2 ="t2:The latest trade is Trader:t2 sell $3.0 Stock: GOOG\n";
        String logTrade3 ="t3:The latest trade is Trader:t2 sell $3.0 Stock: GOOG";

        boolean condicion = (logTrade1+logTrade2+logTrade3).equals(sm.trade(t2,s2, "sell",3.00));

        assertTrue(condicion);
    }

// t3 vende la acción s3 en $2.50 y se les notifica a los interesados en esa acción ------------
    @Test public void testTrading3() {
        String logTrade ="t3:The latest trade is Trader:t3 sell $2.5 Stock: AAPL";

        boolean condicion = logTrade.equals(sm.trade(t3,s3, "sell",2.50));

        assertTrue(condicion);
    }

// t3 compra la acción s4 en $5.00 y se les notifica a los interesados en esa acción ------------

    @Test public void testTrading4() {
        String logTrade1 ="t2:The latest trade is Trader:t3 buy $5.0 Stock: GOOG\n";
        String logTrade2 ="t3:The latest trade is Trader:t3 buy $5.0 Stock: GOOG";

        boolean condicion = (logTrade1+logTrade2).equals(sm.trade(t3,s4, "buy",5.00));

        assertTrue(condicion);
    }
}
