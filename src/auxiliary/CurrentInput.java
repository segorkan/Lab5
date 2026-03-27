package auxiliary;

import java.io.InputStream;

public class CurrentInput {
    private static InputStream stream;

    static {
        stream = System.in;
    }

    /**
     * Изменяет текущий входной поток.
     * @param stream входной поток.
     */
    public static void changeInputStream(InputStream stream) {
        CurrentInput.stream = stream;
    }

    /**
     * Возвращает текущий входной поток.
     */
    public static InputStream getInputStream() {
        return CurrentInput.stream;
    }
}
