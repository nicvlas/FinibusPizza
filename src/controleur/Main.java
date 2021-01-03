package controleur;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.*;


public class Main extends Application {
    public static Parent root;
    public static Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            stage = primaryStage;
            //Création écran Menu
            root = FXMLLoader.load(getClass().getResource("../fxml/Menu.fxml"));
            primaryStage.setTitle("PizzaFinibus : Menu");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                NiveauAPCControleur.arret = true;
            }
        });
    }
    public static void main(String[] args) {
        launch(args);
    }
    public static void changementFenetre(String chemin, String titre) throws IOException {
        root = FXMLLoader.load(Main.class.getResource(chemin));
        stage.setTitle(titre);
        stage.setScene(new Scene(root));
        stage.show();
    }

}
