package ca.jrvs.apps.stockquote.dao;

public class Position {

    private String ticker; //id
    private int numOfShares;
    private double valuePaid; //total amount paid for shares


    public double getValuePaid() {
        return valuePaid;
    }

    public int getNumOfShares() {
        return numOfShares;
    }

    public String getTicker() {
        return ticker;
    }



    public void setNumOfShares(int numOfShares) {
        this.numOfShares = numOfShares;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public void setValuePaid(double valuePaid) {
        this.valuePaid = valuePaid;
    }
}
