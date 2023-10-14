package common;

public class Common {
    public static String handleString(String str) {
        str = str.trim();
        if (str == null || str.isEmpty()) {
            return "";
        }
        return str;
    }
}
