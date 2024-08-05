import ca.jrvs.apps.stockquote.dao.Position;
import ca.jrvs.apps.stockquote.dao.PositionDao;
import ca.jrvs.apps.stockquote.dao.PositionService;
import org.junit.*;
import org.mockito.Mock;

import java.sql.Connection;

import static org.junit.Assert.*;

public class PositionService_UnitTest {


    @Mock
    Position position1;
    @Mock
    Position position2;
    @Mock
    Position position3;
    @Mock
    Connection c;
    @Mock
    PositionDao dao;

    PositionService positionService = new PositionService(dao);


    @BeforeClass
    void init(){
        position1.setValuePaid(12); position1.setTicker("MSDT"); position1.setNumOfShares(5);
        position2.setValuePaid(14.5); position2.setTicker("MSDT"); position2.setNumOfShares(2);
        position3.setValuePaid(8.3); position3.setTicker("MSDT"); position3.setNumOfShares(4);
        dao.setC(c);

    }

    @Test
    void testsBuy(){
        assertEquals(positionService.buy("MSDT", 5, 12), position1);
        assertEquals(positionService.buy("MSDT", 2, 14.5), position2);
        assertEquals(positionService.buy("MSDT", 4, 8.3), position3);
    }

}
