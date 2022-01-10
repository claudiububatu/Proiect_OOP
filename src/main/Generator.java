package main;

import datas.AnnualChange;
import datas.InitialData;
import datas.Input;

import java.util.ArrayList;
import java.util.List;

public class Generator {

    private InitialData data = null;
    private final List<AnnualChange> chg = new ArrayList<>();

    public Generator() { }

    /**
     * @param input - I take the data from input
     * @return number of years
     */
    public final int populateWithNumberOfYears(final Input input) {
        return input.getNumberOfYears();
    }

    /**
     * @param input - I take the data from input
     * @return santa budget
     */
    public final int populateWithSantaBudget(final Input input) {
        return input.getSantaBudget();
    }

    /**
     * @param input - I take the data from input
     * @return initial data
     */
    public final InitialData populateWithDatas(final Input input) {
        data = new InitialData(input.getData().getChildren(), input.getData().getGifts());
        return data;
    }

    /**
     * @param input - I take the data from input
     * @return the list of annual changes
     */
    public final List<AnnualChange> populateWithChg(final Input input) {
        for (AnnualChange annualchange : input.getChg()) {
            chg.add(new AnnualChange(annualchange.getNewSantaBudget(), annualchange.getNewGifts(),
                    annualchange.getNewChildren(), annualchange.getChildrenUpdates(),
                    annualchange.getStrategy()));
        }
        return chg;
    }
}
