package ca.jrvs.apps.stockquote.dao;

import java.util.Optional;

public class QuoteService {

    private QuoteDao dao;
    private QuoteHttpHelper httpHelper;

    public QuoteService(QuoteDao dao, QuoteHttpHelper httpHelper){
        this.dao = dao;
        this.httpHelper = httpHelper;
    }

    public void setDao(QuoteDao dao) {
        this.dao = dao;
    }

    public void setHttpHelper(QuoteHttpHelper httpHelper) {
        this.httpHelper = httpHelper;
    }

    public QuoteDao getDao() {
        return dao;
    }

    public QuoteHttpHelper getHttpHelper() {
        return httpHelper;
    }

    /**
     * Fetches latest quote data from endpoint
     * @param ticker
     * @return Latest quote information or empty optional if ticker symbol not found
     */
    public Optional<Quote> fetchQuoteDataFromAPI(String ticker) {
        //TO DO
        try {
            Quote quote = httpHelper.fetchQuoteInfo(ticker);
            return Optional.of(dao.save(quote));
        }

        catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

}
