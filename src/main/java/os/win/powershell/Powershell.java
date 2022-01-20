package os.win.powershell;

import os.Shell;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Powershell {

    public static int callAndLog(byte[] output, String... args) {
        return Shell.callAndLog(output, asPowershellArgs(args));
    }

    public static int call(byte[] output, String... args) {
        return Shell.call(output, asPowershellArgs(args));
    }

    public static int callAndLog(String... args) {
        return callAndLog(new byte[]{}, args);
    }

    public static int call(String... args) {
        return call(new byte[]{}, args);
    }

    private static String[] asPowershellArgs(String... args) {
        var params = Arrays.stream(new String[]{"powershell.exe"}).collect(Collectors.toList());
        params.addAll(Arrays.stream(args).collect(Collectors.toList()));
        return params.toArray(new String[]{});
    }
}
