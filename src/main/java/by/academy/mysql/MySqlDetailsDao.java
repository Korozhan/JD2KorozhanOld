package by.academy.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import by.academy.AbstarctDao;
import by.academy.entities.Details;

public class MySqlDetailsDao extends AbstarctDao<Details, Integer> {
	
	final static Logger LOG = Logger.getLogger(MySqlDetailsDao.class.getName());

	private class PersistDetails extends Details {
        public void setId(int id) {
            super.setId(id);
        }
    }

	public MySqlDetailsDao(Connection connection) {
		super (connection);
	}

	 @Override
	    public String getSelectQuery() {
	        return "SELECT id_details, country, mark, model, gearbox, mileage, fuel FROM car_market.details";
	    }

	    @Override
	    public String getCreateQuery() {
	        return "INSERT INTO car_market.details (country, mark, model, gearbox, mileage, fuel) \n" +
	                "VALUES (?, ?, ?, ?, ?, ?);";
	    }

	    @Override
	    public String getUpdateQuery() {
	        return "UPDATE car_market.details SET country= ? mark = ? model= ? gearbox = ? mileage= ? fuel = ? WHERE id_details= ?;";
	    }

	    @Override
	    public String getDeleteQuery() {
	        return "DELETE FROM car_market.details WHERE id_details= ?;";
	    }

	    @Override
	    public Details create() {
	    	Details d = new Details();
	        return add(d);
	    }

	    @Override
	    protected List<Details> parseResultSet(ResultSet rs) throws Exception {
	        LinkedList<Details> result = new LinkedList<Details>();
	        try {
	            while (rs.next()) {
	                PersistDetails details = new PersistDetails();
	                details.setId(rs.getInt("id_details"));
	                details.setCountry(rs.getString("country"));
	                details.setMark(rs.getString("mark"));
	                details.setModel(rs.getString("model"));
	                details.setGearbox(rs.getString("gearbox"));
	                details.setMileage(rs.getInt("mileage"));
	                details.setFuel(rs.getString("fuel"));
	                result.add(details);
	            }
	        } catch (Exception ex) {
	        	LOG.log(Level.SEVERE, null, ex);
	        }
	        return result;
	    }

	    @Override
	    protected void prepareStatementForInsert(PreparedStatement statement, Details object) throws Exception {
	        try {
	            statement.setString(1, object.getCountry());
	            statement.setString(2, object.getMark());
	            statement.setString(3, object.getModel());
	            statement.setString(4, object.getGearbox());
	            statement.setInt(5, object.getMileage());
	            statement.setString(6, object.getFuel());
	        } catch (Exception ex) {
	        	LOG.log(Level.SEVERE, null, ex);
	        }
	    }

	    @Override
	    protected void prepareStatementForUpdate(PreparedStatement statement, Details object) throws Exception {
	        try {
	        	statement.setString(1, object.getCountry());
	            statement.setString(2, object.getMark());
	            statement.setString(3, object.getModel());
	            statement.setString(4, object.getGearbox());
	            statement.setInt(5, object.getMileage());
	            statement.setString(6, object.getFuel());
	            statement.setInt(7, object.getId());
	        } catch (Exception ex) {
	        	LOG.log(Level.SEVERE, null, ex);
	        }
	    }
}
