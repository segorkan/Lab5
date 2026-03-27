package interfaces;

import java.util.Deque;

/**
 * Интерфейс команд которым нужна для работы история.
 */
public interface HistoryGetter {
    Deque<String> getHistory();
}
