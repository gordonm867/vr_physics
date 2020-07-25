package virtual_robot.controller;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * For internal use only. Main class for the JavaFX application.
 */
public class VirtualRobotApplication extends Application {

    private static VirtualRobotController controllerHandle;
    private static VirtualRobotApplication instance;

    public boolean right;
    public boolean left;
    public boolean up;
    public boolean down;
    public boolean a;
    public boolean b;
    public boolean x;
    public boolean y;
    public boolean q;
    public boolean e;
    public boolean w;
    public boolean h;
    public boolean g;
    public boolean skey;

    public VirtualRobotApplication() {
         instance = this;
    }

    public static VirtualRobotApplication getInstance() {
        return instance;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("virtual_robot.fxml"));
        Parent root = (BorderPane)loader.load();
        controllerHandle = loader.getController();
        primaryStage.setTitle("Virtual Robot");
        Scene myScene = new Scene(root);
        myScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case I:     up      = true; break;
                    case K:     down    = true; break;
                    case J:     left    = true; break;
                    case L:     right   = true; break;
                    case A:     a       = true; break;
                    case B:     b       = true; break;
                    case X:     x       = true; break;
                    case Y:     y       = true; break;
                    case E:     e       = true; break;
                    case Q:     q       = true; break;
                    case S:     skey    = true; break;
                    case W:     w       = true; break;
                    case H:     h       = true; break;
                    case G:     g       = true; break;
                }
            }
        });
        myScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case I:     up      = false; break;
                    case K:     down    = false; break;
                    case J:     left    = false; break;
                    case L:     right   = false; break;
                    case A:     a       = false; break;
                    case B:     b       = false; break;
                    case X:     x       = false; break;
                    case Y:     y       = false; break;
                    case E:     e       = false; break;
                    case Q:     q       = false; break;
                    case S:     skey    = false; break;
                    case W:     w       = false; break;
                    case H:     h       = false; break;
                    case G:     g       = false; break;
                }
            }
        });
        primaryStage.setScene(myScene);
        primaryStage.setResizable(true);
        primaryStage.setOnShowing(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                controllerHandle.setConfig(null);
            }
        });
        primaryStage.show();
    }

    @Override
    public void stop() {
        if (controllerHandle.displayExecutorService != null && !controllerHandle.displayExecutorService.isShutdown()) {
            controllerHandle.displayExecutorService.shutdownNow();
        }
        if (controllerHandle.physicsExecutorService != null && !controllerHandle.physicsExecutorService.isShutdown()) {
            controllerHandle.physicsExecutorService.shutdownNow();
        }
        if (controllerHandle.gamePadExecutorService != null && !controllerHandle.gamePadExecutorService.isShutdown()) {
            controllerHandle.gamePadExecutorService.shutdownNow();
        }
        controllerHandle.gamePadHelper.quit();
        controllerHandle.shutDownODE();
    }

    public static VirtualRobotController getControllerHandle(){return controllerHandle;}


    public static void main(String[] args) {
        launch(args);
    }
}
