import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Ahoj, vitám tě ve hře piškvorky");

        Scanner scanner = new Scanner(System.in);

        int turnCounter = 0;
        char[] field = new char[] {'_', '_', '_', '_', '_', '_', '_', '_', '_'};
        char[] playersMark = {'X', 'O'};
        int player = 1;
        int[][] winnersVariant = {{0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}, {0,1,2},{3,4,5},{6,7,8}};
        drawField(field);

        while (turnCounter <= field.length) {
            System.out.println("Hraje hrac číslo: " + player + " (vol pole 1-9)");
            try {
                int turn = scanner.nextInt();
                if (turn >= 1 && turn <= 9) {
                    if (field[turn - 1] == '_') {
                        field[turn - 1] = playersMark[player - 1];
                        drawField(field);
                        if (checkResult(field, winnersVariant, playersMark[player - 1])){
                            System.out.println("Vyhral hrac číslo:" + player + ". KONEC HRY!");
                            break;
                        }

                        turnCounter++;

                        if (player == 1) {
                            player = 2;
                        } else {
                            player = 1;
                        }


                    } else {
                        System.out.println("Musíš volit neobsazené pole!");
                        scanner.nextLine();
                    }
                } else {
                    System.out.println("Musíš volit mezi čísly 1-9!");
                    scanner.nextLine();
                }
            } catch (InputMismatchException e) {
                System.out.println("Špatná volba, zkus znova!");
                scanner.nextLine();
            }
            if (turnCounter==9) {
                System.out.println("Nikdo nevyhral, konec hry.");
                break;
            }
        }

    }

    public static void drawField(char[] field) {

        for (int i = 1; i <= (field.length); i++) {
            System.out.print(field[i - 1] + " | ");
            if (i % 3 == 0) {
                System.out.println();
            }
        }
    }

    public static boolean checkResult(char[] field, int[][] winn, char player) {
    int checkWinner=0;
        for (int k = 0; k <= 7; k++) {
            for (int j = 0; j <= 2; j++) {
                if (field[winn[k][j]] == player) {
                    checkWinner++;
                }
            }
            if (checkWinner==3){
                return true;
            }else {
                checkWinner=0;
            }
        }
        return false;
    }

}



