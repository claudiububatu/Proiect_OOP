package datas;

import pattern.Child;
import java.util.List;

public final class AnnualChildren {
    private List<ChildOutput> children;

    public AnnualChildren() { }

    public AnnualChildren(final List<ChildOutput> children) {
        this.children = children;
    }

    public List<ChildOutput> getChildren() {
        return children;
    }

    public void setChildren(final List<ChildOutput> children) {
        this.children = children;
    }

    /**
     * function built for adding a child to the children list
     */
    public void addChildren(final ChildOutput c) {
        this.children.add(c);
    }

}
