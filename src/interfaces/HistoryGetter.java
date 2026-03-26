package interfaces;

import java.util.Deque;

public interface HistoryGetter {
    Deque<String> getHistory();
}
