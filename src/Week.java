import java.sql.SQLOutput;

public class Week {
    private int NUMBER;
    private Day[] Days = new Day[4];


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
        for (Day object: Days)
            object.printIngredients();
    }

}
