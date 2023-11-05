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

    public static int[] handlePaging(int page) {
        int limit = 10;
        if (page <= 1) {
            return new int[]{0, limit};
        }
        return new int[]{page * limit - limit, limit};
    }

    public static int handleNum(int page) {
        int pageNum = (int) Math.ceil(page / 10);
        if (page % 10 != 0) {
            pageNum++;
        }
        return pageNum;
    }
}
