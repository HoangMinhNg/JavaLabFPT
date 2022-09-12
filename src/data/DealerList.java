package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import tools.MyTool;

/**
 *
 * @author Nguyen Van Hoang Minh
 */
public class DealerList extends ArrayList<Dealer> {

    private String dataFile = "";
    boolean changed = false;
    private static final String SEPARATOR = ",";
    List<Dealer> list = new ArrayList<>();

    public DealerList() {
        initWithFile();
    }

    public final void initWithFile() {
        Config cR = new Config();
        dataFile = cR.getDealerFile();
    }

    public void loadDealerFromFile() {
        try {
            FileReader fr = new FileReader(dataFile);
            BufferedReader bf = new BufferedReader(fr);
            String details;
            while ((details = bf.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(details, SEPARATOR);
                String ID = stk.nextToken();
                String name = stk.nextToken();
                String address = stk.nextToken();
                String phone = stk.nextToken();
                boolean continuing = MyTool.parseBool(stk.nextToken());
                list.add(new Dealer(ID, name, address, phone, continuing));
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
        list.add(new Dealer(ID, name, address, phone, continuing));
        System.out.println("New dealer has been added.");
        changed = true;
    }

    public int checkID(String id) {
        if (list.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getID().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }

    public void searchDealer() {
        String ID = MyTool.getString("Enter Dealer's ID to search: ", "Not blank or empty.");
        int index = checkID(ID);
        if (index >= 0) {
            System.out.println("|----------|----------|--------------------|---------------|----------|");
            System.out.println("|    ID    |   NAME   |      ADDRESS       |     PHONE     |CONTINUING|");
            System.out.println("|----------|----------|--------------------|---------------|----------|");
            list.get(index).showInfor();
            System.out.println("|----------|----------|--------------------|---------------|----------|");
        } else {
            System.out.println("Dealer " + ID + " not found!");
        }
    }

    public void removeDealer() {
        String ID = MyTool.getString("Enter Dealer's to remove: ", "Not blank or empty");
        int index = checkID(ID);
        if (index >= 0) {
            list.get(index).setContinuing(false);
            System.out.println("Removed!");
            changed = true;
        } else {
            System.out.println("Dealer " + ID + "not found!");
        }
    }

    public void updateDealer() {
        String ID = MyTool.getString("Enter Dealer's ID to updating: ", "Not blank or empty");
        int index = checkID(ID);
        if (index >= 0) {
            String newName = MyTool.getString("Enter Dealer's new name: ", "Not blank or empty");
            list.get(index).setName(newName.toUpperCase());
            String newAddress = MyTool.getString("Enter Dealer's new address: ", "Not blank or empty");
            list.get(index).setAddr(newAddress.toUpperCase());
            String newPhone = MyTool.ReadPattern("Enter Dealer's new phone number: ", "Phone is 9 or 11 digit", Dealer.PHONE_FORMAT);
            list.get(index).setAddr(newPhone);
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
        if (list.isEmpty()) {
            System.out.println("Empty List!!!");
        } else {
            System.out.println("|----------|----------|--------------------|---------------|----------|");
            System.out.println("|    ID    |   NAME   |      ADDRESS       |     PHONE     |CONTINUING|");
            System.out.println("|----------|----------|--------------------|---------------|----------|");
            for (int i = 0; i < list.size(); i++) {
                list.get(i).showInfor();
            }
            System.out.println("|----------|----------|--------------------|---------------|----------|");
        }
    }

    public void printContinuingDealers() {
        if (list.isEmpty()) {
            System.out.println("List empty. Nothing to print.");
        } else {
            System.out.println("|----------|----------|--------------------|---------------|----------|");
            System.out.println("|    ID    |   NAME   |      ADDRESS       |     PHONE     |CONTINUING|");
            System.out.println("|----------|----------|--------------------|---------------|----------|");
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).isContinuing() == true) {
                    list.get(i).showInfor();
                }
            }
            System.out.println("|----------|----------|--------------------|---------------|----------|");
        }
    }

    public void printUnContinuingDealers() {
        if (list.isEmpty()) {
            System.out.println("List empty.Nothing to print.");
        } else {
            System.out.println("|----------|----------|--------------------|---------------|----------|");
            System.out.println("|    ID    |   NAME   |      ADDRESS       |     PHONE     |CONTINUING|");
            System.out.println("|----------|----------|--------------------|---------------|----------|");
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).isContinuing() == false) {
                    list.get(i).showInfor();
                }
            }
            System.out.println("|----------|----------|--------------------|---------------|----------|");
        }
    }

    public void writeDealerToFile() {
        if (changed) {
            MyTool.writeFile(dataFile, list);
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
