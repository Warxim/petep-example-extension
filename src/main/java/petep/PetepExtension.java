package petep;

import com.warxim.petep.common.ContextType;
import com.warxim.petep.extension.Extension;
import com.warxim.petep.extension.internal.history.HistoryApi;
import com.warxim.petep.extension.internal.modifier.ModifierApi;
import com.warxim.petep.extension.internal.tagger.TaggerApi;
import com.warxim.petep.helper.ExtensionHelper;
import com.warxim.petep.helper.GuiHelper;
import gui.ExampleAppTabController;
import guide.ExampleGuide;
import interceptor.ExampleInterceptorModuleFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import modifier.ExampleModifierFactory;
import proxy.ExampleProxyModuleFactory;
import receiver.ExampleReceiver;
import tagger.ExampleTagSubruleFactory;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Example PETEP extension.
 */
public class PetepExtension extends Extension {
    private HistoryApi historyApi;
    private ExampleReceiver receiver;
    private ExtensionHelper extensionHelper;

    public PetepExtension(String path) {
        super(path);
    }

    @Override
    public String getCode() {
        return "example";
    }

    @Override
    public String getName() {
        return "Example extension";
    }

    @Override
    public String getDescription() {
        return "Example extension for developers.";
    }

    @Override
    public String getVersion() {
        return "0.0.42";
    }

    @Override
    public void beforeInit(ExtensionHelper helper) {
        registerTaggerSubruleFactory(helper);
        registerModifierSubruleFactory(helper);
        registerReceiver(helper);
    }

    @Override
    public void init(ExtensionHelper helper) {
        helper.registerProxyModuleFactory(new ExampleProxyModuleFactory(this));
        helper.registerInterceptorModuleFactory(new ExampleInterceptorModuleFactory(this));

        Logger.getGlobal().info("Example extension loaded!");
    }

    @Override
    public void afterInit(ExtensionHelper helper) {
        // Get history extension and save it as API
        var maybeHistory = helper.getExtension("history");
        if (maybeHistory.isPresent()) {
            historyApi = (HistoryApi) maybeHistory.get();
        }
    }

    @Override
    public void initGui(GuiHelper helper) {
        helper.registerGuide(new ExampleGuide());
        registerAppTab(helper);
        registerSettingsTab(helper);
        Logger.getGlobal().info("Example extension GUI loaded!");
    }

    /**
     * Registers app tab.
     */
    private void registerAppTab(GuiHelper helper) {
        try {
            // Create example tab
            var loader = new FXMLLoader(getClass().getResource("/fxml/ExampleAppTab.fxml"));
            var controller = new ExampleAppTabController(extensionHelper, historyApi);
            loader.setController(controller);
            var appTab = (Parent) loader.load();

            // Set the consumer of our receiver to the controller method
            receiver.setConsumer(controller::showSerializedPdu);
            helper.registerTab("Example App Tab", appTab);
        } catch (IOException e) {
            Logger.getGlobal().log(Level.SEVERE, "Could not load example app tab.", e);
        }
    }

    /**
     * Registers settings tab.
     */
    private void registerSettingsTab(GuiHelper helper) {
        try {
            Parent settingsTab = FXMLLoader.load(getClass().getResource("/fxml/ExampleSettingsTab.fxml"));
            helper.registerSettingsTab("Example Settings Tab", settingsTab);
        } catch (IOException e) {
            Logger.getGlobal().log(Level.SEVERE, "Could not load example settings tab.", e);
        }
    }

    /**
     * Registers receiver.
     */
    private void registerReceiver(ExtensionHelper helper) {
        // If we have GUI, register receiver to receive PDUs
        if (helper.getContextType().equals(ContextType.GUI)) {
            receiver = new ExampleReceiver();
            helper.registerReceiver(receiver);
            extensionHelper = helper;
        }
    }

    /**
     * Registers example tag subrule factory to tagger extension.
     */
    private void registerTaggerSubruleFactory(ExtensionHelper helper) {
        // Find tagger extension and use it as API
        var maybeTagger = helper.getExtension("tagger");
        if (maybeTagger.isEmpty()) {
            return;
        }
        var tagger = (TaggerApi) maybeTagger.get();

        // Register example tag subrule
        if (tagger.registerSubruleFactory(new ExampleTagSubruleFactory())) {
            Logger.getGlobal().info("Example tag subrule registered!");
        }
    }

    /**
     * Registers example modifier factory to modifier extension.
     */
    private void registerModifierSubruleFactory(ExtensionHelper helper) {
        // Find modifier extension and use it as API
        var maybeModifier = helper.getExtension("modifier");
        if (maybeModifier.isEmpty()) {
            return;
        }
        var modifier = (ModifierApi) maybeModifier.get();

        // Register example modifier
        if (modifier.registerModifierFactory(new ExampleModifierFactory())) {
            Logger.getGlobal().info("Example modifier registered!");
        }
    }
}
