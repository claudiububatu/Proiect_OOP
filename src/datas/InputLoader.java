package datas;

import common.Constants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import pattern.Child;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class InputLoader {
    private final String inputPath;

    public InputLoader(final String inputPath) {
        this.inputPath = inputPath;
    }

    /**
     * function built for reading the data from input files
     */
    public Input readData() {
        JSONParser jsonParser = new JSONParser();
        int numberOfYears = 0;
        int santaBudget = 0;
        InitialData data = null;
        List<AnnualChange> chg = new ArrayList<>();
        List<Child> children = new ArrayList<>();
        List<Gift> santaGiftsList = new ArrayList<>();
        try {
            final JSONObject jsonObject = (JSONObject) jsonParser
                    .parse(new FileReader(inputPath));
            final long jNumber = (long) jsonObject.get(Constants.NUMBEROFYEARS);
            final long jBudget = (long) jsonObject.get(Constants.BUDGET);

            final JSONObject jData = (JSONObject) jsonObject.get(Constants.INITIALDATA);

            final int jsonNumber = (int) jNumber;
            final int jsonBudget = (int) jBudget;


            if (jsonNumber != 0) {
                numberOfYears = jsonNumber;
            }

            if (jsonBudget != 0) {
                santaBudget = jsonBudget;
            }

            final JSONArray jChildren = (JSONArray) jData.get(Constants.CHILDREN);


            for (Object jChild : jChildren) {
                final int id = Math.toIntExact((Long) ((JSONObject) jChild).get("id"));
                final int age = Math.toIntExact((Long) ((JSONObject) jChild).get("age"));

                final String lastName = (String) ((JSONObject) jChild).get("lastName");
                final String firstName = (String) ((JSONObject) jChild).get("firstName");
                final String city = (String) ((JSONObject) jChild).get("city");
                List<Double> niceScoreList = new ArrayList<>();

                final double niceScore = (double) ((Long) ((JSONObject) jChild).get("niceScore"));
                niceScoreList.add(niceScore);
                final List<String> giftsPreferences = (List<String>) ((JSONObject) jChild).
                        get("giftsPreferences");
                final double niceScoreBonus = (double) ((Long) ((JSONObject) jChild).get("niceScoreBonus"));
                final String elf = (String) ((JSONObject) jChild).get("elf");

                children.add(new Child(id, lastName, firstName, city, age,
                        niceScoreList, giftsPreferences, 0, 0, null, niceScoreBonus, elf));
            }

            final JSONArray jGifts = (JSONArray) jData.get("santaGiftsList");
            for (Object jGift : jGifts) {
                final String productName = (String) ((JSONObject) jGift).get("productName");
                final int price = Math.toIntExact((Long) ((JSONObject) jGift).get("price"));
                final String category = (String) ((JSONObject) jGift).get("category");
                final int quantity = Math.toIntExact((Long) ((JSONObject) jGift).get("quantity"));
                santaGiftsList.add(new Gift(productName, price, category, quantity));
            }

            data = new InitialData(children, santaGiftsList);

            final JSONArray jChg = (JSONArray) jsonObject.get(Constants.ANNUALCHANGES);
            for (Object jC : jChg) {
                final List<ChildUpdate> childUpdates = new ArrayList<>();
                final List<Gift> newSantaGiftsList = new ArrayList<>();
                final List<Child> newChildren = new ArrayList<>();
                final String strategy = (String) ((JSONObject) jC).get("strategy");
                final int newSantaBudget = Math.toIntExact((Long) ((JSONObject) jC).
                        get("newSantaBudget"));

                final List<Gift> newGifts = (List<Gift>) ((JSONObject) jC).get("newGifts");
                for (Object newGift : newGifts) {
                    final String productName = (String) ((JSONObject) newGift).get("productName");
                    final int price = Math.toIntExact((Long) ((JSONObject) newGift).get("price"));
                    final String category = (String) ((JSONObject) newGift).get("category");
                    final int quantity = Math.toIntExact((Long) ((JSONObject) newGift).get("quantity"));

                    newSantaGiftsList.add(new Gift(productName, price, category, quantity));
                }

                final List<Child> newJChildren = (List<Child>) ((JSONObject) jC).get("newChildren");

                for (Object newChild : newJChildren) {

                    final List<Double> newNiceScore = new ArrayList<>();
                    final int id = Math.toIntExact((Long) ((JSONObject) newChild).get("id"));
                    final int age = Math.toIntExact((Long) ((JSONObject) newChild).get("age"));

                    final String lastName = (String) ((JSONObject) newChild).get("lastName");
                    final String firstName = (String) ((JSONObject) newChild).get("firstName");
                    final String city = (String) ((JSONObject) newChild).get("city");

                    final double niceScore = (double) ((Long) ((JSONObject) newChild).
                            get("niceScore"));
                    newNiceScore.add(niceScore);
                    final List<String> giftsPreferences = (List<String>) ((JSONObject) newChild).
                            get("giftsPreferences");
                    final double niceScoreBonus = (double) ((Long) ((JSONObject) newChild).
                            get("niceScoreBonus"));
                    final String elf = (String) ((JSONObject) newChild).get("elf");
                    newChildren.add(new Child(id, lastName, firstName, city, age,
                            newNiceScore, giftsPreferences,
                            0, 0, null, niceScoreBonus, elf));
                }

                final List<ChildUpdate> jChildUpdates = (List<ChildUpdate>) ((JSONObject) jC).
                        get("childrenUpdates");
                for (Object jChildUpdate : jChildUpdates) {
                    final int id = Math.toIntExact((Long) ((JSONObject) jChildUpdate).get("id"));
                    final double niceScore;
                    final List<String> giftsPreferences = (List<String>) ((JSONObject)
                            jChildUpdate).get("giftsPreferences");
                    final String elf = (String) ((JSONObject) jChildUpdate).get("elf");

                    if (((JSONObject) jChildUpdate).get("niceScore") == null) {
                        niceScore = -1;
                    } else {
                        niceScore = (double) ((Long) ((JSONObject) jChildUpdate).
                                get("niceScore"));
                    }
                    childUpdates.add(new ChildUpdate(id, niceScore, giftsPreferences, elf));

                }
                chg.add(new AnnualChange(newSantaBudget, newSantaGiftsList,
                        newChildren, childUpdates, strategy));
            }



            if (jsonBudget == 0) {
                santaBudget = 0;
            }

            if (jsonNumber == 0) {
                numberOfYears = 0;
            }

            if (jData == null) {
                data = null;
            }

            if (jChg == null) {
                chg = null;
            }

        } catch (ParseException | IOException | NullPointerException e) {
            e.printStackTrace();
        }

        return new Input(numberOfYears, santaBudget, data, chg);
    }
}
