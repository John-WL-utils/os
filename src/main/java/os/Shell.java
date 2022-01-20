package os;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Shell {

    public static int callAndLog(byte[] output, String... args) {
        try {
            final ProcessBuilder builder = new ProcessBuilder(args);
            final Process p = builder.start();
            p.getOutputStream().write(output);
            p.getOutputStream().close();
            displayInputStream(p.getInputStream());
            p.getInputStream().close();
            displayInputStream(p.getErrorStream());
            p.getErrorStream().close();
            p.waitFor();
            p.destroy();
            return p.exitValue();
        }
        catch (IOException | InterruptedException ioException) {
            ioException.printStackTrace();
            return -1;
        }
    }

    public static int call(byte[] output, String... args) {
        try {
            final ProcessBuilder builder = new ProcessBuilder(args);
            final Process p = builder.start();
            p.getOutputStream().write(output);
            p.getOutputStream().close();
            p.getInputStream().close();
            p.getErrorStream().close();
            p.waitFor();
            p.destroy();
            return p.exitValue();
        }
        catch (IOException | InterruptedException ioException) {
            ioException.printStackTrace();
            return -1;
        }
    }

    public static int callAndLog(String... args) {
        return callAndLog(new byte[]{}, args);
    }

    public static int call(String... args) {
        return call(new byte[]{}, args);
    }

    private static void displayInputStream(InputStream inputStream) throws IOException {
        String s;
        String previousLine = "";
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(inputStream));
        while ((s = stdInput.readLine()) != null) {
            if(!s.equals(previousLine)) {
                System.out.println(s);
            }
            previousLine = s;
        }
    }
}
