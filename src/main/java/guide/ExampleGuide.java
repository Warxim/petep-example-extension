package guide;

import com.warxim.petep.gui.guide.Guide;

/**
 * Guide for example extension.
 */
public class ExampleGuide extends Guide {
    @Override
    public String getHtml() {
        return loadHtmlResource("/html/ExampleGuide.html");
    }

    @Override
    public String getTitle() {
        return "Example Guide";
    }
}
