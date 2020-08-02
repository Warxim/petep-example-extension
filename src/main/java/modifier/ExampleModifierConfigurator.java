package modifier;

import java.io.IOException;

import com.warxim.petep.extension.internal.modifier.factory.ModifierConfigurator;
import com.warxim.petep.extension.internal.modifier.factory.ModifierData;
import com.warxim.petep.gui.dialog.Dialogs;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ExampleModifierConfigurator extends ModifierConfigurator {
  @FXML private TextField whatInput;
  @FXML private TextField withInput;

  public ExampleModifierConfigurator() throws IOException {
    super("/fxml/ExampleModifierConfigurator.fxml");
  }

  @Override
  public ModifierData getConfig() {
    return new ExampleModifierData(whatInput.getText(), withInput.getText());
  }

  @Override
  public boolean isValid() {
    if (whatInput.getText().isEmpty()) {
      Dialogs.createErrorDialog(
          "What required", "You have to enter what you want to find in stringParam.");
      return false;
    }

    return true;
  }

  @Override
  public void setConfig(ModifierData data) {
    whatInput.setText(((ExampleModifierData) data).getWhat());
    withInput.setText(((ExampleModifierData) data).getWith());
  }
}
