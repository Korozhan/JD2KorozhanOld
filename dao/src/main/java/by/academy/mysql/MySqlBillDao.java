/**
 * 
 */
package by.academy.mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import by.academy.AbstarctDao;
import by.academy.entities.Bill;

public class MySqlBillDao extends AbstarctDao<Bill, Integer> {

	final static Logger LOG = Logger.getLogger(MySqlBillDao.class.getName());

	public MySqlBillDao(Connection connection) {
		super(connection);
	}

	private class PersistBill extends Bill {
        public void setId(int id) {
            super.setId(id);
        }
    }
	
	@Override
	public Bill create(){
		Bill b = new Bill();
        return add(b);
	}

	@Override
	public String getCreateQuery() {
		return "INSERT INTO car_market.Bill (price, date) \n" +
                "VALUES (?, ?);";
	}

	@Override
	public String getSelectQuery() {
		return "SELECT id_bill, price, date FROM car_market.bill";
	}

	@Override
	public String getUpdateQuery() {
        return "UPDATE car_market.bill SET price, date= ? WHERE id_bill= ?;";
	}

	@Override
	public String getDeleteQuery() {
        return "DELETE FROM car_market.bill WHERE id_bill= ?;";

	}

	@Override
	protected List<Bill> parseResultSet(ResultSet rs) throws Exception {
		LinkedList<Bill> result = new LinkedList<Bill>();
        try {
            while (rs.next()) {
                PersistBill bill = new PersistBill();
                bill.setId(rs.getInt("id_bill"));
                bill.setPrice(rs.getInt("price"));
                bill.setDate(rs.getDate("date"));
                result.add(bill);
            }
        } catch (Exception ex) {
        	LOG.log(Level.SEVERE, null, ex);
        }
        return result;
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement,
			Bill object) throws Exception {
		try {
            Date sqlDate = convert(object.getDate());
            statement.setInt(1, object.getPrice());
            statement.setDate(2, sqlDate);
            statement.setInt(3, object.getId());
        } catch (Exception ex) {
        	LOG.log(Level.SEVERE, null, ex);
        }
	}

	private Date convert(java.util.Date date) {
		if (date == null) {
            return null;
        }
        return new java.sql.Date(date.getTime());
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement,
			Bill object) throws Exception {
		try {
            Date sqlDate = convert(object.getDate());
            statement.setInt(1, object.getPrice());
            statement.setDate(2, sqlDate);
        } catch (Exception ex) {
        	LOG.log(Level.SEVERE, null, ex);
        }		
	}

}
