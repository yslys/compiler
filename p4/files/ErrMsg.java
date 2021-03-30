/**
 * ErrMsg
 *
 * This class is used to generate warning and fatal error messages.
 */
class ErrMsg {
    // compiler should quit after nameAnalysis has finished if fatal() is called
    static boolean toQuit = false;

    /**
     * Generates a fatal error message.
     * @param lineNum line number for error location
     * @param charNum character number (i.e., column) for error location
     * @param msg associated message for error
     */
    static void fatal(int lineNum, int charNum, String msg) {
        System.err.println(lineNum + ":" + charNum + " ***ERROR*** " + msg);
        System.out.println(lineNum + ":" + charNum + " ***ERROR*** " + msg);
        toQuit = true;
    }

    /**
     * Generates a warning message.
     * @param lineNum line number for warning location
     * @param charNum character number (i.e., column) for warning location
     * @param msg associated message for warning
     */
    static void warn(int lineNum, int charNum, String msg) {
        System.err.println(lineNum + ":" + charNum + " ***WARNING*** " + msg);
    }
}
