package auxiliary;

import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Класс для хранения и изменения текущего входного потока.
 */
public class CurrentInput {
    private static InputStream stream;
    private static InputStreamReader reader;

    static {
        stream = System.in;
    }

    /**
     * Изменяет текущий входной поток.
     * @param stream входной поток.
     */
    public static void changeInputStream(InputStream stream) {
        CurrentInput.stream = stream;
        reader = new InputStreamReader(stream);
    }

    /**
     * Возвращает текущий входной поток.
     */
    public static InputStream getInputStream() {
        return CurrentInput.stream;
    }

    public static InputStreamReader getInputStreamReader(){
        return CurrentInput.reader;
    }
}
