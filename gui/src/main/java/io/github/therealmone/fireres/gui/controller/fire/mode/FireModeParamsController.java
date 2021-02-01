package io.github.therealmone.fireres.gui.controller.fire.mode;

import com.google.inject.Inject;
import io.github.therealmone.fireres.core.config.GenerationProperties;
import io.github.therealmone.fireres.core.config.SampleProperties;
import io.github.therealmone.fireres.gui.annotation.ParentController;
import io.github.therealmone.fireres.gui.controller.AbstractController;
import io.github.therealmone.fireres.gui.controller.SampleContainer;
import io.github.therealmone.fireres.gui.service.ResetSettingsService;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@EqualsAndHashCode(callSuper = true)
@Data
@Slf4j
public class FireModeParamsController extends AbstractController implements SampleContainer {

    @ParentController
    private FireModePaneController fireModePaneController;

    @FXML
    private Spinner<Integer> thermocoupleSpinner;

    @Inject
    private ResetSettingsService resetSettingsService;

    @Inject
    private GenerationProperties generationProperties;

    private void handleSpinnerFocusChanged(Boolean newValue, Spinner<?> spinner) {
        if (!newValue) {
            log.info("Spinner {} lost focus, sample id: {}", spinner.getId(), getSampleProperties().getId());
            commitSpinner(spinner);
        }
    }

    @Override
    public SampleProperties getSampleProperties() {
        return fireModePaneController.getSampleProperties();
    }

    @Override
    protected void initialize() {
        thermocoupleSpinner.focusedProperty().addListener((observable, oldValue, newValue) ->
                handleSpinnerFocusChanged(newValue, thermocoupleSpinner));
    }

    @Override
    public void postConstruct() {
        resetSettingsService.resetFireModeParameters(this);
    }
}
