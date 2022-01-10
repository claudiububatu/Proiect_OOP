package main;

import checker.Checker;
import common.Constants;
import datas.*;
import elves.BlackElf;
import elves.Elf;
import elves.PinkElf;
import elves.WhiteElf;
import pattern.Baby;
import pattern.Child;
import pattern.Kid;
import pattern.Teen;
import pattern.Parent;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Class used to run the code
 */
public final class Main {

    private Main() {
        ///constructor for checkstyle
    }
    /**
     * This method is used to call the checker which calculates the score
     * @param args
     *          the arguments used to call the main method
     */
    public static void main(final String[] args) throws IOException, NullPointerException {

        Path path = Paths.get(Constants.RESULT_PATH);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        File outputDirectory = new File(Constants.RESULT_PATH);

        Checker.deleteFiles(outputDirectory.listFiles());

        for (int i = 1; i <= Constants.TESTS_NUMBER; i++) {
            String inputpath = "etapa2_proiect/tests/test" + i + Constants.FILE_EXTENSION;
            String outpath = Constants.OUTPUT_PATH + i + Constants.FILE_EXTENSION;

            File out = new File(outpath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                System.out.println(outpath);
                action(inputpath, outpath);
            }
        }
        Checker.calculateScore();
    }

    /**
     * @param filePath1 for input file
     * @param filePath2 for output file
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void action(final String filePath1,
                              final String filePath2) throws IOException, NullPointerException {
        final InputLoader inputLoader = new InputLoader(filePath1);
        final Input input = inputLoader.readData();
        Writer fileWriter = new Writer(filePath2);
        Generator g = new Generator();
        final int numberOfYears = g.populateWithNumberOfYears(input);
        int santaBudget = g.populateWithSantaBudget(input);
        final InitialData initialData = g.populateWithDatas(input);
        final List<AnnualChange> annualChanges = g.populateWithChg(input);
        AnnualChildrenPadre annualPadre = new AnnualChildrenPadre();
        annualPadre.setAnnualChildren(new ArrayList<>());
        Parent p = new Parent();
        for (int i = 0; i < numberOfYears + 1; i++) {
            AnnualChildren annualChildren = new AnnualChildren();
            annualChildren.setChildren(new ArrayList<>());
            double totalAverageScore = 0;
            double budgetUnit = 0;
            initialData.getChildren().removeIf(child -> child.getAge() > Constants.MAX_TEEN_AGE);
            for (Child c : initialData.getChildren()) {
                int age = c.getAge();
//                System.out.println(c.getElf());
                if (age <= Constants.MAX_BABY_AGE) {
                    Baby baby = new Baby(c.getId(), c.getLastName(),
                            c.getFirstName(), age, c.getCity(),
                            c.getNiceScoreHistory(), c.getGiftsPreferences(), 0,
                            c.getAssignedBudget(), null, c.getNiceScoreBonus(), c.getElf());
                    baby.setAverageScore(p.visit(baby));
                    c.setAverageScore(p.visit(baby));
                    totalAverageScore += c.getAverageScore();
                    baby.accept(p);
                }
                if (age > Constants.MIN_KID_AGE && age < Constants.MAX_KID_AGE) {
                    Kid kid = new Kid(c.getId(), c.getLastName(),
                            c.getFirstName(), age, c.getCity(),
                            c.getNiceScoreHistory(), c.getGiftsPreferences(), 0,
                            c.getAssignedBudget(), null, c.getNiceScoreBonus(), c.getElf());
                    kid.setAverageScore(p.visit(kid));
                    c.setAverageScore(p.visit(kid));
                    totalAverageScore += c.getAverageScore();
                    kid.accept(p);
                }
                if (age >= Constants.MIN_TEEN_AGE && age <= Constants.MAX_TEEN_AGE) {
                    Teen teen = new Teen(c.getId(), c.getLastName(),
                            c.getFirstName(), age, c.getCity(),
                            c.getNiceScoreHistory(), c.getGiftsPreferences(), 0,
                            c.getAssignedBudget(), null, c.getNiceScoreBonus(), c.getElf());
                    c.setAverageScore(p.visit(teen));
                    teen.setAverageScore(p.visit(teen));
                    totalAverageScore += c.getAverageScore();
                    teen.accept(p);
                }
            }
            budgetUnit = santaBudget / totalAverageScore;
            for (Child c : initialData.getChildren()) {
                double budget = c.getAverageScore() * budgetUnit;
                double budgetUpdated = budget;
                if (c.getElf() != null) {
                    if (c.getElf().compareTo("black") == 0) {
                        BlackElf blackElf = new BlackElf();
                        budgetUpdated = blackElf.modifyBudget(budget);
                    } else if (c.getElf().compareTo("pink") == 0) {
                        PinkElf pinkElf = new PinkElf();
                        budgetUpdated = pinkElf.modifyBudget(budget);
                    } else if (c.getElf().compareTo("white") == 0) {
                        WhiteElf whiteElf = new WhiteElf();
                        budgetUpdated = whiteElf.modifyBudget(budget);
                    }
                }
                c.setAssignedBudget(budgetUpdated);
            }
            for (Child c : initialData.getChildren()) {
                int id = c.getId();
                String lastName = c.getLastName();
                String firstName = c.getFirstName();
                int age = c.getAge();
                String city = c.getCity();
                List<Double> niceScore = new ArrayList<>();
                niceScore.addAll(c.getNiceScoreHistory());
                List<String> annualGiftPreferences = new ArrayList<>();
                annualGiftPreferences.addAll(c.getGiftsPreferences());
                c.setReceivedGifts(new ArrayList<>());
                List<String> childPreferences = c.getGiftsPreferences();
                double budget = c.getAssignedBudget();
                for (String preference : childPreferences) {
                    List<Gift> santaGifts = initialData.getGifts();
                    List<GiftOutput> giftsPerCategory = new ArrayList<>();
                    for (Gift santaGift : santaGifts) {
                        if (santaGift.getCategory().equals(preference)) {
                            String productName = santaGift.getProductName();
                            double price = santaGift.getPrice();
                            String category = santaGift.getCategory();
//                            giftsPerCategory.add(santaGift);
                            giftsPerCategory.add(new GiftOutput(productName, price, category));
                        }
                    }
                    if (giftsPerCategory.size() != 0) {
                        Collections.sort(giftsPerCategory, Comparator.comparing(GiftOutput::getPrice));
                        if (giftsPerCategory.get(0).getPrice() <= budget) {
                            c.receiveGift(giftsPerCategory.get(0));
                            budget -= giftsPerCategory.get(0).getPrice();
                        }
                    }
                }
                annualChildren.addChildren(new ChildOutput(id, lastName, firstName, city,
                        age, niceScore, annualGiftPreferences, c.getAverageScore(),
                        c.getAssignedBudget(), c.getReceivedGifts()));
                c.increaseAge();
            }
            if (i != numberOfYears) {
                santaBudget = annualChanges.get(i).getNewSantaBudget();
                List<ChildUpdate> childUpdates = annualChanges.get(i).getChildrenUpdates();
                List<Child> newChildren = annualChanges.get(i).getNewChildren();
                List<Gift> newGifts = annualChanges.get(i).getNewGifts();
                if (newGifts.size() != 0) {
                    for (Gift gift : newGifts) {
                        initialData.addGift(gift);
                    }
                }
                for (Child child : newChildren) {
                    if (child.getAge() <= Constants.MAX_TEEN_AGE) {
                        int id = child.getId();
                        String lastName = child.getLastName();
                        String firstName = child.getFirstName();
                        int age = child.getAge();
                        String city = child.getCity();
                        List<Double> niceScore = child.getNiceScoreHistory();
                        List<String> giftsPreferences = child.getGiftsPreferences();

                        initialData.addChildren(new Child(id, lastName, firstName,
                                city, age, niceScore, giftsPreferences, child.getAverageScore(),
                                child.getAssignedBudget(), child.getReceivedGifts(), 0, null));
                    }
                }
                for (ChildUpdate childUpdate : childUpdates) {
                    List<String> giftsPreferences = childUpdate.getGiftsPreferences();
                    int id = childUpdate.getId();
                    Double niceScore = childUpdate.getNiceScore();
                    for (int k = 0; k < initialData.getChildren().size(); k++) {
                        Child c = initialData.getChildren().get(k);
                        if (c.getId() == id) {
                            if (niceScore != -1) {
                                c.addToNiceScoreHistory(niceScore);
                            }
                            for (int j = giftsPreferences.size() - 1; j >= 0; j--) {
                                c.addGiftPreference(giftsPreferences.get(j));
                            }
                            c.setGiftsPreferences(c.removeDuplicates(c.getGiftsPreferences()));
                        }
                    }
                }
            }

            annualPadre.addAnnualChildrenPadre(annualChildren);
        }

        fileWriter.writeF(annualPadre);
    }
}
