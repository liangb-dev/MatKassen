import javafx.css.ParsedValue;

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
    private ArrayList<ArrayList<String>> PARSED_INGREDIENTS = new ArrayList<>();
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

        System.out.println(parsedcontent[0]);

        // Dish name
        this.TITLE = parsedcontent[0];
        // Dish prep time
        this.TIME = parsedcontent[1];


        // Initialize ingredient elements
        parseByKeyWord("Ingredienser:", INGREDIENTS, parsedcontent);

        parseIngredients();

        // Initialize steps
        for (int i = parsedcontent.length -1; !parsedcontent[i].startsWith("Steps");i--){
            STEPS.add(parsedcontent[i]);
        }
        Collections.reverse(STEPS);

        // Initialize funfacts
        parseByKeyWord("Fakta", FUNFACT, parsedcontent);
        // Initialize TMR
        parseByKeyWord("Till imorgon", TMR, parsedcontent);


    }

    public void parseIngredients() {
        int it = 0; // iterator
        // Get every line of ingredients
        for (String line : INGREDIENTS) {
            PARSED_INGREDIENTS.add(it, new ArrayList<String>()); // Create container
            String[] ing_desc = line.split("\\("); //ingredient/description split
            // Add QT/Units/Produce to PARSE_INGREDIENTS
            String[] ing = ing_desc[0].split(" ");
            if (Character.isLetter(ing[0].charAt(0))) {
                System.out.println(ing_desc[0]);
                PARSED_INGREDIENTS.get(it).add(ing_desc[0]); // "Resten av de kokta svarta bönorna"
            }
            else {
                PARSED_INGREDIENTS.get(it).add(0, ing[0]); // Quantity

                int it2 = 1;
                if (ing.length > 2) { // Not all lines have "Unit"
                    PARSED_INGREDIENTS.get(it).add(1, ing[1]); // Unit
                    it2 = 2;
                } else {
                    PARSED_INGREDIENTS.get(it).add(1, ""); // empty cell
                }
                // Rest is Produce
                String produce = "";
                for (int i = it2; i < ing.length; i++) {
                    produce += ing[i]; // Append the rest
                    if (i != ing.length-1) { // Add space " "
                        produce += " ";
                    }
                }
                PARSED_INGREDIENTS.get(it).add(2, produce); // Produce
            }

            // IF there is description, add it too
            if (ing_desc.length > 1) {
                PARSED_INGREDIENTS.get(it).add("("+ing_desc[1]);
            }

            it++; // increment iterator
        }

        for (ArrayList<String> arr : PARSED_INGREDIENTS) {
            if (arr.size() > 2) {
                System.out.println(arr.get(2));
            }
        }

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
     * Running/testing other private methods
     */
    public void testMethod() {
        this.printIngredients();
    }


    /**
     * Parsing certain sections by keyword until next '\n'
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
                = "Weeks\\Vecka" + Integer.toString(WEEK) +
                "\\DAG" + Integer.toString(ID) + ".txt";

        // This will reference one line at a time
        String line = null;
        String filecontent = "";

        try {
            // InputStreamReader reads text
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(fileName), "UTF8");

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
