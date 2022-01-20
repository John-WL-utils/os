package os;

import java.util.Locale;

public final class OperatingSystem {
    public static OperatingSystemType TYPE = getOperatingSystemType();

    private static OperatingSystemType getOperatingSystemType() {
        String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
        if ((OS.contains("mac")) || (OS.contains("darwin"))) {
            return OperatingSystemType.MAC_OS;
        } else if (OS.contains("win")) {
            return OperatingSystemType.WINDOWS;
        } else if (OS.contains("nux")) {
            return OperatingSystemType.LINUX;
        } else {
            return OperatingSystemType.OTHER;
        }
    }
}