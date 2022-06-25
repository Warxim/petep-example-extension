package modifier;

import com.warxim.petep.core.pdu.PDU;
import com.warxim.petep.extension.internal.modifier.factory.Modifier;
import com.warxim.petep.extension.internal.modifier.factory.ModifierData;
import com.warxim.petep.extension.internal.modifier.factory.ModifierFactory;
import pdu.ExamplePdu;

/**
 * Example modifier that replaces string params (metadata) in example PDUs.
 */
public class ExampleModifier extends Modifier {
    public ExampleModifier(ModifierFactory factory, ModifierData data) {
        super(factory, data);
    }

    @Override
    public boolean process(PDU pdu) {
        if (!(pdu instanceof ExamplePdu)) {
            return true;
        }

        var examplePdu = (ExamplePdu) pdu;
        var exampleModifierData = (ExampleModifierData) data;

        var currentStringParam = examplePdu.getStringParam();
        var newStringParam = currentStringParam.replace(exampleModifierData.getWhat(), exampleModifierData.getWith());
        examplePdu.setStringParam(newStringParam);

        return true;
    }
}
