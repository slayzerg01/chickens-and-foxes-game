package Game;

import Game.objects.Chiсkens;
import Game.objects.Foxes;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Game extends Application {
    private static final String Game = "GAME";
    private final int size = 7;
    private final Logic logic = new Logic();
    private int count = 20;

    public static void main(String[] args) {
        Application.launch(args);
    }

    private Rectangle buildRectangle(int x, int y, int size) {
        Rectangle rect = new Rectangle();
        rect.setX(x * size);
        rect.setY(y * size);
        rect.setHeight(size);
        rect.setWidth(size);
        rect.setFill(Color.WHITE);
        rect.setStroke(Color.BLACK);
        return rect;
    }

    private Group buildGrid() {
        Group panel = new Group();
        for (int y = 0; y != this.size; y++) {
            for (int x = 0; x != this.size; x++) {
                if((x==2)||(x==3)||(x==4))
                panel.getChildren().add(this.buildRectangle(x, y, 50));
                if((y==2)||(y==3)||(y==4))
                panel.getChildren().add(this.buildRectangle(x, y, 50));
            }
        }
        return panel;
    }

    @Override
    public void start(Stage stage) {
        BorderPane border = new BorderPane();
        HBox control = new HBox();
        control.setPrefHeight(55F);
        control.setSpacing(10F);
        control.setAlignment(Pos.BASELINE_CENTER);
        HBox center = new HBox();
        center.setPadding(new Insets(10, 10, 10, 10));
        center.setSpacing(10.0);
        center.setAlignment(Pos.BASELINE_CENTER);
        border.setCenter(center);
        border.setBottom(control);
        Group board = this.buildGrid();
        center.getChildren().add(board);
        border.setBottom(control);
        stage.setScene(new Scene(border, 370, 410));
        stage.setTitle(Game);
        stage.setResizable(false);
        stage.show();
        logic.add(new Chiсkens(Cell.D1));
        logic.add(new Chiсkens(Cell.D2));
        logic.add(new Chiсkens(Cell.D3));
        logic.add(new Chiсkens(Cell.D4));
        logic.add(new Chiсkens(Cell.D5));
        logic.add(new Chiсkens(Cell.D6));
        logic.add(new Chiсkens(Cell.D7));
        logic.add(new Chiсkens(Cell.E1));
        logic.add(new Chiсkens(Cell.E2));
        logic.add(new Chiсkens(Cell.E3));
        logic.add(new Chiсkens(Cell.E4));
        logic.add(new Chiсkens(Cell.E5));
        logic.add(new Chiсkens(Cell.E6));
        logic.add(new Chiсkens(Cell.E7));
        logic.add(new Chiсkens(Cell.F3));
        logic.add(new Chiсkens(Cell.F4));
        logic.add(new Chiсkens(Cell.F5));
        logic.add(new Chiсkens(Cell.G3));
        logic.add(new Chiсkens(Cell.G4));
        logic.add(new Chiсkens(Cell.G5));
        logic.add(new Foxes(Cell.C3));
        logic.add(new Foxes(Cell.C5));

        board.getChildren().addAll(darwObj(board,border));

    }

    private Group darwObj(Group board, BorderPane border){
        Group panel = new Group();
        for(int index=0; index<count; index++){
            Rectangle rect = new Rectangle();
            rect.setX(logic.getChicken(index).position().getX()*50+2);
            rect.setY(logic.getChicken(index).position().getY()*50+2);
            rect.setHeight(46);
            rect.setWidth(46);
            rect.setArcWidth (20);
            rect.setArcHeight (20);
            rect.setFill(Color.GREEN);
            Rectangle rectwhite = new Rectangle();
            rectwhite.setX(0);
            rectwhite.setY(0);
            rectwhite.setHeight(50);
            rectwhite.setWidth(50);
            rectwhite.setFill(Color.WHITE);
            panel.getChildren().add(rectwhite);
            final Rectangle momento = new Rectangle(logic.getChicken(index).position().getX()*50, logic.getChicken(index).position().getY()*50);
            rect.setOnDragDetected(
                    event -> {
                        momento.setX(event.getX());
                        momento.setY(event.getY());
                    }
            );
            rect.setOnMouseDragged(
                    event -> {
                        rect.setX(event.getX());
                        rect.setY(event.getY());
                    }
            );
            rect.setOnMouseReleased(
                    event -> {
                        if (logic.move(this.findBy(momento.getX(), momento.getY()), this.findBy(event.getX(), event.getY()))) {
                            logic.eatFox(true);
                            board.getChildren().remove(42);
                            board.getChildren().addAll(darwObj(board,border));
                            if (logic.winFox()){
                                Label Text = new Label("You lose");
                                Text.setFont(new Font(40));
                                border.getChildren().remove(0,2);
                                border.setCenter(Text);
                            }
                            if (logic.winCh()){
                                Label Text = new Label("You win");
                                Text.setFont(new Font(40));
                                border.getChildren().remove(0,2);
                                border.setCenter(Text);
                            }
                        } else {
                            rect.setX(((int) momento.getX() / 50) * 50+2);
                            rect.setY(((int) momento.getY() / 50) * 50+2);
                        }
                    }
            );
            rect.setOnMouseClicked(
                    event -> {
                        if (event.getButton() != MouseButton.PRIMARY) {
                            rect.setWidth(momento.getHeight());
                            rect.setHeight(momento.getWidth());
                        }
                    }
            );
            panel.getChildren().add(rect);
        }
        for(int index=0; index<2; index++){
            Rectangle rect = new Rectangle();
            rect.setX(logic.getFox(index).position().getX()*50+2);
            rect.setY(logic.getFox(index).position().getY()*50+2);
            rect.setHeight(46);
            rect.setWidth(46);
            rect.setArcWidth (20);
            rect.setArcHeight (20);
            rect.setFill(Color.ORANGE);
            panel.getChildren().add(rect);
        }
        return panel;
    }

   private Cell findBy(double graphX, double graphY) {
        Cell rst = Cell.A1;
        int x = (int) graphX / 50;
        int y = (int) graphY / 50;
        for (Cell cell : Cell.values()) {
            if (cell.getX() == x && cell.getY() == y) {
                rst = cell;
                break;
            }
        }
        return rst;
    }
}
