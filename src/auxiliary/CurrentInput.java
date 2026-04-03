package auxiliary;

import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Класс для хранения и изменения текущего входного потока.
 */
public class CurrentInput {
    private static InputStream stream;
    private static InputStreamReader reader;

    /**
     * Изменяет текущий входной поток и обертку для ввода символов.
     * @param stream входной поток.
     */
    public static void changeInputStream(InputStream stream) {
        CurrentInput.stream = stream;
        CurrentInput.reader = new InputStreamReader(stream);
    }

    /**
     * Возвращает текущий входной поток.
     */
    public static InputStream getInputStream() {
        return CurrentInput.stream;
    }

    /**
     * Возвращает обертку входного потока для ввода символов.
     * @return
     */
    public static InputStreamReader getInputStreamReader(){
        return CurrentInput.reader;
    }
}
