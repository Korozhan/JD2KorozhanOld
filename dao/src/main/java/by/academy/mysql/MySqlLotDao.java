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
import by.academy.entities.Lot;

public class MySqlLotDao extends AbstarctDao<Lot, Integer> {

	final static Logger LOG = Logger.getLogger(MySqlLotDao.class.getName());

	public MySqlLotDao(Connection connection) {
		super(connection);
	}

	private class PersistLot extends Lot {
//        public void setId(int id) {
//            super.setId(id);
//        }
    }
	
	@Override
	public Lot create(){
		Lot l = new Lot();
        return add(l);
	}

	@Override
	public String getCreateQuery() {
		return "INSERT INTO car_market.lot (id_user, id_role, id_car, id_bill) \n" +
                "VALUES (?, ?, ?, ?);";
	}

	@Override
	public String getSelectQuery() {
		return "SELECT id_user, id_role, id_car, id_bill FROM car_market.lot";
	}

	@Override
	public String getUpdateQuery() {
        return "UPDATE car_market.lot SET id_user = ?, id_role = ?, id_car = ?, id_bill = ?";
	}

	@Override
	public String getDeleteQuery() {
        return "DELETE FROM car_market.lot;";

	}

	@Override
	protected List<Lot> parseResultSet(ResultSet rs) throws Exception {
		LinkedList<Lot> result = new LinkedList<Lot>();
        try {
            while (rs.next()) {
                PersistLot lot = new PersistLot();
                lot.setUserId(rs.getInt("id_user"));
                lot.setRoleId(rs.getInt("id_role"));
                lot.setCarId(rs.getInt("id_car"));
                lot.setBillId(rs.getInt("id_bill"));
                result.add(lot);
            }
        } catch (Exception ex) {
        	LOG.log(Level.SEVERE, null, ex);
        }
        return result;
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement,
			Lot object) throws Exception {
		try {
            statement.setInt(1, object.getUserId());
            statement.setInt(2, object.getRoleId());
            statement.setInt(3, object.getCarId());
            statement.setInt(4, object.getBillId());
        } catch (Exception ex) {
        	LOG.log(Level.SEVERE, null, ex);
        }
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement,
			Lot object) throws Exception {
		try {
			
			int userId = (object.getUserId() == null) ? 0 : object.getUserId();
			int rolesId = (object.getRoleId() == null) ? 0 : object.getRoleId();
			int carId = (object.getCarId() == null) ? 0 : object.getCarId();
			int billId = (object.getBillId() == null) ? 0 : object.getBillId();
            statement.setInt(1, userId);
            statement.setInt(2, rolesId);
            statement.setInt(3, carId);
            statement.setInt(4, billId);
            
        } catch (Exception ex) {
        	LOG.log(Level.SEVERE, null, ex);
        }		
	}
	
}
