package mng;

import java.util.ArrayList;
import tools.MyTool;

/**
 *
 * @author Hoang Minh Nguyen Van
 */
public class Menu extends ArrayList<String> {

    private final String menuTitle;
    private final ArrayList<String> optionList = new ArrayList();

    public Menu(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public void addNewOption(String newOption) {
        optionList.add(newOption);
    }

    public void printMenu() {
        System.out.println("\n");
        System.out.println("Welcome to " + menuTitle);
        for (String x : optionList) {
            System.out.println(x);
        }
    }

    public int getChoice() {
        int maxOption = optionList.size();
        String inputMsg = "Choose [1.." + maxOption + "]: ";
        return MyTool.inputValue(inputMsg, 1, maxOption);
    }
}
