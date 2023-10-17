package controller;

public class Common {

    public static String handleString(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        return str.trim();
    }

    public static int handleInt(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(str.trim());
    }
}
