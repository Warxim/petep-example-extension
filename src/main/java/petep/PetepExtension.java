package petep;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.warxim.petep.extension.Extension;
import com.warxim.petep.extension.ExtensionInitListener;
import com.warxim.petep.extension.internal.modifier.ModifierApi;
import com.warxim.petep.extension.internal.tagger.TaggerApi;
import com.warxim.petep.helper.ExtensionHelper;
import com.warxim.petep.helper.GuiHelper;
import guide.ExampleGuide;
import interceptor.ExampleInterceptorModuleFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import modifier.ExampleModifierFactory;
import proxy.ExampleProxyModuleFactory;
import tagger.ExampleTagSubruleFactory;

public class PetepExtension extends Extension implements ExtensionInitListener {
  public PetepExtension(String path) {
    super(path);
  }

  @Override
  public String getCode() {
    return "example";
  }

  @Override
  public String getName() {
    return "Example extension.";
  }

  @Override
  public String getDescription() {
    return "Example extension for developers.";
  }

  @Override
  public String getVersion() {
    return "0.0.1";
  }

  @Override
  public void beforeInit(ExtensionHelper helper) {
    Extension tagger = helper.getExtension("tagger");

    if (tagger == null) {
      return;
    }

    if (((TaggerApi) tagger).registerSubruleFactory(new ExampleTagSubruleFactory())) {
      Logger.getGlobal().info("Example tag subrule registered!");
    }

    Extension modifier = helper.getExtension("modifier");

    if (modifier == null) {
      return;
    }

    if (((ModifierApi) modifier).registerModifierFactory(new ExampleModifierFactory())) {
      Logger.getGlobal().info("Example modifier registered!");
    }
  }

  @Override
  public void init(ExtensionHelper helper) {
    helper.registerProxyModuleFactory(new ExampleProxyModuleFactory(this));
    helper.registerInterceptorModuleFactory(new ExampleInterceptorModuleFactory(this));

    Logger.getGlobal().info("Example extension loaded!");
  }

  @Override
  public void afterInit(ExtensionHelper helper) {}

  @Override
  public void initGui(GuiHelper helper) {
    helper.registerGuide(new ExampleGuide());

    try {
      Parent appTab = FXMLLoader.load(getClass().getResource("/fxml/ExampleTab.fxml"));
      Parent settingsTab = FXMLLoader.load(getClass().getResource("/fxml/ExampleTab.fxml"));

      helper.registerTab("Example Tab 1", appTab);
      helper.registerSettingsTab("Example Tab 2", settingsTab);
    } catch (IOException e) {
      Logger.getGlobal().log(Level.SEVERE, "Could not load example tab.", e);
    }

    Logger.getGlobal().info("Example extension GUI loaded!");
  }
}
