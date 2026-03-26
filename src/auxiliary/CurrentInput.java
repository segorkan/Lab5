package auxiliary;

import java.io.InputStream;
import java.io.OutputStream;

public class CurrentInput {
    private static InputStream stream;

    static {
        stream = System.in;
    }

    public static void changeInputStream(InputStream stream) {
        CurrentInput.stream = stream;
    }

    public static InputStream getInputStream() {
        return CurrentInput.stream;
    }
}
