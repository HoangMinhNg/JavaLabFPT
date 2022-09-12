package main;

import data.Account;
import data.DealerList;
import mng.LogIn;
import mng.Menu;
import tools.MyTool;

/**
 *
 * @author Hoang Minh Nguyen Van
 */
public class DealerManagement {

    public static void main(String[] args) {
        LogIn login = new LogIn();
        boolean confirm = false;
        DealerList dList = new DealerList();
        dList.loadDealerFromFile();
        do {
            Account acc = login.inputAccount();
            boolean checkLogin = login.checkLogin(acc);
            if (checkLogin) {
                if (acc.getRole().equalsIgnoreCase("ACC-1")) {

                    int choice;
                    Menu menu = new Menu("Managing dealers: ");
                    menu.addNewOption("     1-Add new dealer.");
                    menu.addNewOption("     2-Search a dealer.");
                    menu.addNewOption("     3-Remove a dealer.");
                    menu.addNewOption("     4-Update a dealer.");
                    menu.addNewOption("     5-Print all dealers.");
                    menu.addNewOption("     6-Print continuing dealers.");
                    menu.addNewOption("     7-Print Un-continuing dealers.");
                    menu.addNewOption("     8-Write to file");
                    menu.addNewOption("     9-Others.Exit...");
                    do {
                        menu.printMenu();
                        choice = menu.getChoice();
                        switch (choice) {
                            case 1:
                                dList.addDealer();
                                break;
                            case 2:
                                dList.searchDealer();
                                break;
                            case 3:
                                dList.removeDealer();
                                break;
                            case 4:
                                dList.updateDealer();
                                break;
                            case 5:
                                dList.printAllDealers();
                                break;
                            case 6:
                                dList.printContinuingDealers();
                                break;
                            case 7:
                                dList.printUnContinuingDealers();
                                break;
                            case 8:
                                dList.writeDealerToFile();
                                break;
                            case 9:
                                System.out.println("Bye,bye.See you next time.");
                                break;
                        }
                    } while (choice != 9);
                } else {
                    System.out.println("Unsupported feature yet");
                }
            } else {
                System.out.println("Your account does not exist in the system.");
            }
            confirm = MyTool.confirmYesNo("Do you want to try again(Y/N): ");
        } while (confirm);
    }
}
