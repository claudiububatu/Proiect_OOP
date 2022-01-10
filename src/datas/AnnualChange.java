package datas;

import pattern.Child;

import java.util.List;

public final class AnnualChange {
    private int newSantaBudget;
    private String strategy;
    private List<Gift> newGifts;
    private List<Child> newChildren;
    private List<ChildUpdate> childrenUpdates;

    public AnnualChange(final int newSantaBudget, final List<Gift> newGifts,
                        final List<Child> newChildren, final List<ChildUpdate> childrenUpdates,
                        final String strategy) {
        this.newSantaBudget = newSantaBudget;
        this.newGifts = newGifts;
        this.newChildren = newChildren;
        this.childrenUpdates = childrenUpdates;
        this.strategy = strategy;
    }

    public int getNewSantaBudget() {
        return newSantaBudget;
    }

    public void setNewSantaBudget(final int newSantaBudget) {
        this.newSantaBudget = newSantaBudget;
    }

    public List<Gift> getNewGifts() {
        return newGifts;
    }

    public void setNewGifts(final List<Gift> newGifts) {
        this.newGifts = newGifts;
    }

    public List<Child> getNewChildren() {
        return newChildren;
    }

    public void setNewChildren(final List<Child> newChildren) {
        this.newChildren = newChildren;
    }

    public List<ChildUpdate> getChildrenUpdates() {
        return childrenUpdates;
    }

    public void setChildrenUpdates(final List<ChildUpdate> childrenUpdates) {
        this.childrenUpdates = childrenUpdates;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }
}
