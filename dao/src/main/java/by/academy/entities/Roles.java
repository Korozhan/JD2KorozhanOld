/**
 * 
 */
package by.academy.entities;

import by.academy.Identified;

/**
 * @author Veronika
 *
 */
public class Roles implements Identified<Integer>{
	private Integer id_role = null;
    private String role;

    public Integer getId() {
        return id_role;
    }

    protected void setId(int id_role) { this.id_role = id_role;  }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
