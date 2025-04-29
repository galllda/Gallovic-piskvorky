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
        drawField(field);

        playGame(turnCounter, field, player, scanner, playersMark);

    }

    public static void playGame(int turnCounter, char[] field, int player, Scanner scanner, char[] playersMark) {
        while (turnCounter <= field.length) {
            System.out.println("Hraje hrac číslo: " + player + " (vol pole 1-9)");
            try {
                int turn = scanner.nextInt();
                if (turn >= 1 && turn <= field.length) {
                    if (field[turn - 1] == '_') {
                        field[turn - 1] = playersMark[player - 1];
                        drawField(field);
                        if (checkResult(field, playersMark[player - 1])){
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
            if (turnCounter ==9) {
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

    public static boolean checkResult(char[] field, char player) {
        char[][] fieldToCheck=new char[3][3];
        int d=0;
        while (d<field.length) {
            for (int k = 0; k <= 2; k++) {
                for (int j = 0; j <= 2; j++) {
                    fieldToCheck[k][j] = field[d];
                    d++;
                }

            }
        }
        for (int k = 0; k < fieldToCheck.length; k++) {
            if (fieldToCheck[k][0]==player && fieldToCheck[k][1]==player && fieldToCheck[k][2]==player){
                return true;
            }
            if (fieldToCheck[0][k]==player && fieldToCheck[1][k]==player && fieldToCheck[2][k]==player){
                return true;
            }
            if (fieldToCheck[0][0]==player && fieldToCheck[1][1]==player && fieldToCheck[2][2]==player){
                return true;
            }
            if (fieldToCheck[0][2]==player && fieldToCheck[1][1]==player && fieldToCheck[2][0]==player){
                return true;
            }
        }
        return false;
    }

}



