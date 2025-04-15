package edu.iit.erp.jfxgtds_2425607.utils;

public class Checksum {

    public Integer findChecksum(String text) {
        Integer uppercase = 0, lowercase = 0, numeric = 0;
        for (char c : text.toCharArray()) {
            if (Character.isUpperCase(c)) {
                uppercase++;
            }
            if (Character.isLowerCase(c)) {
                lowercase++;
            }
            if (Character.isDigit(c)) {
                numeric++;
            }
        }
        return uppercase + lowercase + numeric;
    }
}
