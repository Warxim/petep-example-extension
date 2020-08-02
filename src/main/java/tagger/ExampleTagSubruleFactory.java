package tagger;

import java.io.IOException;
import java.lang.reflect.Type;

import com.warxim.petep.extension.internal.tagger.factory.TagSubrule;
import com.warxim.petep.extension.internal.tagger.factory.TagSubruleConfigurator;
import com.warxim.petep.extension.internal.tagger.factory.TagSubruleData;
import com.warxim.petep.extension.internal.tagger.factory.TagSubruleFactory;

public class ExampleTagSubruleFactory extends TagSubruleFactory {

  @Override
  public TagSubruleConfigurator createConfigPane() throws IOException {
    return new ExampleTagSubruleConfigurator();
  }

  @Override
  public TagSubrule createSubrule(TagSubruleData data) {
    return new ExampleTagSubrule(this, data);
  }

  @Override
  public String getCode() {
    return "example-string";
  }

  @Override
  public String getName() {
    return "Example - String param equals";
  }

  @Override
  public Type getConfigType() {
    return ExampleTagSubruleData.class;
  }
}
