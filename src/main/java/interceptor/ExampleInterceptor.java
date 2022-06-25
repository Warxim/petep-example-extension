package interceptor;

import com.warxim.petep.core.pdu.PDU;
import com.warxim.petep.helper.PetepHelper;
import com.warxim.petep.interceptor.module.InterceptorModule;
import com.warxim.petep.interceptor.worker.Interceptor;

import java.util.logging.Logger;

/**
 * Example interceptor that logs number of intercepted PDUs.
 */
public class ExampleInterceptor extends Interceptor {
    private ExampleInterceptorStore store;

    public ExampleInterceptor(
            int id,
            InterceptorModule module,
            PetepHelper helper,
            ExampleInterceptorStore store) {
        super(id, module, helper);
        this.store = store;
    }

    @Override
    public boolean intercept(PDU pdu) {
        Logger.getGlobal().info(
                () -> "Example interceptor intercepted PDU number " + store.increaseAndGetCount() + "!"
        );

        return true;
    }

    @Override
    public boolean prepare() {
        Logger.getGlobal().info("Example interceptor prepared!");

        return true;
    }

    @Override
    public void stop() {
        Logger.getGlobal().info("Example interceptor stopped!");
    }
}
