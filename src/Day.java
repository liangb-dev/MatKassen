import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

public class Day {
    private int ID;
    private int WEEK;
    private String TITLE;
    private String TIME;
    private ArrayList<String> INGREDIENTS = new ArrayList<String>();
    private ArrayList<String> STEPS = new ArrayList<String>();

    private ArrayList<String> FUNFACT = new ArrayList<String>();
    private ArrayList<String> TMR = new ArrayList<String>();


    /**
     * Param Constructor
     * Initializes default values
     * @param WEEK
     * @param ID
     */
    Day(int WEEK, int ID) {
        this.WEEK = WEEK;
        this.ID = ID;
        String rawcontent = readFile(); // read target file content
        String[] parsedcontent = rawcontent.split("\n"); // parse into array


        // Dish name
        this.TITLE = parsedcontent[0];
        // Dish prep time
        this.TIME = parsedcontent[1];

        // Initialize ingredient elements
        for (int i = 3; parsedcontent[i].length()!= 0;i++){
            INGREDIENTS.add(parsedcontent[i]);
        }

        // Initialize steps
        for (int i = parsedcontent.length -1; !parsedcontent[i].startsWith("Steps");i--){
            STEPS.add(parsedcontent[i]);
        }
        Collections.reverse(STEPS);

        // Initialize funfacts
        FUNFACT = parseByKeyWord("Fakta", FUNFACT, parsedcontent);

        // Initialize TMR
        TMR = parseByKeyWord("Till imorgon", TMR, parsedcontent);


    }

    /**
     * Getter methods
     */
    public String getTitle() {return this.TITLE;}
    public String getTime() {return this.TIME;}
    public ArrayList<String> getINGREDIENTS() {return this.INGREDIENTS;}
    public ArrayList<String> getSTEPS() {return this.STEPS;}
    public ArrayList<String> getFUNFACT() {return this.FUNFACT;}
    public ArrayList<String> getTMR() {return this.TMR;}



    /**
     * Method for running/testing other private methods
     */
    public void testMethod() {
        this.printIngredients();
    }


    /**
     * Method for parsing certain sections
     */
    private ArrayList<String> parseByKeyWord(String keyword, ArrayList<String> dst, String[] source) {
        int i = 0;
        try {
            while (!source[i].startsWith(keyword)) {
                i++;
            }
            i++;
            while (source[i].length() != 0) {
                dst.add(source[i]);
                i++;
            }
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            dst.clear();
        }
        return dst;
    }


    /**
     * Read/open a text file that has stored all the steps.
     */
    public String readFile() {

        // The name of the file to open.
        String fileName
                = "D:\\IntelliJ\\Projects\\Matkassen\\Weeks\\Vecka" + Integer.toString(WEEK) +
                "\\DAG" + Integer.toString(ID) + ".txt";

        // This will reference one line at a time
        String line = null;
        String filecontent = "";

        try {
            // InputStreamReader reads text
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(fileName), "UTF-8");

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(reader);

            while((line = bufferedReader.readLine()) != null) {
                filecontent += line + '\n';
            }

            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
        }

        return filecontent;
    }


    /**
     * Print Ingredients
     */
    public void printIngredients() {

        // OPTION 1: "Advanced" for-loop iteration
        /*
        for (String ingredient : INGREDIENTS) {
            System.out.println(ingredient);
        }
        */

        // OPTION 2: iterator loop iteration
        Iterator<String> ingredientIterator = INGREDIENTS.iterator();
        while(ingredientIterator.hasNext()) {
            System.out.println(ingredientIterator.next());
        }
    }





    /**
     * User input method for adding INGREDIENTS and corresponding QT
     * First QT, then INGREDIENTS, then next.
     * @deprecated
     */
    private void addIngredients() {


        // Scanner (for User Input)
        Scanner reader = new Scanner(System.in);
        // While-loop to keep adding ingredients until done
        String n = "";
        while (!n.equals("q!")) {
            System.out.println("Enter Ingredient: ");
            n = reader.nextLine();
            if (!n.equals("q!")) {
                INGREDIENTS.add(n);
            }
            System.out.println(n);
        }
        reader.close();

        printIngredients();

    }

}
