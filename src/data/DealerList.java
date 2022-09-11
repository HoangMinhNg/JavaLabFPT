package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import tools.MyTool;

/**
 *
 * @author Nguyen Van Hoang Minh
 */
public class DealerList extends ArrayList<Dealer> {

    private static final String PHONEPATTERN = "\\d{9}|\\d{11}";
    private String dataFile = "";
    boolean changed = false;

    public DealerList() {
        initWithFile();
        loadDealerFromFile();
    }

    public void initWithFile() {
        Config cR = new Config();
        dataFile = cR.getDealerFile();
    }

    private void loadDealerFromFile() {
        try {
            FileReader fr = new FileReader(dataFile);
            BufferedReader bf = new BufferedReader(fr);
            String details;
            while ((details = bf.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(details, ",");
                String ID = stk.nextToken();
                String name = stk.nextToken();
                String address = stk.nextToken();
                String phone = stk.nextToken();
                boolean continuing = Boolean.parseBoolean(stk.nextToken());
                this.add(new Dealer(ID, name, address, phone, continuing));
            }
            bf.close();
            fr.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addDealer() {
        String ID;
        int index;
        do {
            ID = MyTool.ReadPattern("ID of new dealer: ", "Wrong.Input again.", Dealer.ID_FORMAT);
            index = checkID(ID);
            if (index >= 0) {
                System.out.println("ID is duplicated.Input again.");
            }
        } while (index >= 0);
        String name = MyTool.getString("Name of new dealer: ", "Not blank or empty.Input again.");
        String address = MyTool.getString("Address of new dealer: ", "Not blank or empty.Input again.");
        String phone = MyTool.ReadPattern("Phone number: ", "Phone is 9 or 11 digit.", Dealer.PHONE_FORMAT);
        boolean continuing = true;
        this.add(new Dealer(ID, name, address, phone, continuing));
        System.out.println("New dealer has been added.");
        changed = true;
    }

    public int checkID(String id) {
        if (this.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getID().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }

    public void searchDealer() {
        String ID = MyTool.getString("Enter Dealer's ID to search: ", "Not blank or empty.");
        int index = checkID(ID);
        if (index >= 0) {
            System.out.println("+----------+----------+--------------------+---------------+----------+");
            System.out.println("|    ID    |   NAME   |      ADDRESS       |     PHONE     |CONTINUING|");
            System.out.println("+----------+----------+--------------------+---------------+----------+");
            this.get(index).showInfor();
            System.out.println("+----------+----------+--------------------+---------------+----------+");
        } else {
            System.out.println("Dealer " + ID + " not found!");
        }
    }

    public void removeDealer() {
        String ID = MyTool.getString("Enter Dealer's to remove", "Not blank or empty");
        int index = checkID(ID);
        if (index >= 0) {
            this.get(index).setContinuing(false);
            System.out.println("Removed!");
            changed = true;
        } else {
            System.out.println("Dealer " + ID + "not found!");
        }
    }

    public void updateDealer() {
        String ID = MyTool.getString("Enter Dealer's ID to updating", "Not blank or empty");
        int index = checkID(ID);
        if (index >= 0) {
            String newName = MyTool.getString("Enter Dealer's new name", "Not blank or empty");
            this.get(index).setName(newName.toUpperCase());
            String newAddress = MyTool.getString("Enter Dealer's new address", "Not blank or empty");
            this.get(index).setAddr(newAddress.toUpperCase());
            String newPhone = MyTool.ReadPattern("Enter Dealer's new phone number", "Phone is 9 or 11 digit", Dealer.PHONE_FORMAT);
            this.get(index).setPhone(newPhone);
            changed = true;
            if (changed == true) {
                System.out.println("The dealer's information has been updated successfully");
            } else {
                System.out.println("The dealer's information has been updated UNSUCCESSFULLY!!!");
            }
        } else {
            System.out.println("Dealer " + ID + "not found!");
        }
    }

    public void printAllDealers() {
        if (this.isEmpty()) {
            System.out.println("Empty List!!!");
        } else {
            System.out.println("+----------+----------+--------------------+---------------+----------+");
            System.out.println("|    ID    |   NAME   |      ADDRESS       |     PHONE     |CONTINUING|");
            System.out.println("+----------+----------+--------------------+---------------+----------+");
            for (int i = 0; i < this.size(); i++) {
                this.get(i).showInfor();
            }
            System.out.println("+----------+----------+--------------------+---------------+----------+");
        }
    }

    public void printContinuingDealers() {
        if (this.isEmpty()) {
            System.out.println("List empty.Nothing to print.");
        } else {
            System.out.println("+----------+----------+--------------------+---------------+----------+");
            System.out.println("|    ID    |   NAME   |      ADDRESS       |     PHONE     |CONTINUING|");
            System.out.println("+----------+----------+--------------------+---------------+----------+");
            for (int i = 0; i < this.size(); i++) {
                if (this.get(i).isContinuing() == true) {
                    this.get(i).showInfor();
                }
            }
            System.out.println("+----------+----------+--------------------+---------------+----------+");
        }
    }

    public void printUnContinuingDealers() {
        if (this.isEmpty()) {
            System.out.println("List empty.Nothing to print.");
        } else {
            System.out.println("+----------+----------+--------------------+---------------+----------+");
            System.out.println("|    ID    |   NAME   |      ADDRESS       |     PHONE     |CONTINUING|");
            System.out.println("+----------+----------+--------------------+---------------+----------+");
            for (int i = 0; i < this.size(); i++) {
                if (this.get(i).isContinuing() == false) {
                    this.get(i).showInfor();
                }
            }
            System.out.println("+----------+----------+--------------------+---------------+----------+");
        }
    }

    public void writeDealerToFile() {
        if (changed) {
            MyTool.writeFile(dataFile, this);
            changed = false;
            System.out.println("Save to file successfully");
        }
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }
}