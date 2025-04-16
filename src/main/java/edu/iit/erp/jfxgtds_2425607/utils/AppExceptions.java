package edu.iit.erp.jfxgtds_2425607.utils;

public class AppExceptions {

    public static class FileFormatErrorException extends Exception {
        public FileFormatErrorException(String message) {
            super(message);
        }
    }

    public static class FileNotFoundErrorException extends Exception {
        public FileNotFoundErrorException(String message) {
            super(message);
        }
    }
    public static class InvalidDoubleException extends Exception {
        public InvalidDoubleException(String message) {
            super(message);
        }
    }

    public static class InvalidIntegerException extends Exception {
        public InvalidIntegerException(String message) {
            super(message);
        }
    }
}
