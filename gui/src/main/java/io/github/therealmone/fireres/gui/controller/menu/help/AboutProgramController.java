package io.github.therealmone.fireres.gui.controller.menu.help;

import com.google.inject.Inject;
import io.github.therealmone.fireres.gui.annotation.ParentController;
import io.github.therealmone.fireres.gui.controller.AbstractController;
import io.github.therealmone.fireres.gui.controller.TopMenuBarController;
import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@EqualsAndHashCode(callSuper = true)
@Slf4j
@Data
public class AboutProgramController extends AbstractController {

    @ParentController
    private TopMenuBarController topMenuBarController;

    private Stage modalWindow;

    @FXML
    private Hyperlink repositoryLink;

    @Inject
    private HostServices hostServices;

    public void openGithubLink() {
        hostServices.showDocument(repositoryLink.getText());
    }

}
