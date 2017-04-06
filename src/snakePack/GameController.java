package snakePack;

import java.awt.Graphics;

public class GameController implements Runnable{
    private static final int UPDATE_RATE = 30;
    private int startAppleX = 3;
    private int startAppleY = 7;
    private Board board;
    private Snake snake;
    private GameState gameState;
    private MyPanel myPanel;
    private ConnectionManager connectionManager;
    
    public GameController(MyPanel myPanel){
        this.myPanel = myPanel;
        
        initObjects();
        initGame();
        
        gameState = gameState.PLAYING;
        
        connectionManager = new ConnectionManager();
    }
    
    private void initObjects(){
        board = new Board(startAppleX, startAppleY);
        board.init();
        snake = new Snake();
        
    }
    
    private void initGame(){        
        snake.init(10, 8, Direction.UP);
        board.clear();
        board.putApple(startAppleX, startAppleY);
        board.pasteSnake(snake);
    }
    
    public void makeLeftTurn(){
        connectionManager.sendDataToServer("w lewo!");
        snake.turnLeft();
        board.eraseSnake();
        board.pasteSnake(snake);
        
    }
    
    public void makeRightTurn(){
        connectionManager.sendDataToServer("w prawo!");

        snake.turnRight();
        board.eraseSnake();
        board.pasteSnake(snake);
        
    }
    
    public void drawBoard(Graphics g){//TODO very not pretty
        board.draw(g);

    }
    
    @Override
    public void run() {
        while (gameState == GameState.PLAYING){
            SnakeField erasedField = snake.moveForward();

            if (snake.iAteMe()){
                gameState = GameState.LOST;
                connectionManager.closeConnection();
            }

            if (board.justAteApple(snake)){
                board.setAppleAtRandom();

                snake.lengthen(erasedField);
            }

            board.eraseSnake();
            board.pasteSnake(snake);

            myPanel.doRepaint();
            try{
                Thread.sleep(170L);
            }catch (InterruptedException localInterruptedException) {}
        }
      }
}
