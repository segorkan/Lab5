package interfaces;

import java.io.InputStream;
import java.io.InputStreamReader;

import static auxiliary.CurrentInput.getInputStream;
import static auxiliary.CurrentInput.getInputStreamReader;

/**
 * Интерфейс команд для введения элемента коллекции.
 */
public interface ElementCreator {

    default InputStreamReader getStreamInput() {
        return getInputStreamReader();
    }
}
