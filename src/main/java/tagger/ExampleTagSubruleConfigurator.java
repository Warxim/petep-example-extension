package tagger;

import com.warxim.petep.extension.internal.tagger.factory.TagSubruleConfigurator;
import com.warxim.petep.extension.internal.tagger.factory.TagSubruleData;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ExampleTagSubruleConfigurator extends TagSubruleConfigurator {
    @FXML
    private TextField stringInput;

    public ExampleTagSubruleConfigurator() throws IOException {
        super("/fxml/ExampleTagSubruleConfigurator.fxml");
    }

    @Override
    public TagSubruleData getConfig() {
        return new ExampleTagSubruleData(stringInput.getText());
    }

    @Override
    public void setConfig(TagSubruleData data) {
        stringInput.setText(((ExampleTagSubruleData) data).getStringParam());
    }

    @Override
    public boolean isValid() {
        return true;
    }
}
