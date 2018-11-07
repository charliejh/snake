package snake;

public class Main {

    public static void main(String[] args) {
        Game game = new Game(
                Constants.FRAME_SIZE * Constants.BLOCK_SIZE + 300,
                Constants.FRAME_SIZE * Constants.BLOCK_SIZE + 22,
                new Gameplay());
        game.startGame();
    }

}
