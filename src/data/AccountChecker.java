/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.util.List;
import tools.MyTool;

/**
 *
 * @author Hoang Minh Nguyen Van
 */
public class AccountChecker {

    private String accFile;
    private static final String SEPERATOR = ",";

    public AccountChecker() {
        setupAccFile();
    }

    private void setupAccFile() {
        Config cR = new Config();
        accFile = cR.getAccountFile();
    }

    public boolean check(Account acc) {
        List<String> lines = MyTool.readLinesFromFile(accFile);
        for (String line : lines) {
            String[] parts = line.split(SEPERATOR);
            if (parts.length < 3) {
                return false;
            }
            if (parts[0].equalsIgnoreCase(acc.getAccName())
                    && parts[1].equals(acc.getPwd())
                    ) {
                System.out.println("run true");
                return true;
            }
        }
        return false;
    }
}
