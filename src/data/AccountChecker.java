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
                return true;
            }
        }
        return false;
    }
}
