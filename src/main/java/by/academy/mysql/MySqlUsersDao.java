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
import by.academy.entities.Users;

public class MySqlUsersDao extends AbstarctDao<Users, Integer> {
	
	final static Logger LOG = Logger.getLogger(MySqlUsersDao.class.getName());

	private class PersistUsers extends Users {
        public void setId(int id) {
            super.setId(id);
        }
    }

	public MySqlUsersDao(Connection connection) {
		super (connection);
	}

	 @Override
	    public String getSelectQuery() {
	        return "SELECT id_user, login, password FROM car_market.users";
	    }

	    @Override
	    public String getCreateQuery() {
	        return "INSERT INTO car_market.users (login, password) \n" +
	                "VALUES (?, ?);";
	    }

	    @Override
	    public String getUpdateQuery() {
	        return "UPDATE car_market.users SET login= ? password = ? WHERE id_user= ?;";
	    }

	    @Override
	    public String getDeleteQuery() {
	        return "DELETE FROM car_market.users WHERE id_user= ?;";
	    }

	    @Override
	    public Users create() {
	    	Users u = new Users();
	        return add(u);
	    }

	    @Override
	    protected List<Users> parseResultSet(ResultSet rs) throws Exception {
	        LinkedList<Users> result = new LinkedList<Users>();
	        try {
	            while (rs.next()) {
	                PersistUsers users = new PersistUsers();
	                users.setId(rs.getInt("id_user"));
	                users.setLogin(rs.getString("login"));
	                users.setPassword(rs.getString("password"));
	                result.add(users);
	            }
	        } catch (Exception ex) {
	        	LOG.log(Level.SEVERE, null, ex);
	        }
	        return result;
	    }

	    @Override
	    protected void prepareStatementForInsert(PreparedStatement statement, Users object) throws Exception {
	        try {
	            statement.setString(1, object.getLogin());
	            statement.setString(2, object.getPassword());
	        } catch (Exception ex) {
	        	LOG.log(Level.SEVERE, null, ex);
	        }
	    }

	    @Override
	    protected void prepareStatementForUpdate(PreparedStatement statement, Users object) throws Exception {
	        try {
	            statement.setString(1, object.getLogin());
	            statement.setString(2, object.getPassword());
	            statement.setInt(3, object.getId());
	        } catch (Exception ex) {
	        	LOG.log(Level.SEVERE, null, ex);
	        }
	    }
}
