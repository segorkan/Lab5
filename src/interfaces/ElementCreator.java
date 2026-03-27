package interfaces;

import java.io.InputStream;

import static auxiliary.CurrentInput.getInputStream;

/**
 * Интерфейс команд для введения элемента коллекции.
 */
public interface ElementCreator {

    default InputStream getStreamInput() {
        return getInputStream();
    }
}
