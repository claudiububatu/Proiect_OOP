package elves;

public class PinkElf extends Elf {
//    static String colour = "pink";

    public PinkElf() { }

    public double modifyBudget(double budget) {
        double increase = 30 * budget / 100;
        return budget + increase;
    }

//    public String getColour() {
//        return colour;
//    }
}
