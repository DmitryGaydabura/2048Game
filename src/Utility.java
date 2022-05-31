import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Utility {

    /**
     * Get the length of the field from the user, and if it's less than 3, ask again.
     *
     * @return The method returns the length of the field.
     */
    public static int getN() {
        System.out.println("Enter field's length:");
        while (true) {
            Scanner in = new Scanner(System.in);
            int n = in.nextInt();
            if (n < 3) {
                System.out.println("Incorrect input. Length could not be less than 3.");
                continue;
            }
            return n;
        }
    }

    /**
     * Get the width of the field from the user, and make sure it's at least 3.
     *
     * @param n the height of the field
     * @return The method returns the width of the field.
     */
    public static int getM(int n) {
        System.out.println("Enter field's width:");
        while (true) {
            Scanner in = new Scanner(System.in);
            int m = in.nextInt();

            if (m < 3) {
                System.out.println("Incorrect input. Width could not be less than 3.");
                continue;
            }
            System.out.println("The size of your field is " + n + "x" + m);
            return m;
        }
    }

    /**
     * It prints a field of n rows and m columns, where each cell contains a string from the array
     *
     * @param n number of rows
     * @param m number of columns
     * @param array the array of strings to be printed
     */
    public static void printField(int n, int m, String[] array) {
        int k = 0;

        for (int i = 0; i < m; i++) {
            System.out.print("+------");
            if ((m - i) == 1) {
                System.out.print("+");
            }
        }
        System.out.println();
        String separation = "";
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (array[k].length() == 1) {
                    separation = "    ";
                } else if (array[k].length() == 2) {
                    separation = "   ";
                } else if (array[k].length() == 3) {
                    separation = "  ";
                } else if (array[k].length() == 4) {
                    separation = " ";
                }
                System.out.print("| " + array[k] + separation);
                k++;
                if ((m - j) == 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            for (int j = 0; j < m; j++) {
                System.out.print("+------");
                if ((m - j) == 1) {
                    System.out.print("+");
                }
            }
            System.out.println();

        }
    }

    /**
     * This function takes an array of strings and fills it with spaces.
     *
     * @param array The array to fill with spaces.
     */
    public static void fillArrayWithSpaces(String[] array) {
        Arrays.fill(array, "0");
    }

    /**
     * It creates a 2 in a random place in the array
     *
     * @param n the number of rows
     * @param m the number of rows
     * @param array the array of the game
     */
    public static void create2InRandomPlace(int n, int m, String[] array) {
        boolean ready = false;
        for (int i = 0; i < n * m; i++) {
            if (array[i].equalsIgnoreCase("0")) {
                array[i] = "2";
                ready = true;
                break;
            }
        }
        if (!ready) {
            System.out.println("You lost!");
            Utility.exit();
        }
    }

    /**
     * Exit() is a function that takes no arguments and returns nothing, and it exits the program.
     */
    private static void exit() {
        System.exit(0);
    }

    /**
     * This function takes in the size of the board, the board itself, and the user's input. It then calls the appropriate
     * function based on the user's input
     *
     * @param n the size of the array
     * @param m the number of columns.
     * @param array the array of strings that represents the game board
     */
    public static void getInstruction(int n, int m, String[] array) {
        System.out.println("Enter your move( w, a, s, d )");
        Scanner in = new Scanner(System.in);
        String instruction = in.nextLine();

        switch (instruction) {
            case "w" -> makeWMove(n, m, array);
            case "a" -> makeAMove(n, m, array);
            case "s" -> makeSMove(n, m, array);
            case "d" -> makeDMove(n, m, array);
        }


    }

    private static void makeWMove(int n, int m, String[] array) {

        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n - 1; i++) {
                int i1 = (m * (n - 1)) - m * i + j;
                int i2 = (m * (n - 1)) - m * (i + 1) + j;
                if (Integer.parseInt(array[i1]) == Integer.parseInt(array[i2])) {
                    array[i2] = String.valueOf(Integer.parseInt(array[i2]) * 2);
                    array[i1] = "0";
                }

                if (Objects.equals(array[i2], "0")) {
                    array[i2] = array[i1];
                    array[i1] = "0";
                }

            }
        }

    }

    private static void makeDMove(int n, int m, String[] array) {
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m - 1; i++) {

                if (Integer.parseInt(array[j * m + i]) == Integer.parseInt(array[j * m + (i + 1)])) {
                    array[j * m + (i + 1)] = String.valueOf(Integer.parseInt(array[j * m + (i + 1)]) * 2);
                    array[j * m + i] = "0";
                }

                if (Objects.equals(array[j * m + (i + 1)], "0")) {
                    array[j * m + (i + 1)] = array[j * m + i];
                    array[j * m + i] = "0";
                }
            }
        }
    }

    private static void makeSMove(int n, int m, String[] array) {
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n - 1; i++) {


                int i1 = m * i + j;

                int i2 = m * i + m + j;
                if (Integer.parseInt(array[i1]) == Integer.parseInt(array[i2])) {
                    array[i2] = String.valueOf(Integer.parseInt(array[i2]) * 2);
                    array[i1] = "0";
                }
                if (Objects.equals(array[i2], "0")) {
                    array[i2] = array[i1];
                    array[i1] = "0";
                }
            }
        }


    }

    private static void makeAMove(int n, int m, String[] array) {
        for (int j = 0; j < n; j++) {
            for (int i = 1; i < m; i++) {
                int i1 = m * n - 1 - j * m;
                int i2 = m * n - 1 - i - j * m;
                if (Objects.equals(array[i1], array[i2])) {
                    array[i2] = String.valueOf(Integer.parseInt(array[i2]) * 2);
                    array[i1] = "0";
                }

                if (Objects.equals(array[i2], "0")) {
                    array[i2] = array[i1];
                    array[i1] = "0";
                }

            }
        }

    }

    // It prints the logo of the game.
    public static void printLogo() {
        System.out.println("""

                  ____   ___  _  _    ___   \s
                 |___ \\ / _ \\| || |  ( _ )  \s
                   __) | | | | || |_ / _ \\  \s
                  / __/| |_| |__   _| (_) | \s
                 |_____|\\___/   |_|  \\___/  \s
                  / ___| __ _ _ __ ___   ___\s
                 | |  _ / _` | '_ ` _ \\ / _ \\
                 | |_| | (_| | | | | | |  __/
                  \\____|\\__,_|_| |_| |_|\\___|
                                            \s
                """);
    }
}
