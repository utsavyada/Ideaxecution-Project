import java.util.Scanner;

public class LostScreenPage {
    private static final int MAX_COINS = 21;
    private static final String AI_NAME = "AI";
    private static final String HUMAN_NAME = "Human";

    private int numCoins = MAX_COINS;
    private boolean isPlayerTurn = true;
    private String winner;

    private int aiMove() {
        int coinsToTake = (numCoins - 1) % 5;
        if (coinsToTake == 0) {
            coinsToTake = (int) (Math.random() * 4) + 1;
        }
        return coinsToTake;
    }

    private void playerMove() {
        Scanner scanner = new Scanner(System.in);
        int coinsToTake = 0;
        do {
            System.out.printf("%s, how many coins do you want to take? (1-4): ", HUMAN_NAME);
            coinsToTake = scanner.nextInt();
        } while (coinsToTake < 1 || coinsToTake > 4 || coinsToTake > numCoins);
        numCoins -= coinsToTake;
        isPlayerTurn = false;
        if (numCoins == 0) {
            winner = AI_NAME;
        }
    }

    private void aiTurn() {
        int coinsToTake = aiMove();
        System.out.printf("%s takes %d coins.\n", AI_NAME, coinsToTake);
        numCoins -= coinsToTake;
        isPlayerTurn = true;
        if (numCoins == 0) {
            winner = HUMAN_NAME;
        }
    }

    private void playGame() {
        while (numCoins > 0) {
            if (isPlayerTurn) {
                playerMove();
            } else {
                aiTurn();
            }
            System.out.printf("%d coins remaining.\n", numCoins);
        }
    }

    private void printGameResult() {
        System.out.printf("%s wins!\n", winner);
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the Coin Game!");
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;
        int numGamesPlayed = 0;
        int numGamesWonByAI = 0;
        while (playAgain) {
            CoinGame game = new CoinGame();
            game.playGame();
            game.printGameResult();
            numGamesPlayed++;
            if (game.winner.equals(AI_NAME)) {
                numGamesWonByAI++;
            }
            System.out.printf("Games played: %d\n", numGamesPlayed);
            System.out.printf("Games won by %s: %d\n", AI_NAME, numGamesWonByAI);
            System.out.printf("Games won by %s: %d\n", HUMAN_NAME, numGamesPlayed - numGamesWonByAI);
            System.out.print("Play again? (y/n): ");
            playAgain = scanner.nextLine().equalsIgnoreCase("y");
        }
        System.out.println("Thanks for playing!");
    }
}
