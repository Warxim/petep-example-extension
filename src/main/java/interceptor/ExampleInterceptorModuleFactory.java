package interceptor;

import com.warxim.petep.extension.Extension;
import com.warxim.petep.interceptor.factory.InterceptorModuleFactory;
import com.warxim.petep.interceptor.module.InterceptorModule;

/**
 * Example interceptor module factory for creating example interceptor modules.
 */
public class ExampleInterceptorModuleFactory extends InterceptorModuleFactory {
    public ExampleInterceptorModuleFactory(Extension extension) {
        super(extension);
    }

    @Override
    public String getCode() {
        return "example-interceptor";
    }

    @Override
    public String getName() {
        return "Example interceptor";
    }

    @Override
    public InterceptorModule createModule(String code, String name, String description, boolean enabled) {
        return new ExampleInterceptorModule(this, code, name, description, enabled);
    }
}
