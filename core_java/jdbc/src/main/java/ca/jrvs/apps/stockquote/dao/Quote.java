package ca.jrvs.apps.stockquote.dao;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "ticker",
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
    @JsonProperty("ticker")
    private String ticker; //id
    @JsonProperty("open")
    private double open;
    @JsonProperty("high")
    private double high;
    @JsonProperty("low")
    private double low;
    @JsonProperty("price")
    private double price;
    @JsonProperty("volume")
    private int volume;
    @JsonProperty("latestTradingDay")
    private Date latestTradingDay;
    @JsonProperty("previousClose")
    private double previousClose;
    @JsonProperty("change")
    private double change;
    @JsonProperty("changePercent")
    private String changePercent;
    private Timestamp timestamp; //time when the info was pulled


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
        return Timestamp.valueOf(LocalDateTime.now());
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

