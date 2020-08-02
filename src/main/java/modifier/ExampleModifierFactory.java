package modifier;

import java.io.IOException;
import java.lang.reflect.Type;

import com.warxim.petep.extension.internal.modifier.factory.Modifier;
import com.warxim.petep.extension.internal.modifier.factory.ModifierConfigurator;
import com.warxim.petep.extension.internal.modifier.factory.ModifierData;
import com.warxim.petep.extension.internal.modifier.factory.ModifierFactory;

public class ExampleModifierFactory extends ModifierFactory {
  @Override
  public ModifierConfigurator createConfigPane() throws IOException {
    return new ExampleModifierConfigurator();
  }

  @Override
  public Modifier createModifier(ModifierData data) {
    return new ExampleModifier(this, data);
  }

  @Override
  public String getCode() {
    return "example-replace";
  }

  @Override
  public Type getConfigType() {
    return ExampleModifierData.class;
  }

  @Override
  public String getName() {
    return "Example - Replace in StringParam";
  }
}
