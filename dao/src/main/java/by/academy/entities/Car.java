/**
 * 
 */
package by.academy.entities;

import by.academy.Identified;

/**
 * @author Veronika
 *
 */
public class Car implements Identified<Integer>{

	private Integer id_car = null;
    private Integer id_type;
    private Integer id_details;

    public Integer getId() {
        return id_car;
    }

    protected void setId(int id_car) { this.id_car = id_car;  }

    public Integer getTypeId() {
        return id_type;
    }

    public void setTypeId(Integer id_type) {
        this.id_type = id_type;
    }

    public Integer getDetailsId() {
        return id_details;
    }

    public void setDetailsId(Integer id_details) { 
    	this.id_details = id_details;  }
}
