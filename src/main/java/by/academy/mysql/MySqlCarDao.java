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
import by.academy.entities.Car;

public class MySqlCarDao extends AbstarctDao<Car, Integer> {

	final static Logger LOG = Logger.getLogger(MySqlCarDao.class.getName());

	public MySqlCarDao(Connection connection) {
		super(connection);
	}

	private class PersistCar extends Car {
        public void setId(int id) {
            super.setId(id);
        }
    }
	
	@Override
	public Car create(){
		Car c = new Car();
        return add(c);
	}

	@Override
	public String getCreateQuery() {
		return "INSERT INTO car_market.car (id_type, id_details) \n" +
                "VALUES (?, ?);";
	}

	@Override
	public String getSelectQuery() {
		return "SELECT id_car, id_type, id_details FROM car_market.car";
	}

	@Override
	public String getUpdateQuery() {
        return "UPDATE car_market.car SET id_type = ?, id_details = ? WHERE id_car= ?;";
	}

	@Override
	public String getDeleteQuery() {
        return "DELETE FROM car_market.car WHERE id_car= ?;";

	}

	@Override
	protected List<Car> parseResultSet(ResultSet rs) throws Exception {
		LinkedList<Car> result = new LinkedList<Car>();
        try {
            while (rs.next()) {
                PersistCar car = new PersistCar();
                car.setId(rs.getInt("id_car"));
                car.setTypeId(rs.getInt("id_type"));
                car.setDetailsId(rs.getInt("id_details"));
                result.add(car);
            }
        } catch (Exception ex) {
        	LOG.log(Level.SEVERE, null, ex);
        }
        return result;
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement,
			Car object) throws Exception {
		try {
            statement.setInt(1, object.getTypeId());
            statement.setInt(2, object.getDetailsId());
        } catch (Exception ex) {
        	LOG.log(Level.SEVERE, null, ex);
        }
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement,
			Car object) throws Exception {
		try {
			
			int typeId = (object.getTypeId() == null) ? 0 : object.getTypeId();
			int detailsId = (object.getDetailsId() == null) ? 0 : object.getDetailsId();

            statement.setInt(1, typeId);
            statement.setInt(2, detailsId);
            
        } catch (Exception ex) {
        	LOG.log(Level.SEVERE, null, ex);
        }		
	}
	

}
