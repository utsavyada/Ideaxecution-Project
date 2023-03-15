import java.util.Scanner;

public class LoginPage{
    public static void main(String[] args) {
       
        Scanner input = new Scanner(System.in);
        System.out.print("Username: ");
        String username = input.nextLine();
        System.out.print("Password: ");
        String password = input.nextLine();
        if (username.equals("admin") && password.equals("admin1234")) {
            System.out.println("Login successful. Starting game...");
            playGame();
        } else if (username.equals("guest") && password.equals("guest1234")) {
            System.out.println("Login successful. Starting game...");
            playGame();
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    public static void playGame() {
        int coins = 21;
        int playerChoice, aiChoice;
        boolean playerTurn = true;

        while (coins > 0) {
            if (playerTurn) {
            
                System.out.print("Enter your choice (1-4): ");
                playerChoice = Integer.parseInt(input.nextLine());
                while (playerChoice < 1 || playerChoice > 4 || playerChoice > coins) {
                    System.out.print("Invalid choice. Enter again: ");
                    playerChoice = Integer.parseInt(input.nextLine());
                }
                coins -= playerChoice;
                System.out.println("Coins left: " + coins);
                playerTurn = false;
            } else {
                
                aiChoice = coins % 5; 
                if (aiChoice == 0) {
                    aiChoice = 1;
                }
                coins -= aiChoice;
                System.out.println("AI picks " + aiChoice + " coins. Coins left: " + coins);
                playerTurn = true;
            }
        }

        if (playerTurn) {
            System.out.println("You lose! AI wins");
        } else {
            System.out.println("AI loses!");
        }
    }
}
