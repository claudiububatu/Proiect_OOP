package pattern;

import common.Constants;

public final class Parent implements Visitor {

    @Override
    public double visit(final Kid kid) {
        double averageScore = 0;
        for (Double score : kid.getNiceScoreHistory()) {
            averageScore += score;
        }
        averageScore /= kid.getNiceScoreHistory().size();
        averageScore += averageScore * kid.getNiceScoreBonus() / 100;
        if (averageScore > Constants.MAX_AVERAGE_SCORE) {
            averageScore = Constants.MAX_AVERAGE_SCORE;
        }
        return averageScore;
    }

    @Override
    public double visit(final Teen teen) {
        double averageScore = 0;
        int numitor = 0;
        for (int i = 0; i < teen.getNiceScoreHistory().size(); i++) {
            averageScore += teen.getNiceScoreHistory().get(i) * (i + 1);
            numitor += i + 1;
        }
        averageScore /= numitor;
        averageScore += averageScore * teen.getNiceScoreBonus() / 100;
        if (averageScore > Constants.MAX_AVERAGE_SCORE) {
            averageScore = Constants.MAX_AVERAGE_SCORE;
        }
        return averageScore;
    }

    @Override
    public double visit(final YoungAdult youngAdult) {
        return 0;
    }

    @Override
    public double visit(final Baby baby) {
        double averageScore = Constants.BABY_AVERAGE_SCORE;
        averageScore += averageScore * baby.getNiceScoreBonus() / 100;
        if (averageScore > Constants.MAX_AVERAGE_SCORE) {
            averageScore = Constants.MAX_AVERAGE_SCORE;
        }
        return averageScore;
    }

    @Override
    public double visit(final Child child) {
        return 0;
    }

}
