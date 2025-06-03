import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Sudoko {

    public static class Tile extends JButton {
        int r, c;

        Tile(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    int boardWidth = 600;
    int boardHeight = 650;

    String[] puzzle = {
            "530070000",
            "600195000",
            "098000060",
            "800060003",
            "400803001",
            "700020006",
            "060000280",
            "000419005",
            "000080079"
    };

    String[] solution = {
            "534678912",
            "672195348",
            "198342567",
            "859761423",
            "426853791",
            "713924856",
            "961537284",
            "287419635",
            "345286179"
    };

    JFrame frame = new JFrame();
    JLabel textLabel = new JLabel();
    JPanel boardPanel = new JPanel();
    JPanel buttonPanel =new JPanel();

    JButton numSelected = null;
    int errors=0;

    public Sudoko() {
        frame.setTitle("Sudoku");
        frame.setSize(boardWidth, boardHeight);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        textLabel.setFont(new Font("Arial", Font.BOLD, 30));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Sudoku :0");

        frame.add(textLabel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(9, 9));
        setupTiles();
        frame.add(boardPanel, BorderLayout.CENTER);

        buttonPanel.setLayout(new GridLayout(1,9));
        setupButton();
        frame.add(buttonPanel,BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    void setupTiles() {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                Tile tile = new Tile(r, c);
                char ch = puzzle[r].charAt(c);

                if (ch != '0') {
                    tile.setFont(new Font("Arial", Font.BOLD, 20));
                    tile.setText(String.valueOf(ch));
                    tile.setEnabled(false);
//                    tile.setBackground(Color.LIGHT_GRAY);
                    tile.setBackground(	new Color(245, 222, 179));
                }else{
                    tile.setFont(new Font("Arial", Font.PLAIN, 20));
                    tile.setBackground(Color.WHITE);
                }
                int top = (r % 3 == 0) ? 2: 1;
                int left = (c % 3 == 0) ? 2 : 1;
                int bottom = (r == 8) ? 2 : 1;
                int right = (c == 8) ? 2 : 1;
                tile.setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.BLACK));

                tile.setFocusable(false);
                boardPanel.add(tile);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Tile tile=(Tile) e.getSource();

                        int r= tile.r;
                        int c= tile.c;
                        if (numSelected !=null){
                            if (tile.getText() != ""){
                                return;
                            }
                            String numSelectedText= numSelected.getText();
                            String tilesolution =String.valueOf(solution[r].charAt(c));

                            if (tilesolution.equals(numSelectedText)){
                                tile.setText(numSelectedText);
                            }else{
                                errors += 1;
                                textLabel.setText("Sudoku:" +String.valueOf(errors));
                            }
                        }
                    }
                });
            }
        }
    }

    void setupButton(){
        for (int i=0;i<10;i++){
            JButton button=new JButton();
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.setText(String.valueOf(i));
            button.setBackground(	new Color(245, 245, 220));
            button.setFocusable(false);
            buttonPanel.add(button);

            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JButton button=(JButton ) e.getSource();
                    if(numSelected !=null){
                        numSelected.setBackground(new Color(245, 245, 220));
                    }
                        numSelected = button;
                        numSelected.setBackground(Color.LIGHT_GRAY);
                }
            });
        }
    }

    public static void main(String[] args) {
        Sudoko sudoko =new Sudoko();

    }
}
