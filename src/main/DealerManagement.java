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
        DealerList dealerList = new DealerList();
        dealerList.loadDealerFromFile();
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
                                dealerList.addDealer();
                                break;
                            case 2:
                                dealerList.searchDealer();
                                break;
                            case 3:
                                dealerList.removeDealer();
                                break;
                            case 4:
                                dealerList.updateDealer();
                                break;
                            case 5:
                                dealerList.printAllDealers();
                                break;
                            case 6:
                                dealerList.printContinuingDealers();
                                break;
                            case 7:
                                dealerList.printUnContinuingDealers();
                                break;
                            case 8:
                                dealerList.writeDealerToFile();
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
