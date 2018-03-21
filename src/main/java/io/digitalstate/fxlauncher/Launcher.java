package io.digitalstate.fxlauncher;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.SimpleBindings;
import javax.script.ScriptContext;

import java.io.FileReader;
import java.util.Map;

import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application {
    
    public static String entryPoint;

    public static void main(String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        if (System.getProperty("nashorn.args") == null) {
            System.setProperty("nashorn.args", "--language=es6");
        }

        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        SimpleBindings bindings = new SimpleBindings();
        bindings.put("$STAGE", primaryStage);

        Map<String, String> parameters = getParameters().getNamed();
        for (Map.Entry<String, String> entry : parameters.entrySet())
        {
            if (entry.getKey().equals("entrypoint")) {
               entryPoint = entry.getValue();
            }
        }
        // @TODO Add error handling for missing entrypoint param

        FileReader entryFile = new FileReader(entryPoint);

        engine.setBindings(bindings, ScriptContext.ENGINE_SCOPE);
        engine.eval(entryFile);
    }

}