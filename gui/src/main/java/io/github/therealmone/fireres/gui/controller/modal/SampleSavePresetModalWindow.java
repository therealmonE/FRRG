package io.github.therealmone.fireres.gui.controller.modal;

import com.google.inject.Inject;
import io.github.therealmone.fireres.core.model.Sample;
import io.github.therealmone.fireres.gui.ApplicationConfig;
import io.github.therealmone.fireres.gui.annotation.LoadableComponent;
import io.github.therealmone.fireres.gui.annotation.ModalWindow;
import io.github.therealmone.fireres.gui.controller.AbstractComponent;
import io.github.therealmone.fireres.gui.controller.SampleContainer;
import io.github.therealmone.fireres.gui.controller.common.SampleTab;
import io.github.therealmone.fireres.gui.preset.Preset;
import io.github.therealmone.fireres.gui.service.AlertService;
import io.github.therealmone.fireres.gui.service.PresetService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.UUID;

@Slf4j
@LoadableComponent("/component/modal/sampleSavePresetModalWindow.fxml")
@ModalWindow(title = "Сохранить как пресет")
public class SampleSavePresetModalWindow extends AbstractComponent<Pane> implements SampleContainer {

    @Inject
    ApplicationConfig applicationConfig;
    @FXML
    private TextField descriptionTextField;
    @ModalWindow.Window
    @Getter
    private Stage window;
    @Inject
    private AlertService alertService;
    @Inject
    private PresetService presetService;

    @Override
    protected void initialize() {
        descriptionTextField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                savePresetSample();
            }
        });
    }

    @Override
    public void postConstruct() {
        val currentSampleName = getSample().getSampleProperties().getName();

        descriptionTextField.setText(currentSampleName);
    }

    public void closeWindow() {
        window.close();
    }

    @SneakyThrows
    public void savePresetSample() {
        log.info("Save sample button pressed");

        if (validateSampleNamePreset()) {
            Preset newPreset = new Preset();
            newPreset.setProperties(getSample().getSampleProperties().getPropertiesMap());
            newPreset.setDescription(descriptionTextField.getText());
            newPreset.setApplyingByDefault(false);
            presetService.savePreset(newPreset, applicationConfig.getCustomPresetsPath() + "/custom_preset_" + UUID.randomUUID() + ".json");
            presetService.getAvailablePresets().add(newPreset);
            closeWindow();
        }
    }

    private boolean validateSampleNamePreset() {
        val presets = presetService.getAvailablePresets();
        val newSampleDescription = descriptionTextField.getText();

        if (newSampleDescription == null || newSampleDescription.isEmpty() || newSampleDescription.isBlank()) {
            alertService.showError("Описание пресета не может быть пустым");
            return false;
        }

        for (Preset preset : presets) {
            val description = preset.getDescription();

            if (newSampleDescription.equals(description)) {
                alertService.showError("Пресет с таким описанием уже существует");
                return false;
            }
        }

        return true;
    }

    @Override
    public Sample getSample() {
        return ((SampleTab) getParent()).getSample();
    }
}
