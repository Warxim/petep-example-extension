package interceptor;

/**
 * Store for persisting PDU count.
 */
public class ExampleInterceptorStore {
    private int pduCount;

    public ExampleInterceptorStore() {
        pduCount = 0;
    }

    /**
     * Increases the PDU count and returns it.
     */
    public int increaseAndGetCount() {
        return ++pduCount;
    }
}
