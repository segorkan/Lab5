package interfaces;

import java.io.InputStreamReader;

import static auxiliary.CurrentInput.getInputStreamReader;

/**
 * Интерфейс команд для введения элемента коллекции.
 */
public interface ElementCreator {

    default InputStreamReader getStreamInput() {
        return getInputStreamReader();
    }
}
