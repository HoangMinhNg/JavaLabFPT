package data;

/**
 *
 * @author Hoang Minh Nguyen Van
 */
public class Dealer implements Comparable<Dealer> {

    public static final char SEPARATOR = ',';
    public static final String ID_FORMAT = "D\\d{3}|d\\d{3}";
    public static final String PHONE_FORMAT = "\\d{9}|\\d{11}";
    private String ID;
    private String name;
    private String addr;
    private String phone;
    private boolean continuing;

    public Dealer(String ID, String name, String addr, String phone, boolean continuing) {
        this.ID = ID;
        this.name = name;
        this.addr = addr;
        this.phone = phone;
        this.continuing = continuing;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isContinuing() {
        return continuing;
    }

    public void setContinuing(boolean continuing) {
        this.continuing = continuing;
    }

    @Override
    public String toString() {
        return ID + SEPARATOR + name + SEPARATOR
                + addr + SEPARATOR + phone + SEPARATOR
                + continuing + "\n";
    }

    public void showInfor() {
        System.out.printf("|%-10s|%-10s|%-20s|%-15s|%-10s|\n", ID.toUpperCase(), name.toUpperCase(), addr.toUpperCase(), phone, continuing);
    }

    @Override
    public int compareTo(Dealer o) {
        return this.getID().compareToIgnoreCase(o.getID());
    }

}
