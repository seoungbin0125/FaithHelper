package truesolution.ledpad.asign.share;

import com.squareup.otto.Bus;

public class EventBusProvider {
    private static Bus sBus;

    public static Bus getInstance() {
        if (sBus == null)
            sBus = new Bus();
        return sBus;    }

    private EventBusProvider() {
        // No instances.
    }


}
