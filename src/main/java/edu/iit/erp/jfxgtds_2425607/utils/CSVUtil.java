package edu.iit.erp.jfxgtds_2425607.utils;

public class CSVUtil {

    public static Boolean formatChecker(String line) {
        String[] arr = line.split(",");
        if (arr.length != 6) {
            return false;
        }
        else {
            // Checking whether the columns from 2 to 6 is numeric
            for (int i = 1; i <= 5; i++) {
                if (!isNumeric(arr[i])) {
                    return false;
                }
            }
            return true;
        }
    }

    private static boolean isNumeric(String data) {
        if (data == null || data.isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(data);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }


}
