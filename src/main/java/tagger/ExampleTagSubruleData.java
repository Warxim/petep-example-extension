package tagger;

import com.warxim.petep.extension.internal.tagger.factory.TagSubruleData;

public class ExampleTagSubruleData extends TagSubruleData {
  private String stringParam;

  public ExampleTagSubruleData(String stringParam) {
    this.stringParam = stringParam;
  }

  public String getStringParam() {
    return stringParam;
  }

  public void setStringParam(String stringParam) {
    this.stringParam = stringParam;
  }
}
