package tagger;

import com.warxim.petep.extension.internal.tagger.factory.TagSubruleData;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper=true)
public class ExampleTagSubruleData extends TagSubruleData {
    String stringParam;
}
