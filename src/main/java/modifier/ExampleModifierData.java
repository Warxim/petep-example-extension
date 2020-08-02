package modifier;

import com.warxim.petep.extension.internal.modifier.factory.ModifierData;

public class ExampleModifierData extends ModifierData {
  private String what;
  private String with;

  public ExampleModifierData(String what, String with) {
    this.what = what;
    this.with = with;
  }

  public String getWhat() {
    return what;
  }

  public void setWhat(String what) {
    this.what = what;
  }

  public String getWith() {
    return with;
  }

  public void setWith(String with) {
    this.with = with;
  }
}
