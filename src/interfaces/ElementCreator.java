package interfaces;

import java.io.InputStream;

import static auxiliary.CurrentInput.getInputStream;

public interface ElementCreator {

    default InputStream getStreamInput() {
        return getInputStream();
    }
}
