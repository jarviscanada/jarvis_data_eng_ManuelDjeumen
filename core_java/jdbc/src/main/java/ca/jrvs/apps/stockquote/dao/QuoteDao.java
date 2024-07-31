package ca.jrvs.apps.stockquote.dao;

//import okhttp3.Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QuoteDao implements CrudDao<Quote, String> {

    private Connection c;
    private static final String INSERT = "INSERT INTO quote (symbol, open, high, low, price, volume, latest_trading_day,previous_close, change, change_percent, timestamp) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String GET_ONE =  "SELECT symbol, open, high, low, price, volume, latest_trading_day,previous_close, change, change_percent, timestamp FROM quote WHERE symbol = ?;";
    private static final String GET_ALL =  "SELECT symbol, open, high, low, price, volume, latest_trading_day,previous_close, change, change_percent, timestamp FROM quote;";
    private static final String DELETE = "DELETE FROM quote WHERE symbol = ?";
    private static final String DELETE_ALL = "DELETE FROM quote";

    public QuoteDao(Connection c){
        this.c = c;
    }

    @Override
    public Quote save(Quote entity) throws IllegalArgumentException {
        try(PreparedStatement statement = this.c.prepareStatement(INSERT)) {
            statement.setString(1, entity.getTicker());
            statement.setDouble(2, entity.getOpen());
            statement.setDouble(3, entity.getHigh());
            statement.setDouble(4, entity.getLow());
            statement.setDouble(5, entity.getPrice());
            statement.setInt(6, entity.getVolume());
            statement.setDate(7, entity.getLatestTradingDay());
            statement.setDouble(8, entity.getPreviousClose());
            statement.setDouble(9, entity.getChange());
            statement.setString(10, entity.getChangePercent());
            statement.setTimestamp(11, entity.getTimestamp());
            statement.execute();

        }
        catch (SQLException e){
            e.printStackTrace();
        }

        finally {
            return entity;
        }
    }

    @Override
    public Optional<Quote> findById(String s) throws IllegalArgumentException {
        Quote quote = new Quote();
        try(PreparedStatement statement = this.c.prepareStatement(GET_ONE)) {
            statement.setString(1, s);
            ResultSet rs = statement.executeQuery();
            quote.setTicker(rs.getString("symbol"));
            quote.setOpen(rs.getDouble("open"));
            quote.setHigh(rs.getDouble("high"));
            quote.setLow(rs.getDouble("low"));
            quote.setPrice(rs.getDouble("price"));
            quote.setVolume(rs.getInt("volume"));
            quote.setLatestTradingDay(rs.getDate("latestTradingDay"));
            quote.setPreviousClose(rs.getDouble("previousClose"));
            quote.setChange(rs.getDouble("change"));
            quote.setChangePercent(rs.getString("changePercent"));
            quote.setTimestamp(rs.getTimestamp("timestamp"));
            return Optional.of(quote);

        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return Optional.of(quote);


    }

    @Override
    public Iterable<Quote> findAll() {
        List<Quote> list = new ArrayList<>();
        try(PreparedStatement statement = this.c.prepareStatement(GET_ALL)){
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Quote quote = new Quote();
                quote.setTicker(rs.getString("symbol"));
                quote.setOpen(rs.getDouble("open"));
                quote.setHigh(rs.getDouble("high"));
                quote.setLow(rs.getDouble("low"));
                quote.setPrice(rs.getDouble("price"));
                quote.setVolume(rs.getInt("volume"));
                quote.setLatestTradingDay(rs.getDate("latestTradingDay"));
                quote.setPreviousClose(rs.getDouble("previousClose"));
                quote.setChange(rs.getDouble("change"));
                quote.setChangePercent(rs.getString("changePercent"));
                quote.setTimestamp(rs.getTimestamp("timestamp"));
                list.add(quote);
            }
            return list;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteById(String s) throws IllegalArgumentException {
        try(PreparedStatement statement = this.c.prepareStatement(DELETE)) {
            statement.setString(1, s);
            statement.execute();
        }

        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAll() {
        try (PreparedStatement statement = this.c.prepareStatement(DELETE_ALL)){
            statement.execute();
        }

        catch (SQLException e){
            e.printStackTrace();
        }
    }

    //implement all inherited methods
    //you are not limited to methods defined in CrudDao


    public Connection getC() {
        return c;
    }

    public void setC(Connection c) {
        this.c = c;
    }
}