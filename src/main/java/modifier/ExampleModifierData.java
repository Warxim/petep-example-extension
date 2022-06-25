package modifier;

import com.warxim.petep.extension.internal.modifier.factory.ModifierData;
import lombok.EqualsAndHashCode;
import lombok.Value;

/**
 * Configuration of example modifier.
 */
@Value
@EqualsAndHashCode(callSuper=true)
public class ExampleModifierData extends ModifierData {
    String what;
    String with;
}
