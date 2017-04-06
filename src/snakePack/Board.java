package snakePack;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;

public class Board
{
  public static int ROWSY;
  public static int COLSX;
  public static final int TILE_SIDE = 20;
  public static final int PADDING_LEFT = 10;
  public static final int PADDING_TOP = 10;

  private Tile[][] tiles;
  private Apple apple;
  
    public Board(int rowsy, int colsx, int appleX, int appleY){
        ROWSY = rowsy;
        COLSX = colsx;
        tiles = new Tile[COLSX][ROWSY];
        apple = new Apple(appleX, appleY);
    }
  
    /**
     * creating tiles
     **/
    public void init(){
        for (int i = 0; i < COLSX; i++) {
            for (int j = 0; j < ROWSY; j++) {
                tiles[i][j] = 
                    new Tile(TILE_SIDE * i + PADDING_LEFT, 
                            TILE_SIDE * j + PADDING_TOP, 
                            TILE_SIDE, TILE_SIDE, 
                            Field.EMPTY);
            }
        }
    }
  
    public void clear() {
        for (int i = 0; i < COLSX; i++) {
            for (int j = 0; j < ROWSY; j++) {
                tiles[i][j].setField(Field.EMPTY);
            }
        }
    }
  
    public void putApple(int x, int y){
        tiles[x][y].setField(Field.APPLE);
        apple.setXY(x, y);
    }
  
    public void setAppleAtRandom() {
        boolean appleSet = false;

        Random r = new Random();
        int x = 0, y = 0;
        while (!appleSet) {
            x = r.nextInt(COLSX);
            y = r.nextInt(ROWSY);
            if (tiles[x][y].getField() == Field.EMPTY)
                appleSet = true;
        }
        putApple(x, y);
    }
  
    public boolean justAteApple(Snake snake){
        int snakeX = ((SnakeField)snake.getSnakeList().getFirst()).getX();
        int snakeY = ((SnakeField)snake.getSnakeList().getFirst()).getY();

        if ((snakeX == apple.getX()) && (snakeY == apple.getY()))
            return true;
        return false;
    }
  
    public void pasteSnake(Snake snake) {
        LinkedList<SnakeField> snakeList = snake.getSnakeList();

        for (SnakeField snakeField : snakeList) {
            //System.out.println(snakeField.getX()+","+snakeField.getY());
            tiles[snakeField.getX()][snakeField.getY()].setField(Field.SNAKE);
        }
        //  System.out.println("pasted");
    }
 
    public void eraseSnake(){
        for (int i = 0; i < COLSX; i++) {
            for (int j = 0; j < ROWSY; j++) {
                if (tiles[i][j].getField() == Field.SNAKE)
                    tiles[i][j].setField(Field.EMPTY);
            }
        }
    }
  
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;

        for (int i = 0; i < COLSX; i++) {
            for (int j = 0; j < ROWSY; j++) {
                Rectangle rect = tiles[i][j];

                switch (tiles[i][j].getField()) {
                    case SNAKE: 
                        g2.setColor(Color.green);
                        break;
                    case APPLE: 
                        g2.setColor(Color.red);
                        break;
                    case EMPTY: default: 
                        g2.setColor(Color.gray);
                }
                g2.fill(rect);
                g2.draw(rect);
            }
        }
    }
}
