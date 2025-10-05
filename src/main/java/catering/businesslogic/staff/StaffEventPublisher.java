package catering.businesslogic.staff;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class StaffEventPublisher {
    public enum Type { VALUTAZIONE_REGISTRATA, PROMOZIONE_ESEGUITA }

    public static final class StaffEvent {
        public final Type type;
        public final Object payload;
        StaffEvent(Type type, Object payload) {
            this.type = type; this.payload = payload;
        }
    }

    private final List<StaffEventListener> listeners = new CopyOnWriteArrayList<>();

    public void addListener(StaffEventListener l) { if (l != null) listeners.add(l); }
    public void removeListener(StaffEventListener l) { listeners.remove(l); }

    public void publish(Type type, Object payload) {
        StaffEvent evt = new StaffEvent(type, payload);
        for (StaffEventListener l : listeners) l.onEvent(evt);
    }
}

