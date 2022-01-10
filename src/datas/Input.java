package datas;

import java.util.List;

public final class Input {
    private int numberOfYears;
    private int santaBudget;
    private InitialData data;
    private List<AnnualChange> chg;

    public Input(final int numberOfYears, final int santaBudget,
                 final InitialData data, final List<AnnualChange> chg) {
        this.numberOfYears = numberOfYears;
        this.santaBudget = santaBudget;
        this.data = data;
        this.chg = chg;
    }

    public List<AnnualChange> getChg() {
        return chg;
    }

    public int getNumberOfYears() {
        return numberOfYears;
    }

    public void setNumberOfYears(final int numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    public int getSantaBudget() {
        return santaBudget;
    }

    public void setSantaBudget(final int santaBudget) {
        this.santaBudget = santaBudget;
    }

    public InitialData getData() {
        return data;
    }

}
