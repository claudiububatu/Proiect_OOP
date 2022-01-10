package pattern;

public interface Visitor {
    /**
     * @param baby - it receives a baby
     * @return the average score of the baby
     */
    double visit(Baby baby);

    /**
     * @param kid - it receives a kid
     * @return the average score of the kid
     */
    double visit(Kid kid);

    /**
     * @param teen - it receives a teen
     * @return the average score of the teen
     */
    double visit(Teen teen);

    /**
     * @param youngAdult - it receives a young adult
     * @return 0 because adults can`t receive gifts
     */
    double visit(YoungAdult youngAdult);

    /**
     * necessary for pattern
     */
    double visit(Child child);
}
