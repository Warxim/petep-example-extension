package modifier;

import com.warxim.petep.extension.internal.modifier.factory.Modifier;
import com.warxim.petep.extension.internal.modifier.factory.ModifierConfigurator;
import com.warxim.petep.extension.internal.modifier.factory.ModifierData;
import com.warxim.petep.extension.internal.modifier.factory.ModifierFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Optional;

/**
 * Example modifier factory for creating example modifiers.
 */
public class ExampleModifierFactory extends ModifierFactory {
    @Override
    public String getCode() {
        return "example-modifier";
    }

    @Override
    public String getName() {
        return "Example - Replace in StringParam";
    }

    @Override
    public Modifier createModifier(ModifierData data) {
        return new ExampleModifier(this, data);
    }

    @Override
    public Optional<Type> getConfigType() {
        return Optional.of(ExampleModifierData.class);
    }
    
    @Override
    public Optional<ModifierConfigurator> createConfigPane() throws IOException {
        return Optional.of(new ExampleModifierConfigurator());
    }
}
