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
import by.academy.entities.Roles;

public class MySqlRolesDao extends AbstarctDao<Roles, Integer> {

	final static Logger LOG = Logger.getLogger(MySqlRolesDao.class.getName());

	public MySqlRolesDao(Connection connection) {
		super(connection);
	}

	private class PersistRoles extends Roles {
        public void setId(int id) {
            super.setId(id);
        }
    }
	
	@Override
	public Roles create(){
		Roles r = new Roles();
        return add(r);
	}

	@Override
	public String getCreateQuery() {
		return "INSERT INTO car_market.roles (role) \n" +
                "VALUES (?);";
	}

	@Override
	public String getSelectQuery() {
		return "SELECT id_role, role FROM car_market.roles";
	}

	@Override
	public String getUpdateQuery() {
        return "UPDATE car_market.roles SET role= ? WHERE id_role= ?;";
	}

	@Override
	public String getDeleteQuery() {
        return "DELETE FROM car_market.roles WHERE id_role= ?;";

	}

	@Override
	protected List<Roles> parseResultSet(ResultSet rs) throws Exception {
		LinkedList<Roles> result = new LinkedList<Roles>();
        try {
            while (rs.next()) {
                PersistRoles roles = new PersistRoles();
                roles.setId(rs.getInt("id_user"));
                roles.setRole(rs.getString("role"));
                result.add(roles);
            }
        } catch (Exception ex) {
        	LOG.log(Level.SEVERE, null, ex);
        }
        return result;
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement,
			Roles object) throws Exception {
		try {
            statement.setString(1, object.getRole());
            statement.setInt(2, object.getId());
        } catch (Exception ex) {
        	LOG.log(Level.SEVERE, null, ex);
        }
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement,
			Roles object) throws Exception {
		try {
            statement.setString(1, object.getRole());
        } catch (Exception ex) {
        	LOG.log(Level.SEVERE, null, ex);
        }		
	}
	
}
