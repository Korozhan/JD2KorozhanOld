/**
 * 
 */
package by.academy.entities;

/**
 * @author Veronika
 *
 */
public class Users {
	
	private Integer id_user = null;
    private String login;
    private String password;

    public Integer getId() {
        return id_user;
    }

    protected void setId(int id_user) { this.id_user = id_user;  }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswordt() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
