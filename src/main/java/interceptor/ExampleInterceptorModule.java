package interceptor;

import com.warxim.petep.helper.PetepHelper;
import com.warxim.petep.interceptor.factory.InterceptorModuleFactory;
import com.warxim.petep.interceptor.module.InterceptorModule;
import com.warxim.petep.interceptor.worker.Interceptor;
import com.warxim.petep.persistence.Storable;

/**
 * Example interceptor module that creates interceptor for logging number of intercepted PDUs.
 */
public class ExampleInterceptorModule extends InterceptorModule implements Storable<ExampleInterceptorStore> {
    /**
     * Store for persisting the PDU counter
     */
    private ExampleInterceptorStore store;

    public ExampleInterceptorModule(
            InterceptorModuleFactory factory,
            String code,
            String name,
            String description,
            boolean enabled) {
        super(factory, code, name, description, enabled);
    }

    @Override
    public Interceptor createInterceptor(int id, PetepHelper helper) {
        if (store == null) {
            store = new ExampleInterceptorStore();
        }

        return new ExampleInterceptor(id, this, helper, store);
    }

    @Override
    public void loadStore(ExampleInterceptorStore store) {
        this.store = store;
    }

    @Override
    public ExampleInterceptorStore saveStore() {
        return store;
    }
}
