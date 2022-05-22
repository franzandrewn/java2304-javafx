package com.andrewn.java2304javafx;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private void helloWorld(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    // Определить три кнопки на интерфейса, каждой из них задать fx:id и onAction
    // Первая кнопка должна менять текст второй кнопки, вторая - текст третьей и третья - текст первой и второй
    // Для этого в контроллере нужно будет создать поля под каждую кнопку с именем как fx:id
    // и метод под каждый onAction
    // задание со *: кроме текста поменять ещё цвет текста/фона
    // задания со **: посмотреть что-то кроме onAction
    private void task1(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("three-buttons.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Three buttons!");
        stage.setScene(scene);
        stage.show();
    }

    private void manualInterface(Stage stage) {
        Scene scene = new Scene(createContent(), 400, 400);
        stage.setTitle("Manual UI!");
        stage.setScene(scene);
        stage.show();
    }

    private Parent createContent() {
        HBox parent = new HBox();
        parent.setAlignment(Pos.CENTER);
        parent.setPrefSize(400, 600);
        parent.setSpacing(50.0);

        Label welcomeText = new Label();
        Button helloButton = new Button("Нажми меня!");
        parent.getChildren().addAll(welcomeText, helloButton);

        // Наследование -> анонимные классы -> лямбда-выражения
        // Наследование - создание класса, реализующего EventHandler
//        helloButton.setOnAction(new HelloButtonActionHandler(welcomeText));
        // Анонимные классы - создание класса без имени, который используются только единожды
//        helloButton.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                System.out.println("Привет из анонимного класса " + getClass().getName());
//                welcomeText.setText("hello world from anonym class");
//            }
//        });
        // Лямбда-выражения - определение действия в одну строчку
        helloButton.setOnAction(actionEvent -> {
            System.out.println(actionEvent.getEventType());
            System.out.println("Привет из лямбда-выражения " + getClass().getName());
            welcomeText.setText("hello world from lambda expression");
        });
        helloButton.setOnMouseEntered(mouseEvent -> helloButton.setText("На мне мышь"));
        helloButton.setOnMouseExited(mouseEvent -> helloButton.setText("Мышь ушла"));
        return parent;
    }

    private void changeScenes(Stage stage) throws IOException{
        FXMLLoader loginLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        FXMLLoader loggedLoader = new FXMLLoader(HelloApplication.class.getResource("logged.fxml"));

        Scene loginScene = new Scene(loginLoader.load(), 400, 600);
        Scene loggedScene = new Scene(loggedLoader.load(), 400, 600);

        loginScene.getStylesheets().add(String.valueOf(HelloApplication.class.getResource("stylesheet.css")));
        System.out.println(loginScene.getStylesheets());

        LoginController loginController = loginLoader.getController();
        loginController.setCurrentStage(stage);
        loginController.setNextScene(loggedScene);

        LoggedController loggedController = loggedLoader.getController();
        loggedController.setCurrentStage(stage);
        loggedController.setNextScene(loginScene);

        stage.setTitle("Changing scenes!");
        stage.setScene(loginScene);
        stage.show();
    }

    private void startWithThread(Stage stage) {
        VBox root =  new VBox(5);
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.CENTER);

        ProgressBar progressBar = new ProgressBar(0.0);

        Label barProgress = new Label();
        Label powProgress = new Label();

        Button buttonProgress = new Button("Start progress bar");
        buttonProgress.setOnAction(event -> startProgressBar(progressBar, barProgress));

        Button buttonPow = new Button("Start 2.9^100 calculate");
        buttonPow.setOnAction(event -> startPow(powProgress));

        HBox temp = new HBox(5);
        temp.setAlignment(Pos.CENTER);
        temp.getChildren().addAll(
                new Label("Current step: "),
                barProgress
        );

        root.getChildren().addAll(
                progressBar,
                temp,
                buttonProgress,
                powProgress,
                buttonPow
        );

        stage.setWidth(300);
        stage.setHeight(300);
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void startProgressBar(ProgressBar progressBar, Label barProgress) {
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                int steps = 1000;
                for (int i = 0; i < steps; i++) {
                    Thread.sleep(10);
                    updateProgress(i, steps);
                    updateMessage(String.valueOf(i));
                }
                return null;
            }
        };

        task.setOnFailed(workerStateEvent -> {
            workerStateEvent.getSource().getException().printStackTrace();
        });

        task.setOnSucceeded(workerStateEvent -> {
            System.out.println("Done!");
        });

        progressBar.progressProperty().bind(task.progressProperty());
        barProgress.textProperty().bind(task.messageProperty());

        new Thread(task).start();
    }

    private void startPow(Label powProgress) {
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                int steps = 99;
                double initValue = 2.9;
                for (int i = 0; i < steps; i++) {
                    initValue *= 2.9;
                    Thread.sleep(100);
                    updateProgress(i, steps);
                    updateMessage(String.valueOf(initValue));
                }
                return null;
            }
        };

        task.setOnFailed(workerStateEvent -> {
            workerStateEvent.getSource().getException().printStackTrace();
        });

        task.setOnSucceeded(workerStateEvent -> {
            System.out.println("Pow done!");
        });

        powProgress.textProperty().bind(task.messageProperty());

        new Thread(task).start();
    }

    private void startWithoutThread(Stage stage) {
        VBox root =  new VBox(5);
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.CENTER);

        ProgressBar progressBar = new ProgressBar(0.0);

        Label barProgress = new Label();
        Label powProgress = new Label();

        Button buttonProgress = new Button("Start progress bar");
        buttonProgress.setOnAction(event -> startProgressBarAlt(progressBar, barProgress));

        Button buttonPow = new Button("Start 2.9^100 calculate");
        buttonPow.setOnAction(event -> startPowAlt(powProgress));

        HBox temp = new HBox(5);
        temp.setAlignment(Pos.CENTER);
        temp.getChildren().addAll(
                new Label("Current step: "),
                barProgress
        );

        root.getChildren().addAll(
                progressBar,
                temp,
                buttonProgress,
                powProgress,
                buttonPow
        );

        stage.setWidth(300);
        stage.setHeight(300);
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void startPowAlt(Label powProgress) {
        double initValue = 2.9;
        for (int i = 0; i < 99; i++) {
            initValue *= 2.9;
            powProgress.setText(String.valueOf(initValue));
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void startProgressBarAlt(ProgressBar progressBar, Label barProgress) {
        int steps = 1000;
        for (int i = 0; i < steps; i++) {
            progressBar.progressProperty().setValue((double) i / steps);
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
//        helloWorld(stage);
//        task1(stage);
//        manualInterface(stage);
        changeScenes(stage);
//        startWithoutThread(stage);
//        startWithThread(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}