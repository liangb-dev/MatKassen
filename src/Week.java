import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Week {
    private int NUMBER;
    private Day[] Days = new Day[4]; // Hold number of days
    private Map<String, String> ingredients = new HashMap<>();


    Week(int NUMBER){
        this.NUMBER = NUMBER;

        Day one = new Day(NUMBER,1);
        Day two = new Day(NUMBER,2);
        Day three = new Day(NUMBER,3);
        Day four = new Day(NUMBER,4);

        Days[0] = one;
        Days[1] = two;
        Days[2] = three;
        Days[3] = four;

        compile_grocery();

    }

    public Day getDay(int num) {
        return Days[num];
    }


    /**
     * Print all the Meals for this week
     */
    public void printAllMeals(){
        for (Day object: Days){
            System.out.println(object.getTitle());
        }
    }

    /**
     * Print all the Ingredients for this week
     */
    public void printAllIngredients(){
        /*for (Day object: Days) {
            object.printProduce();
        }*/

        for (Map.Entry k : ingredients.entrySet()) {
            System.out.println(k.getValue()+ " "+k.getKey());
        }

    }

    public void compile_grocery() {
        for (Day d : Days) {
            for (ArrayList<String> line : d.getIngredients()) {
                String qt_new;
                String product;
                if (line.size() < 2) { // "Resten av de kokta svarta bönorna"
                    qt_new = "";
                    product = line.get(0);
                }
                else if (line.size() < 5) { // "1 paprika"
                    qt_new = line.get(0);
                    product = line.get(2);
                }
                else { // "1 ask tomater"
                    qt_new = line.get(0);
                    product = line.get(1) + " " + line.get(2);
                }

                if (ingredients.containsKey(product)) {
                    String qt_old = ingredients.get(product);
                    //System.out.println(qt_new);
                    //System.out.println(qt_old);
                    ingredients.replace(product, performAddition(qt_new,qt_old));
                } else {
                    ingredients.put(product,qt_new);
                }
            }
        }

        //System.out.println(ingredients.keySet());
    }

    public String performAddition(String a, String b) {


        String[] a_parsed = convertInt(a);
        String[] b_parsed = convertInt(b);
        String result = "";


        if (a.length() >= b.length()) {
            result = additionHelper(a_parsed, b_parsed);
        } else {
            result = additionHelper(b_parsed, a_parsed);
        }

        return result;

    }

    public String[] convertInt(String a) {
        String[] a_parsed = a.split("-");
        for (int i = 0; i < a_parsed.length; i++) {
            if (a_parsed[i].length() == 1) {
                a_parsed[i] = a_parsed[i] + "/" + 1;
            }
        }
        return a_parsed;
    }

    public String additionHelper(String[] a, String[] b) {
        int AminNom = Character.getNumericValue(a[0].charAt(0));
        int AminDen = Character.getNumericValue(a[0].charAt(2));
        int BminNom = Character.getNumericValue(b[0].charAt(0));
        int BminDen = Character.getNumericValue(b[0].charAt(2));
        int AmaxNom;
        int AmaxDen;
        int BmaxNom;
        int BmaxDen;
        String result = AminNom*BminDen+BminNom*AminDen + "/" + AminDen*BminDen;

        if (a.length > 1 && b.length > 1) {
            AmaxNom = Character.getNumericValue(a[1].charAt(0));
            AmaxDen = Character.getNumericValue(a[1].charAt(2));
            BmaxNom = Character.getNumericValue(b[1].charAt(0));
            BmaxDen = Character.getNumericValue(b[1].charAt(2));

            result += "-" + (AmaxNom*BmaxDen+BmaxNom*AmaxDen) + "/" + AmaxDen*BmaxDen;
        }
        else if (a.length > 1) {
            AmaxNom = Character.getNumericValue(a[1].charAt(0));
            AmaxDen = Character.getNumericValue(a[1].charAt(2));

            result += "-" + (AmaxNom*BminDen+BminNom*AmaxDen) + "/" + AmaxDen*BminDen;
        } else if (b.length > 1) {
            BmaxNom = Character.getNumericValue(b[1].charAt(0));
            BmaxDen = Character.getNumericValue(b[1].charAt(2));

            result += "-" + (BmaxNom*AminDen+AminNom*BmaxDen) + "/" + BmaxDen*AminDen;
        }

        String[] simplify = result.split("-");

        for (int i = 0; i < simplify.length; i++) {

            if (simplify[i].length() > 2) {
                String[] fraction = simplify[i].split("/");
                int num = Integer.parseInt(fraction[0]);
                int den = Integer.parseInt(fraction[1]);
                if ((num % den) == 0) {
                    simplify[i] = num/den+"";
                } else {
                    for (int j = num; j > 0; j++) {
                        if (num%j == 0 && den%j == 0) {
                            simplify[i] = (num/j) + "/" + (den/j);
                            break;
                        }
                    }
                }
            }

        }

        return String.join("-", simplify);

    }


}
