import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.sql.SQLOutput;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        InitiatField();
        PrintField();
        while (true) {
            PlayerMove();
            PrintField();
            if (Winner(PlayerToken)) {
                System.out.println("Вы победили!");
                break;
            }
            if (FullField()) {
                System.out.println("Ничья");
                break;
            }
            AImove();
            PrintField();
            if (Winner(AIToken)) {
                System.out.println("Вы проиграли :С");
                break;
            }
            if (FullField()) {
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Конец игры");
    }

    static char[][] GameField;
    static final char FieldEmpty = '_';
    static final char PlayerToken = 'X';
    static final char AIToken = 'O';
    static final int SizeField = 3;

    static void InitiatField() {
        GameField = new char[SizeField][SizeField];
        for (int i = 0; i < SizeField; i++) {
            for (int j = 0; j < SizeField; j++) {
                GameField[i][j] = FieldEmpty;
            }
        }
    }

    static void PrintField() {
        for (int i = 0; i < SizeField; i++) {
            for (int j = 0; j < SizeField; j++) {
                System.out.print(GameField[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static Scanner scanner = new Scanner(System.in);

    static void PlayerMove() {
        int line, column;
        do {
            System.out.println("Введите номер строки");
            line = scanner.nextInt() - 1;
            System.out.println("Введите номер стобца");
            column = scanner.nextInt() - 1;
        } while (!Examination(line, column));
        GameField[line][column] = PlayerToken;
    }

    static boolean Examination(int line, int column) {
        if (line < 0 || line >= SizeField || column < 0 || column >= SizeField) return false;
        if (GameField[line][column] == FieldEmpty) return true;
        return false;
    }

    static Random random = new Random();

    static void AImove() {
        int line, column;
        do {
            line = random.nextInt(SizeField);
            column = random.nextInt(SizeField);
        } while (!Examination(line, column));
        GameField[line][column] = AIToken;
    }

    public static boolean FullField() {
        for (int i = 0; i < SizeField; i++) {
            for (int j = 0; j < SizeField; j++) {
                if (GameField[i][j] == FieldEmpty) return false;
            }
        }
        return true;
    }

    public static boolean Winner(char token) {
        for (int i = 0; i < GameField.length; i++) {
            if (GameField[i][SizeField -1] == token) {
                return true;
            }
        }
        for (int i = 0; i < GameField.length; i++) {
            if (GameField[SizeField - 1][i] == token){
                return true;
            }
        }
        if (GameField[0][0] == token && GameField[1][1] == token && GameField[2][2] == token) {
            return true;
        }
        if (GameField[0][2] == token && GameField[1][1] == token && GameField[2][0] == token){
            return true;
        }
        return false;
    }
}