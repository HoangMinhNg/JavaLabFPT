package data;
/**
 *
 * @author Hoang Minh Nguyen Van
 */
public class Account {
    private final String accName;
    private final String pwd;
    private final String role;

    public Account(String accName, String pwd, String role) {
        this.accName = accName;
        this.pwd = pwd;
        this.role = role;
    }

    public String getAccName() {
        return accName;
    }

    public String getPwd() {
        return pwd;
    }

    public String getRole() {
        return role;
    }
}
