/**
 * 
 */
package by.academy.entities;

/**
 * @author Veronika
 *
 */
public class CarType {
	
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
