public class Logic {
    public static void startGame() {

        // Getting the size of the field from the user.
        int n = Utility.getN();
        int m = Utility.getM(n);


        // Creating a new array of strings with the size of n*m and filling it with spaces.
        String[] array = new String[n * m];
        Utility.fillArrayWithSpaces(array);




        // A loop that prints the field, creates a 2 in a random place, and gets the instruction.
        while (true) {
            Utility.printField(n, m, array);
            Utility.create2InRandomPlace(n, m, array);
            Utility.getInstruction(n, m, array);
        }


    }
}
