package com.example.snakegame;

import Model.*;
import javafx.animation.*;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application
{
    private IntegerProperty score = new SimpleIntegerProperty(0);
    @Override
    public void start(Stage stage) throws IOException
    {
        BorderPane bp = new BorderPane();
        Scene scene = new Scene(bp, 800, 600);

        // Adds gridPane for score
        GridPane gridPane = new GridPane();
        gridPane.setMinHeight(20);
        gridPane.prefWidth(scene.getWidth());
        bp.setTop(gridPane);

        // Label for score
        Label scoreLabel = new Label("Score: ");
        scoreLabel.textProperty().bind(Bindings.concat("Score: ", score.asString()));
        gridPane.add(scoreLabel,0,0);

        // Creates 2 canvases one for the map and one for the snake and food
        Canvas background = new Canvas();
        background.setHeight(scene.getHeight() - gridPane.getMinHeight());
        background.setWidth(scene.getWidth());
        Canvas foreground = new Canvas();
        foreground.setHeight(scene.getHeight() - gridPane.getMinHeight());
        foreground.setWidth(scene.getWidth());

        // Adds canvases to stackPane
        StackPane stackPane = new StackPane(background, foreground);

        // Sets stackPane to center of BorderPane
        bp.setCenter(stackPane);

        // Draws the board
        GameBoard gameBoard = new GameBoard((int)background.getWidth(), (int) background.getHeight(), 20);
        GraphicsContext backgroundGc = background.getGraphicsContext2D();
        gameBoard.drawBoard(backgroundGc);

        // Draws the snake
        GraphicsContext foregroundGc = foreground.getGraphicsContext2D();
        Snake snake = new Snake(gameBoard.getTileSize(), gameBoard.getTileSize(), gameBoard.getTileSize(), Direction.RIGHT);
        snake.draw(foregroundGc);

        Food food = new Food(0, 0, gameBoard.getTileSize());
        food.randomizePosition(gameBoard);
        food.draw(foregroundGc);

        new AnimationTimer()
        {
            private long lastUpdate = 0;

            public void handle(long now)
            {
                if (now - lastUpdate >= 100_000_000)
                {
                    // Checks collision with wall

                    if (snake.checkWallCollision(gameBoard.getBoardWidth(), gameBoard.getBoardHeight()) || snake.checkSnakeCollision())
                    {
                        stop();
                    }

                    // Checks collision with food
                    if (snake.checkFoodCollision(food))
                    {
                        snake.grow();
                        score.set(score.get() + 1);
                        food.erase(foregroundGc);
                        food.randomizePosition(gameBoard);
                        food.draw(foregroundGc);
                    }

                    // Draws and moves the snake
                    snake.erase(foregroundGc);
                    snake.move();
                    snake.draw(foregroundGc);

                    lastUpdate = now;
                }
            }
        }.start();



        foreground.setFocusTraversable(true);
        foreground.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent keyEvent)
            {
                if (keyEvent.getCode() == KeyCode.UP)
                {
                    snake.setDirection(Direction.UP);
                }
                if (keyEvent.getCode() == KeyCode.DOWN)
                {
                    snake.setDirection(Direction.DOWN);
                }
                if (keyEvent.getCode() == KeyCode.RIGHT)
                {
                    snake.setDirection(Direction.RIGHT);
                }
                if (keyEvent.getCode() == KeyCode.LEFT)
                {
                    snake.setDirection(Direction.LEFT);
                }
            }
        });



        stage.setTitle("SnakeGame");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }
}