package tagger;

import com.warxim.petep.extension.internal.tagger.factory.TagSubrule;
import com.warxim.petep.extension.internal.tagger.factory.TagSubruleConfigurator;
import com.warxim.petep.extension.internal.tagger.factory.TagSubruleData;
import com.warxim.petep.extension.internal.tagger.factory.TagSubruleFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Optional;

public class ExampleTagSubruleFactory extends TagSubruleFactory {
    @Override
    public String getCode() {
        return "example-tagger";
    }

    @Override
    public String getName() {
        return "Example - String param equals";
    }

    @Override
    public TagSubrule createSubrule(TagSubruleData data) {
        return new ExampleTagSubrule(this, data);
    }

    @Override
    public Optional<Type> getConfigType() {
        return Optional.of(ExampleTagSubruleData.class);
    }

    @Override
    public Optional<TagSubruleConfigurator> createConfigPane() throws IOException {
        return Optional.of(new ExampleTagSubruleConfigurator());
    }
}
