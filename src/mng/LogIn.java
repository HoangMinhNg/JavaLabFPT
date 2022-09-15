package mng;

import data.Account;
import data.AccountChecker;
import data.DealerList;
import tools.MyTool;

/**
 *
 * @author Hoang Minh Nguyen Van
 */
public class LogIn {

    private Account acc = null;
    private static final String SEPARATOR = ",";

    public LogIn(Account acc) {
        this.acc = acc;
    }

    public static Account inputAccount() {
        String name = MyTool.getString("Your account name: ", "Not blank or empty.Input again.");
        String password = MyTool.getString("Your password: ", "Not blank or empty.Input again.");
        String role = MyTool.getString("Your role: ", "Not blank or empty.Input again.");
        Account accIn = new Account(name, password, role);
        return accIn;
    }

    public static void main(String[] args) {
        Account acc = null;
        boolean cont = false;
        boolean confirm = false;
        boolean valid = false;
        DealerList dealerList = new DealerList();
        dealerList.loadDealerFromFile();
        do {
            do {
                AccountChecker accChk = new AccountChecker();
                acc = inputAccount();
                valid = accChk.check(acc);
                if (!valid) {
                    System.out.println("Incorrect user or password!!!");
                    cont = MyTool.confirmYesNo("Try again? (Y/N)");
                }
                if (!valid && !cont) {
                    System.exit(0);
                }
                if (valid) {
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
                                    if (dealerList.isChanged() == true) {
                                        boolean res = MyTool.confirmYesNo("Data is changed. Write to file? (Y/N)");
                                        if (res == true) {
                                            dealerList.writeDealerToFile();
                                        }
                                    }
                                    System.out.println("Bye,bye.See you next time.");
                                    cont = false;
                                    break;
                            }
                        } while (choice != 9);
                    } else {
                        System.out.println("Unsupported feature yet");
                    }
                }
            } while (cont);
            confirm = MyTool.confirmYesNo("Do you want to try again? (Y/N)");
        } while (confirm);
    }
}
