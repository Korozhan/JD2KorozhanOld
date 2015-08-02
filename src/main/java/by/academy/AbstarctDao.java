package by.academy;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import by.academy.entities.Car;
import by.academy.entities.Users;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public abstract class AbstarctDao<T extends Identified<PK>, PK extends Integer> implements GenericDao<T, PK>{
	
	private Connection connection;
	
	public abstract String getCreateQuery();//INSERT INTO [Table] ([column, column, ...]) VALUES (?, ?, ...);

	public abstract String getSelectQuery();

	public abstract String getUpdateQuery();//UPDATE [Table] SET [column = ?, column = ?, ...] WHERE id = ?;
	public abstract String getDeleteQuery();//DELETE FROM [Table] WHERE id= ?;

	protected abstract List<T> parseResultSet(ResultSet rs) throws Exception;
	protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws Exception;
    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws Exception;

	
	@Override
    public T add(T object) {
		T persistInstance = null;
        String sql = getCreateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareStatementForInsert(statement, object);
            int count = statement.executeUpdate();
            if (count != 1) {
            	try {
                    throw new Exception("On add modify more then 1 record: " + count);
                } catch (Exception ex) {
                    Logger.getLogger(AbstarctDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(AbstarctDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        sql = getSelectQuery() + " WHERE id = last_insert_id();";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            List<T> list = parseResultSet(rs);
            if ((list == null) || (list.size() != 1)) {
                try {
                    throw new Exception("Exception on findByPK new persist data.");
                } catch (Exception ex) {
                    Logger.getLogger(AbstarctDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            persistInstance = list.iterator().next();
        } catch (Exception ex) {
            Logger.getLogger(AbstarctDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return persistInstance;
    }
	
	@Override
    public void update(T object) {
		String sql = getUpdateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            prepareStatementForUpdate(statement, object);
            int count = statement.executeUpdate();
            if (count != 1) {
                try {
                    throw new Exception("On update modify more then 1 record: " + count);
                } catch (Exception ex) {
                    Logger.getLogger(AbstarctDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(AbstarctDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(T object) {
        String sql = getDeleteQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try {
                statement.setObject(1, object.getId());
            } catch (Exception ex) {
                Logger.getLogger(AbstarctDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            int count = statement.executeUpdate();
            if (count != 1) {
                try {
                    throw new Exception("On delete modify more then 1 record: " + count);
                } catch (Exception ex) {
                    Logger.getLogger(AbstarctDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            statement.close();
        } catch (Exception ex) {
            Logger.getLogger(AbstarctDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	
	@Override
	public T getById(Integer id) {
		List<T> list = null;
        String sql = getSelectQuery();
        sql += " WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception ex) {
            Logger.getLogger(AbstarctDao.class.getName()).log(Level.SEVERE, null, ex);
        } 
        if (list == null || list.size() == 0) {
        	 try {
        		 throw new Exception("Record with PK = " + id + " not found.");
             } catch (Exception ex) {
                 Logger.getLogger(AbstarctDao.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
        if (list.size() > 1) {
        	try {
                throw new Exception("Received more than one record.");
            } catch (Exception ex) {
                Logger.getLogger(AbstarctDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list.iterator().next();
		
	}
	
	public AbstarctDao(Connection connection) {
	    this.connection = connection;
	}




	
}


