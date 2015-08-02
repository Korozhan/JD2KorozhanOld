/**
 * 
 */
package by.academy.entities;

import by.academy.Identified;

/**
 * @author Veronika
 *
 */
public class Details implements Identified<Integer>{
	
	private Integer id_details = null;
    private String mark;
    private String model;
    private String country;
    private String gearbox;
    private Integer mileage;
    private String fuel;


    public Integer getId() {
        return id_details;
    }

    protected void setId(int id_details) { this.id_details = id_details;  }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
    
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
    
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    public String getGearbox() {
        return gearbox;
    }

    public void setGearbox(String gearbox) {
        this.gearbox = gearbox;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }
    
    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }
}
