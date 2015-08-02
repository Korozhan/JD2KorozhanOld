package by.academy;

import java.io.Serializable;

public interface GenericDao<T extends Identified<PK>, PK extends Serializable> {
	
	public T create() throws Exception;
	
	public T add(T object);
	
	public void delete(T object);
	
	public void update(T object);
	
	public T getById(Integer id);	
	
}
