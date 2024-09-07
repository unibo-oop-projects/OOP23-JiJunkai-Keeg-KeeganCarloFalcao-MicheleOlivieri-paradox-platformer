package com.project.paradoxplatformer;

import com.project.paradoxplatformer.view.Page;
import com.project.paradoxplatformer.view.legacy.ViewLegacy;

import javafx.application.Platform;

public abstract class AbstractThreadedPage implements Page<String>{


    public AbstractThreadedPage() {

    }

    @Override
    public void create(String param) throws Exception {
         // Ensure that the code runs on the JavaFX application thread
        if (!Platform.isFxApplicationThread()) {
            ViewLegacy.javaFxFactory().mainAppManager().get().runOnAppThread(() -> safelyRunOnFXThread(param));
        } else {
            safelyRunOnFXThread(param);
        }
    }

    private void safelyRunOnFXThread(String param) {
        try {
            this.runOnFXThread(param); // Run the given code on the JavaFX thread
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e); // Wrap and rethrow exception
        }
    }

    protected abstract void runOnFXThread(String param) throws Exception;
    
}
