package ca.jrvs.apps.stockquote.dao;


//import okhttp3.Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PositionDao implements CrudDao<Position, String> {

    private Connection c;
    private static final String INSERT = "INSERT INTO position (symbol, number_of_shares, value_paid, symbol_fk) VALUES (?, ?, ?, ?);";
    private static final String GET_ONE =  "SELECT symbol, number_of_shares, value_paid, symbol_fk FROM position WHERE symbol = ?;";
    private static final String GET_ALL =  "SELECT symbol, number_of_shares, value_paid, symbol_fk FROM position;";
    private static final String DELETE = "DELETE FROM position WHERE symbol = ?";
    private static final String DELETE_ALL = "DELETE FROM position";

    public PositionDao(Connection c){
        this.c = c;
    }

    @Override
    public Position save(Position entity) throws IllegalArgumentException {
        try(PreparedStatement statement = this.c.prepareStatement(INSERT)){
            statement.setString(1, entity.getTicker());
            statement.setInt(2, entity.getNumOfShares());
            statement.setDouble(3, entity.getValuePaid());
            statement.setString(4, entity.getSymbol_fk());
            statement.execute();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        finally {
            return null;
        }
    }

    @Override
    public Optional<Position> findById(String s) throws IllegalArgumentException {
        Position position = new Position();
        try(PreparedStatement statement = this.c.prepareStatement(GET_ONE)) {
            statement.setString(1, s);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                position.setTicker(rs.getString("symbol"));
                position.setNumOfShares(rs.getInt("number_of_shares"));
                position.setValuePaid(rs.getDouble("value_paid"));
                position.setSymbol_fk(rs.getString("symbol_fk"));
            }
            return Optional.of(position);
        }

        catch (SQLException e){
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Iterable<Position> findAll() {
        List<Position> list = new ArrayList<>();
        try(PreparedStatement statement = this.c.prepareStatement(GET_ALL)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Position position = new Position();
                position.setTicker(rs.getString("symbol"));
                position.setNumOfShares(rs.getInt("number_of_shares"));
                position.setValuePaid(rs.getDouble("value_paid"));
                position.setSymbol_fk(rs.getString("symbol_fk"));
                list.add(position);
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
        try(PreparedStatement statement= this.c.prepareStatement(DELETE_ALL)) {
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
}