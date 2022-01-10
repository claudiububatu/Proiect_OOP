package datas;

import datas.Gift;
import pattern.Visitor;

import java.util.ArrayList;
import java.util.List;

public class ChildOutput {
    private int id;
    private String lastName;
    private String firstName;
    private String city;
    private int age;
    private List<String> giftsPreferences;
    private double averageScore;

    private List<Double> niceScoreHistory;
    private double assignedBudget;
    private List<GiftOutput> receivedGifts;

    public ChildOutput(final int id, final String lastName,
                 final String firstName, final String city,
                 final int age, final List<Double> niceScore,
                 final List<String> giftsPreferences,
                 final double averageScore, final double assignedBudget,
                 final List<GiftOutput> receivedGifts) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.city = city;
        this.age = age;

        this.niceScoreHistory = niceScore;
        this.giftsPreferences = giftsPreferences;
        this.averageScore = averageScore;
        this.assignedBudget = assignedBudget;
        this.receivedGifts = receivedGifts;
    }

    /**
     * getter
     */

    public final List<GiftOutput> getReceivedGifts() {
        return receivedGifts;
    }

    /**
     * setter
     */
    public void setReceivedGifts(final List<GiftOutput> receivedGifts) {
        this.receivedGifts = receivedGifts;
    }

    /**
     * function built for adding a gift to the
     * receivedGifts list
     */

    public void receiveGift(final GiftOutput gift) {
        this.receivedGifts.add(gift);
    }

    /**
     * getter
     */

    public final double getAssignedBudget() {
        return assignedBudget;
    }

    /**
     * setter
     */
    public void setAssignedBudget(final double assignedBudget) {
        this.assignedBudget = assignedBudget;
    }

    /**
     * getter
     */
    public final double getAverageScore() {
        return averageScore;
    }

    /**
     * setter
     */
    public void setAverageScore(final double averageScore) {
        this.averageScore = averageScore;
    }

    /**
     * getter
     */
    public final int getId() {
        return id;
    }

    /**
     * setter
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * getter
     */
    public final String getLastName() {
        return lastName;
    }

    /**
     * setter
     */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /**
     * getter
     */
    public final String getFirstName() {
        return firstName;
    }

    /**
     * setter
     */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /**
     * getter
     */
    public final int getAge() {
        return age;
    }

    /**
     * setter
     */
    public void setAge(final int age) {
        this.age = age;
    }

    /**
     * function to increase the age every year
     */
    public void increaseAge() {
        this.age += 1;
    }

    /**
     * getter
     */
    public final String getCity() {
        return city;
    }

    /**
     * setter
     */
    public void setCity(final String city) {
        this.city = city;
    }

    /**
     * getter
     */
    public final List<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }

    /**
     * setter
     */
    public void setNiceScoreHistory(final List<Double> niceScoreHistory) {
        this.niceScoreHistory = niceScoreHistory;
    }

    /**
     * function built for adding a Double
     * to the niceScoreHistory Double list
     */
    public void addToNiceScoreHistory(final Double niceScore) {
        this.niceScoreHistory.add(niceScore);
    }

    /**
     * getter
     */
    public final List<String> getGiftsPreferences() {
        return giftsPreferences;
    }

    /**
     * setter
     */
    public void setGiftsPreferences(final List<String> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }

    /**
     * function built for adding a String
     * to the giftsPreferences String list
     */
    public void addGiftPreference(final String giftPreference) {
        this.giftsPreferences.add(0, giftPreference);
    }

    /**
     * this function eliminates all the duplicates
     * from a String list
     */
    public final List<String> removeDuplicates(final List<String> gifts) {
        List<String> list = new ArrayList<>();
        for (String gift : gifts) {
            if (!list.contains(gift)) {
                list.add(gift);
            }
        }
        return list;
    }

    /**
     * necessary for implementing the pattern
     */
//    public void accept(final Visitor v) {
//        v.visit(this);
//    }
}
