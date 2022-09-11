package tools;

import java.sql.Date;
import java.text.SimpleDateFormat;
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

    public static boolean validPassword(String str, int minLen) {
        if (str.length() < minLen) {
            return false;
        }
        return str.matches(".*[a-zA-Z]+.*")
                && str.matches(".*[\\d]+.*")
                && str.matches(".*[\\W]+.*");
    }

    public static Date parseDate(String dateStr, String dateFormat) {
        SimpleDateFormat dF = (SimpleDateFormat) SimpleDateFormat.getInstance();
        dF.applyPattern(dateFormat);
        try {
            long t = dF.parse(dateStr).getTime();
            return new Date(t);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static String dataToStr(Date date, String dateFormat) {
        SimpleDateFormat dF = (SimpleDateFormat) SimpleDateFormat.getInstance();
        dF.applyPattern(dateFormat);
        return dateFormat.formatted(date);
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
            System.out.print(message + ": ");
            input = sc.nextLine().trim();
            valid = validStr(input, pattern);
            if (input.length() == 0 || input.isEmpty()) {
                System.out.println("Not blank or empty");
            } else if (valid == false) {
                System.out.println(errorMessage);
            } else {
                return input;
            }
        }
    }

    public static boolean readBool(String message) {
        String input;
        System.out.println(message + "[1/0-Y/N-T/F]: ");
        input = sc.nextLine().trim();
        if (input.isEmpty()) {
            return false;
        }
        char c = Character.toUpperCase(input.charAt(0));
        return (c == '1' || c == 'Y' || c == 'T');
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
                System.out.print(welcome);
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
