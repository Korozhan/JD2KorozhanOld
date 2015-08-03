/**
 * 
 */
package by.academy.entities;

import java.util.Date;

import by.academy.Identified;


/**
 * @author Veronika
 *
 */
public class Bill implements Identified<Integer>{
	
	private Integer id_bill = null;
    private Integer price;
    private Date date;

    public Integer getId() {
        return id_bill;
    }

    protected void setId(int id_bill) { this.id_bill = id_bill;  }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
