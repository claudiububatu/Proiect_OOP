package datas;

import java.util.List;

public final class AnnualChildrenPadre {
    private List<AnnualChildren> annualchildren;

    public AnnualChildrenPadre() { }

    public AnnualChildrenPadre(final List<AnnualChildren> annualChildren) {
        this.annualchildren = annualChildren;
    }

    public List<AnnualChildren> getAnnualChildren() {
        return annualchildren;
    }

    public void setAnnualChildren(final List<AnnualChildren> annualC) {
        annualchildren = annualC;
    }

    /**
     * function built for adding all the children for a year
     * @param annualChildren represents the children on a 1 year-time
     */
    public void addAnnualChildrenPadre(final AnnualChildren annualChildren) {
        this.annualchildren.add(annualChildren);
    }

}
