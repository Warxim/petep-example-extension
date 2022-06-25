package tagger;

import com.warxim.petep.core.pdu.PDU;
import com.warxim.petep.extension.internal.tagger.factory.TagSubrule;
import com.warxim.petep.extension.internal.tagger.factory.TagSubruleData;
import com.warxim.petep.extension.internal.tagger.factory.TagSubruleFactory;
import pdu.ExamplePdu;

public class ExampleTagSubrule extends TagSubrule {
    public ExampleTagSubrule(TagSubruleFactory factory, TagSubruleData data) {
        super(factory, data);
    }

    @Override
    public boolean test(PDU pdu) {
        if (!(pdu instanceof ExamplePdu)) {
            return false;
        }

        // Check if string param equals configured value
        return (((ExamplePdu) pdu)
                .getStringParam()
                .equals(((ExampleTagSubruleData) data).getStringParam()));
    }

    @Override
    public String toString() {
        return "StringParam equals '" + ((ExampleTagSubruleData) data).getStringParam() + "'";
    }
}
