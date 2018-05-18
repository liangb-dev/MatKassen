public class Matkassen{
    public static void main(String[] args) {
        Day one = new Day(11,1);
        Day two = new Day(11,2);
        Day three = new Day(11,3);
        Day four = new Day(11,4);

        System.out.println("The name of the dishes for this week are: ");
        System.out.println(one.getTitle());
        System.out.println(two.getTitle());
        System.out.println(three.getTitle());
        System.out.println(four.getTitle());

        System.out.println("The ingredients that you need to buy are: ");
        one.printIngredients();
        two.printIngredients();
        three.printIngredients();
        four.printIngredients();



    }
}