package pattern;

public interface Visitable {
    /**
     * necessary for pattern
     */
    void accept(Visitor v);
}
