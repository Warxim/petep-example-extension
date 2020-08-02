package modifier;

import com.warxim.petep.core.pdu.PDU;
import com.warxim.petep.extension.internal.modifier.factory.Modifier;
import com.warxim.petep.extension.internal.modifier.factory.ModifierData;
import com.warxim.petep.extension.internal.modifier.factory.ModifierFactory;

import pdu.ExamplePdu;

public class ExampleModifier extends Modifier {
  public ExampleModifier(ModifierFactory factory, ModifierData data) {
    super(factory, data);
  }

  @Override
  public boolean process(PDU pdu) {
    if (!(pdu instanceof ExamplePdu)) {
      return true;
    }

    ExamplePdu examplePdu = ((ExamplePdu) pdu);

    examplePdu.setStringParam(
        examplePdu
            .getStringParam()
            .replace(
                ((ExampleModifierData) data).getWhat(), ((ExampleModifierData) data).getWith()));

    return true;
  }
}
