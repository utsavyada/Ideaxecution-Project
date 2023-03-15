import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PlayScreenPage implements ActionListener {
    private JFrame frame;
    private JLabel infoLabel;
    private JButton[] coinButtons;
    private int coinsLeft;
    private boolean playerTurn;

    public CoinGame() {
        
        coinsLeft = 21;
        playerTurn = true;

        
        frame = new JFrame("Coin Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        infoLabel = new JLabel("Coins left: " + coinsLeft);
        frame.add(infoLabel);

        coinButtons = new JButton[4];
        for (int i = 0; i < 4; i++) {
            coinButtons[i] = new JButton(String.valueOf(i + 1));
            coinButtons[i].addActionListener(this);
            frame.add(coinButtons[i]);
        }

        
        frame.pack();
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        int coinsTaken = Integer.parseInt(e.getActionCommand());

        
        coinsLeft -= coinsTaken;
        playerTurn = !playerTurn;

       
        infoLabel.setText("Coins left: " + coinsLeft);
        for (int i = 0; i < 4; i++) {
            coinButtons[i].setEnabled(i + 1 <= coinsLeft && (playerTurn || i + 1 != coinsLeft));
        }

        
        if (coinsLeft == 0) {
            String winner = playerTurn ? "Player" : "AI";
            JOptionPane.showMessageDialog(frame, winner + " wins!");
            System.exit(0);
        }

        
        if (!playerTurn) {
            int coinsToTake = (coinsLeft - 1) % 5;
            if (coinsToTake == 0) coinsToTake = 1;
            coinsLeft -= coinsToTake;
            playerTurn = !playerTurn;

            
            infoLabel.setText("Coins left: " + coinsLeft);
            for (int i = 0; i < 4; i++) {
                coinButtons[i].setEnabled(i + 1 <= coinsLeft && (playerTurn || i + 1 != coinsLeft));
            }

            
            if (coinsLeft == 0) {
                String winner = playerTurn ? "Player" : "AI";
                JOptionPane.showMessageDialog(frame, winner + " wins!");
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        new CoinGame();
    }
}
