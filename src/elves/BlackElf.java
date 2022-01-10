package elves;

public class BlackElf extends Elf {
//    static String colour = "black";

    public BlackElf() { }

    @Override
    public double modifyBudget(double budget) {
        double decrease = 30 * budget / 100;
        return budget - decrease;
    }

//    public static String getColour() {
//        return colour;
//    }
}
