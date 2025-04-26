package edu.iit.erp.jfxgtds_2425607.utils;

public class CSVUtil {

    public static String formatChecker(String line) {
        String[] arr = line.split(",");
        if (arr.length != 6) {
            return "Invalid file. The file must have 6 columns.";
        }
        else {
            // Checking whether the columns from 2 to 6 is numeric
            for (int i = 1; i <= 5; i++) {
                if (!isNumeric(arr[i])) {
                    return "Cannot import file. The columns 2, 3, 4, 6 cannot contain characters.";
                }
            }
            return "";
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
