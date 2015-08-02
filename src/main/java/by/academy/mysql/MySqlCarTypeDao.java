/**
 * 
 */
package by.academy.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import by.academy.AbstarctDao;
import by.academy.entities.CarType;

public class MySqlCarTypeDao extends AbstarctDao<CarType, Integer> {

	final static Logger LOG = Logger.getLogger(MySqlCarTypeDao.class.getName());

	public MySqlCarTypeDao(Connection connection) {
		super(connection);
	}

	private class PersistCarType extends CarType {
        public void setId(int id) {
            super.setId(id);
        }
    }
	
	@Override
	public CarType create(){
		CarType ct = new CarType();
        return add(ct);
	}

	@Override
	public String getCreateQuery() {
		return "INSERT INTO car_market.car_type (type) \n" +
                "VALUES (?);";
	}

	@Override
	public String getSelectQuery() {
		return "SELECT id_type, role FROM car_market.car_type";
	}

	@Override
	public String getUpdateQuery() {
        return "UPDATE car_market.car_type SET type= ? WHERE id_type= ?;";
	}

	@Override
	public String getDeleteQuery() {
        return "DELETE FROM car_market.car_type WHERE id_type= ?;";

	}

	@Override
	protected List<CarType> parseResultSet(ResultSet rs) throws Exception {
		LinkedList<CarType> result = new LinkedList<CarType>();
        try {
            while (rs.next()) {
                PersistCarType carType = new PersistCarType();
                carType.setId(rs.getInt("id_type"));
                carType.setType(rs.getString("type"));
                result.add(carType);
            }
        } catch (Exception ex) {
        	LOG.log(Level.SEVERE, null, ex);
        }
        return result;
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement,
			CarType object) throws Exception {
		try {
            statement.setString(1, object.getType());
            statement.setInt(2, object.getId());
        } catch (Exception ex) {
        	LOG.log(Level.SEVERE, null, ex);
        }
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement,
			CarType object) throws Exception {
		try {
            statement.setString(1, object.getType());
        } catch (Exception ex) {
        	LOG.log(Level.SEVERE, null, ex);
        }		
	}
}
