package ca.jrvs.apps.stockquote.dao;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "symbol",
        "open",
        "high",
        "low",
        "price",
        "volume",
        "latestTradingDay",
        "previousClose",
        "change",
        "changePercent"
})

public class Quote {
    @JsonProperty("01. symbol")
    private String ticker; //id
    @JsonProperty("02. open")
    private double open;
    @JsonProperty("03. high")
    private double high;
    @JsonProperty("04. low")
    private double low;
    @JsonProperty("05. price")
    private double price;
    @JsonProperty("06. volume")
    private int volume;
    @JsonProperty("07. latest trading day")
    private Date latestTradingDay;
    @JsonProperty("08. previous close")
    private double previousClose;
    @JsonProperty("09. change")
    private double change;
    @JsonProperty("10. change percent")
    private String changePercent;
    private Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now()); //time when the info was pulled


    public String getTicker() {
        return ticker;
    }

    public double getHigh() {
        return high;
    }

    public Date getLatestTradingDay() {
        return latestTradingDay;
    }

    public double getChange() {
        return change;
    }

    public double getLow() {
        return low;
    }

    public double getOpen() {
        return open;
    }

    public double getPreviousClose() {
        return previousClose;
    }

    public double getPrice() {
        return price;
    }

    public int getVolume() {
        return volume;
    }

    public String getChangePercent() {
        return changePercent;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public void setChangePercent(String changePercent) {
        this.changePercent = changePercent;
    }

    public void setLatestTradingDay(Date latestTradingDay) {
        this.latestTradingDay = latestTradingDay;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public void setPreviousClose(double previousClose) {
        this.previousClose = previousClose;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "ticker='" + getTicker() + '\n' +
                ", open=" + getOpen() + '\n' +
                ", high=" + getHigh() + '\n'+
                ", low=" + getLow() + '\n'+
                ", price=" + getPrice() + '\n'+
                ", volume=" + getVolume() + '\n'+
                ", latestTradingDay=" + getLatestTradingDay() + '\n'+
                ", previousClose=" + getPreviousClose() + '\n'+
                ", change=" + getChange() + '\n'+
                ", changePercent='" + getChangePercent() + '\n' +
                ", timestamp=" + getTimestamp() + '\n'+
                '}';
    }
}

