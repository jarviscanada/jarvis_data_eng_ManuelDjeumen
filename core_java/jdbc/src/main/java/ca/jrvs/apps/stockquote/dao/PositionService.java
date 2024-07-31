package ca.jrvs.apps.stockquote.dao;

import java.util.Optional;

public class PositionService {

    private PositionDao dao;

    public PositionService(PositionDao dao){
        this.dao = dao;
    }

    public void setDao(PositionDao dao) {
        this.dao = dao;
    }

    public PositionDao getDao() {
        return dao;
    }

    /**
     * Processes a buy order and updates the database accordingly
     * @param ticker
     * @param numberOfShares
     * @param price
     * @return The position in our database after processing the buy
     */
    public Position buy(String ticker, int numberOfShares, double price) {
        //TO DO
        Position position = new Position();
        position.setTicker(ticker);
        position.setNumOfShares(numberOfShares);
        position.setValuePaid(price);
        return dao.save(position);
   }

    /**
     * Sells all shares of the given ticker symbol
     * @param ticker
     */
    public void sell(String ticker) {
        //TO DO
        if(!dao.findById(ticker).isEmpty()){
            Position position = dao.findById(ticker).get();
            dao.deleteById(position.getTicker());
        }
    }
}