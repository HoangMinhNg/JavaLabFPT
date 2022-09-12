package mng;

import data.Account;
import data.Config;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import tools.MyTool;

/**
 *
 * @author Hoang Minh Nguyen Van
 */
public class LogIn {

    private String accFile;
    private static final String SEPARATOR = ",";
    private static final ArrayList<Account> listAcc = new ArrayList();

    public LogIn() {
        setupAccFile();
        readData();
    }

    public final void setupAccFile() {
        Config cR = new Config();
        accFile = cR.getAccountFile();
    }

    public final void readData() {
        try {
            FileReader fr = new FileReader(accFile);
            BufferedReader bf = new BufferedReader(fr);
            String details;
            while ((details = bf.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(details, SEPARATOR);
                String accName = stk.nextToken();
                String password = stk.nextToken();
                String role = stk.nextToken();
                listAcc.add(new Account(accName, password, role));
            }
            bf.close();
            fr.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Account inputAccount() {
        String name = MyTool.getString("Your account name: ", "Not blank or empty.Input again.");
        String password = MyTool.getString("Your password: ", "Not blank or empty.Input again.");
        String role = MyTool.getString("Your role: ", "Not blank or empty.Input again.");
        Account acc = new Account(name, password, role);
        return acc;
    }

    public boolean checkLogin(Account acc) {
        List<String> lines = MyTool.readLinesFromFile(accFile);
        for (String line : lines) {
            String[] parts = line.split(LogIn.SEPARATOR);
            if (parts.length < 3) {
                return false;
            }
            if (parts[0].equalsIgnoreCase(acc.getAccName())
                    && parts[1].equals(acc.getPwd())
                    && parts[2].equalsIgnoreCase(acc.getRole())) {
                return true;
            }
        }
        return false;
    }
}
