package datas;

import pattern.Child;

import java.util.List;

public final class InitialData {
    private List<Child> children;
    private List<Gift> gifts;

    public InitialData(final List<Child> children,
                       final List<Gift> gifts) {
        this.children = children;
        this.gifts = gifts;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(final List<Child> children) {
        this.children = children;
    }

    /**
     * function built for adding a child in the children list
     * @param child represents a child
     */
    public void addChildren(final Child child) {
        this.children.add(child);
    }


    public List<Gift> getGifts() {
        return gifts;
    }

    public void setGifts(final List<Gift> gifts) {
        this.gifts = gifts;
    }

    /**
     * function built for adding a gift in the gifts list
     * @param gift represents a gift
     */
    public void addGift(final Gift gift) {
        this.gifts.add(gift);
    }

    @Override
    public String toString() {
        return "InitialData{" +
                "children=" + children +
                ", gifts=" + gifts +
                '}';
    }
}
