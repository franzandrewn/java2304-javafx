package com.andrewn.java2304javafx;

import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class BaseController {
    private Stage currentStage;
    private Scene nextScene;

    public void setCurrentStage(Stage currentStage) {
        this.currentStage = currentStage;
    }

    public void setNextScene(Scene nextScene) {
        this.nextScene = nextScene;
    }

    public void goToNextScene() {
        currentStage.setScene(nextScene);
    }
}
