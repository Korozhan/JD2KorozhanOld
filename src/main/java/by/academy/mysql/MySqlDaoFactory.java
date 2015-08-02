/**
 * 
 */
package by.academy.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import by.academy.AbstarctDao;
import by.academy.DaoFactory;
import by.academy.GenericDao;
import by.academy.entities.*;

public class MySqlDaoFactory implements DaoFactory<Connection>{
	
	final static Logger LOG = Logger.getLogger(MySqlDaoFactory.class.getName());

	private String user = "user";
    private String password = "1234";
    private String url = "jdbc:mysql://localhost:3306/car_market";
    private String driver = "com.mysql.jdbc.Driver";
    private Map<Class, DaoCreator> creators;

    public Connection getContext() throws Exception {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
        	LOG.log(Level.SEVERE, null, ex);
        }
        return  connection;
    }

    @Override
    public GenericDao getDao(Connection connection, Class dtoClass) throws Exception {
        DaoCreator creator = creators.get(dtoClass);
        if (creator == null) {
            try {
                throw new Exception("Dao object for " + dtoClass + " not found.");
            } catch (Exception ex) {
            	LOG.log(Level.SEVERE, null, ex);
            }
        }
        return creator.create(connection);
    }

    public MySqlDaoFactory() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        	LOG.log(Level.SEVERE, null, ex);
        }

        creators = new HashMap<Class, DaoCreator>();
        creators.put(Users.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySqlUsersDao(connection);
            }
        });
        creators.put(Roles.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySqlRolesDao(connection);
            }
        });
        creators.put(Bill.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySqlBillDao(connection);
            }
        });
        creators.put(CarType.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySqlCarTypeDao(connection);
            }
        });
        creators.put(Details.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySqlDetailsDao(connection);
            }
        });
        creators.put(Car.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySqlCarDao(connection);
            }
        });
        creators.put(Lot.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySqlLotDao(connection);
            }
        });
    }

}
