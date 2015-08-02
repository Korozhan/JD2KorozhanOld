/**
 * 
 */
package by.academy.entities;

import by.academy.Identified;

/**
 * @author Veronika
 *
 */
public class Lot implements Identified<Integer>{
	
	private Integer id_user;
    private Integer id_role;
    private Integer id_car;
    private Integer id_bill;

    public Integer getUserId() {
        return id_user;
    }

    public void setUserId(Integer id_user) {
        this.id_user = id_user;
    }

    public Integer getRoleId() {
        return id_role;
    }

    public void setRoleId(Integer id_role) { 
    	this.id_role = id_role;  }

    public Integer getCarId() {
        return id_car;
    }

    public void setCarId(Integer id_car) {
        this.id_car = id_car;
    }

    public Integer getBillId() {
        return id_bill;
    }

    public void setBillId(Integer id_bill) { 
    	this.id_bill = id_bill;  }

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return null;
	}
}
