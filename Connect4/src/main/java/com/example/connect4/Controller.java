package com.example.connect4;

import Algorithm.*;
import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Controller {
    private static final int TILE_SIZE = 80;
    private static final int COLUMNS = 7;
    private static final int ROWS = 6;
    public int k;
    public boolean withPruning;
    private boolean redMove = true;
    private Disc[][] grid = new Disc[COLUMNS][ROWS];
    private char[][] logicGrid = new char[ROWS][COLUMNS] ;

    private Pane discRoot = new Pane();

    Parent createContent() {

        Pane root = new Pane();
        root.getChildren().add(discRoot);
        for (char[] row : logicGrid)
            Arrays.fill(row, '0');
        Shape gridShape = makeGrid();
        root.getChildren().add(gridShape);
        root.getChildren().addAll(makeColumns());
        return root;
    }

    private Shape makeGrid() {
        Shape shape = new Rectangle((COLUMNS + 1) * TILE_SIZE, (ROWS + 1) * TILE_SIZE);

        for (int y = 0; y < ROWS; y++) {
            for (int x = 0; x < COLUMNS; x++) {
                Circle circle = new Circle(TILE_SIZE / 2);
                circle.setCenterX(TILE_SIZE / 2);
                circle.setCenterY(TILE_SIZE / 2);
                circle.setTranslateX(x * (TILE_SIZE + 5) + TILE_SIZE / 4);
                circle.setTranslateY(y * (TILE_SIZE + 5) + TILE_SIZE / 4);

                shape = Shape.subtract(shape, circle);
            }
        }

        Light.Distant light = new Light.Distant();
        light.setAzimuth(45.0);
        light.setElevation(30.0);

        Lighting lighting = new Lighting();
        lighting.setLight(light);
        lighting.setSurfaceScale(5.0);

        shape.setFill(Color.BLUE);
        shape.setEffect(lighting);

        return shape;
    }

    private List<Rectangle> makeColumns() {
        List<Rectangle> list = new ArrayList<>();

        for (int x = 0; x < COLUMNS; x++) {
            Rectangle rect = new Rectangle(TILE_SIZE, (ROWS + 1) * TILE_SIZE);
            rect.setTranslateX(x * (TILE_SIZE + 5) + TILE_SIZE / 4);
            rect.setFill(Color.TRANSPARENT);

            rect.setOnMouseEntered(e -> rect.setFill(Color.rgb(200, 200, 50, 0.3)));
            rect.setOnMouseExited(e -> rect.setFill(Color.TRANSPARENT));

            final int column = x;
            rect.setOnMouseClicked(e -> {placeDisc(new Disc(redMove), column);

            if (gameOver()) {
                Grid g = new Grid();

                int p1Score = g.countScore(logicGrid, '1');
                int p2Score = g.countScore(logicGrid, '2');
                System.out.println("Game is Over");
                String winner;
                if (p1Score > p2Score) {
                    winner = "The winner is Human";
                } else {
                    winner = "The winner is Agent";
                }
                System.out.println("Human Score is : " + p1Score);
                System.out.println("Agent Score is : " + p2Score);
                Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
                errorAlert.setHeaderText("Game has ended");
                errorAlert.setContentText(winner + "\nHuman Score is " + p1Score + " & Agent Score is " + p2Score);
                errorAlert.showAndWait();
            }

            });

            list.add(rect);
        }

        return list;
    }

    private void placeDisc(Disc disc, int column) {
        int row = ROWS - 1;
        do {
            if (!getDisc(column, row).isPresent())
                break;
            row--;
        } while (row >= 0);

        if (row < 0)
            return;

        grid[column][row] = disc;
        if (disc.red) {
            logicGrid[row][column] = '1';
        }

        discRoot.getChildren().add(disc);
        disc.setTranslateX(column * (TILE_SIZE + 5) + TILE_SIZE / 4);

        final int currentRow = row;

        TranslateTransition animation = new TranslateTransition(Duration.seconds(0.5), disc);
        animation.setToY(row * (TILE_SIZE + 5) + TILE_SIZE / 4);
        animation.setOnFinished(e -> {
            redMove = !redMove;
            if (!redMove) {
                State state=new State(logicGrid);
                Grid g = new Grid();
                State temp;
                if (withPruning) {
                    MiniMax m =new MiniMax();
                    temp=m.maximize(state,k,'2');
                } else {
                    MiniMaxWithPruning m =new MiniMaxWithPruning();
                    temp=m.maximize_with_pruning(state,k,'2', Integer.MIN_VALUE, Integer.MAX_VALUE);

                }
                int col=temp.getCol();
                g.play(logicGrid,col,'2');
                this.placeDisc(new Disc(false), col);
//                System.out.println();
//                for (int i=0;i<6;++i){
//                    for (int j=0;j<7;++j)
//                        System.out.print(logicGrid[i][j]+" ");
//                    System.out.println();
//                }
            }
        });
        Grid g = new Grid();
        int p1Score = g.countScore(logicGrid, '1');
        int p2Score = g.countScore(logicGrid, '2');
        System.out.println("Human Score is : " + p1Score);
        System.out.println("Agent Score is : " + p2Score);
        System.out.println("======================================================");
        animation.play();
    }



    private boolean gameOver() {
        Grid g = new Grid();
        if(g.get_valid_locations(logicGrid).size() <= 0) {
            return true;
        }
        return false;
    }

    private Optional<Disc> getDisc(int column, int row) {
        if (column < 0 || column >= COLUMNS
                || row < 0 || row >= ROWS)
            return Optional.empty();

        return Optional.ofNullable(grid[column][row]);
    }

    private static class Disc extends Circle {
        private final boolean red;
        public Disc(boolean red) {
            super(TILE_SIZE / 2, red ? Color.RED : Color.YELLOW);
            this.red = red;

            setCenterX(TILE_SIZE / 2);
            setCenterY(TILE_SIZE / 2);
        }
    }
}