/**
 * 
 */
package by.academy.entities;

import by.academy.Identified;

/**
 * @author Veronika
 *
 */
public class CarType implements Identified<Integer>{
	
	private Integer id_type = null;
    private String type;

    public Integer getId() {
        return id_type;
    }

    protected void setId(int id_type) { this.id_type = id_type;  }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
