package pattern;

import datas.Gift;
import datas.GiftOutput;

import java.util.List;

public final class Kid extends Child {
    public Kid(final int id, final String lastName, final String firstName, final int age,
               final String city, final List<Double> niceScore,
               final List<String> giftsPreference, final double averageScore,
               final double assignedBudget, final List<GiftOutput> receivedGifts,
               final double niceScoreBonus, final String elf) {
        super(id, lastName, firstName, city, age, niceScore,
                giftsPreference, averageScore, assignedBudget,
                receivedGifts, niceScoreBonus, elf);
    }

    @Override
    public void accept(final Visitor v) {
        v.visit(this);
    }
}
