import ca.jrvs.apps.stockquote.dao.Quote;
import ca.jrvs.apps.stockquote.dao.QuoteDao;
import ca.jrvs.apps.stockquote.dao.QuoteHttpHelper;
import ca.jrvs.apps.stockquote.dao.QuoteService;
import org.junit.*;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.Assert.*;

public class QuoteService_UnitTest {

    @Mock
    Quote quote1;
    @Mock
    QuoteHttpHelper httpHelper;
    @Mock
    QuoteService quoteService;
    @Mock
    QuoteDao quoteDao;

    Optional<Quote> optional;


    @BeforeClass
    void init(){
        quoteService.setDao(quoteDao);
        quoteService.setHttpHelper(httpHelper);
        quote1.setChange(0.5);
        quote1.setChangePercent("12");
        quote1.setVolume(5);
        quote1.setPrice(10.2);
        quote1.setLow(1.5);
        quote1.setHigh(20.3);
        quote1.setOpen(6.5);
        quote1.setTicker("MSDT");
        quote1.setPreviousClose(5.5);
        quote1.setLatestTradingDay(null);
        optional = Optional.of(quote1);
    }

    @Test
    void fetchTest(){
        assertEquals(quoteService.fetchQuoteDataFromAPI("MSDT"), optional);

    }
}
