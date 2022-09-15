package tools;

import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hoang Minh Nguyen Van
 */
public class MyTool {

    public static final Scanner sc = new Scanner(System.in);

    public static boolean validStr(String str, String regEx) {
        boolean matches = str.matches(regEx);
        return matches;
    }

    public static boolean parseBool(String boolStr) {
        char c = boolStr.trim().toUpperCase().charAt(0);
        return (c == '1' || c == 'Y' || c == 'T');
    }

    public static String getString(String inputMsg, String errorMsg) {
        String id;
        while (true) {
            System.out.print(inputMsg);
            id = sc.nextLine().trim();
            if (id.length() == 0 || id.isEmpty()) {
                System.out.println(errorMsg);
            } else {
                return id;
            }
        }
    }

    public static String ReadPattern(String message, String errorMessage, String pattern) {
        String input = "";
        boolean valid;
        while (true) {
            System.out.print(message + " ");
            input = sc.nextLine().trim();
            valid = validStr(input, pattern);
            if (input.isEmpty()) {
                System.out.println("Not blank or empty");
            } else if (valid == false) {
                System.out.println(errorMessage);
            } else {
                return input;
            }
        }
    }

    public static List<String> readLinesFromFile(String filename) {
        ArrayList<String> list = new ArrayList();
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader bf = new BufferedReader(fr);
            String details;
            while ((details = bf.readLine()) != null) {
                list.add(details);
            }
            bf.close();
            fr.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public static void writeFile(String filename, List list) {
        try {
            File f = new File(filename);
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            for (int i = 0; i < list.size(); i++) {
                pw.println(list.get(i).toString());
            }
            pw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static int inputValue(String welcome, int bot, int top) {
        int number = 0;
        Scanner sc = new Scanner(System.in);
        boolean cont = true;
        do {
            try {
                System.out.print(welcome);
                number = Integer.parseInt(sc.nextLine());
                cont = false;
            } catch (Exception e) {
                System.out.println("Empty input!!!");
            }
        } while (cont || number > top || number < bot);
        return number;
    }

    public static boolean confirmYesNo(String welcome) {
        boolean result = false;
        System.out.println(welcome);
        Scanner sc = new Scanner(System.in);
        String confirm = sc.nextLine();
        if ("Y".equalsIgnoreCase(confirm)) {
            result = true;
        }
        return result;
    }
}
