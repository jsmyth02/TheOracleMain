package jamiesmyth.theoraclemain;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONObject;

public class Calculations {
    public static final String TAG = Calculations.class.getSimpleName();

    int numberOfMatches;

    GSONMain mGSONMain;

    public int calculatePercentageOfBtts (JSONObject response, boolean headToHead) {
        int count = 0;
        int percentageOfBtts = 0;

        Gson gson = new Gson();
        mGSONMain = gson.fromJson(response.toString(), GSONMain.class);

        if (headToHead) {
            numberOfMatches = mGSONMain.getStatFixtures().getCount();

            if (numberOfMatches > 0) {
                for (int i = 0; i < numberOfMatches; i++) {
                    if ((mGSONMain.getStatFixtures().getFixtures().get(i).getResult().getGoalsHomeTeam() > 0) && (mGSONMain.getStatFixtures().getFixtures().get(i).getResult().getGoalsAwayTeam() > 0)) {
                        count++;
                    }
                }
            }
            else {
                return 0;
            }
        }
        else {
            numberOfMatches = mGSONMain.getCount();

            for (int i = 0; i < numberOfMatches; i++) {
                if ((mGSONMain.getFixtures().get(i).getResult().getGoalsHomeTeam() > 0) && (mGSONMain.getFixtures().get(i).getResult().getGoalsAwayTeam() > 0)) {
                    count++;
                }
            }
        }

        percentageOfBtts = (int)(((double)count/(double)numberOfMatches) * 100);

        return percentageOfBtts;
    }

    public int calculatePercentageOfOverTwo (JSONObject response, boolean headToHead) {
        int count = 0;
        int percentageOfBtts = 0;

        Gson gson = new Gson();
        mGSONMain = gson.fromJson(response.toString(), GSONMain.class);

        if (headToHead) {
            numberOfMatches = mGSONMain.getStatFixtures().getCount();

            if (numberOfMatches > 0) {
                for (int i = 0; i < numberOfMatches; i++) {
                    if ((mGSONMain.getStatFixtures().getFixtures().get(i).getResult().getGoalsHomeTeam() + mGSONMain.getStatFixtures().getFixtures().get(i).getResult().getGoalsAwayTeam()) > 2) {
                        count++;
                    }

                    // TESTING
                    Log.d(TAG, String.valueOf(mGSONMain.getStatFixtures().getFixtures().get(i).getResult().getGoalsHomeTeam() + " V " + mGSONMain.getStatFixtures().getFixtures().get(i).getResult().getGoalsAwayTeam()));
                }
            }
            else {
                return 0;
            }
        }
        else {
            numberOfMatches = mGSONMain.getCount();

            for (int i = 0; i < numberOfMatches; i++) {
                if ((mGSONMain.getFixtures().get(i).getResult().getGoalsHomeTeam() + mGSONMain.getFixtures().get(i).getResult().getGoalsAwayTeam()) > 2) {
                    count++;
                }
            }
        }

        percentageOfBtts = (int)(((double)count/(double)numberOfMatches) * 100);


        return percentageOfBtts;
    }

    public int calculatePercentageOfOverOne (JSONObject response, boolean headToHead) {
        int count = 0;
        int percentageOfBtts = 0;

        Gson gson = new Gson();
        mGSONMain = gson.fromJson(response.toString(), GSONMain.class);

        if (headToHead) {
            numberOfMatches = mGSONMain.getStatFixtures().getCount();

            if (numberOfMatches > 0) {
                for (int i = 0; i < numberOfMatches; i++) {
                    if ((mGSONMain.getStatFixtures().getFixtures().get(i).getResult().getGoalsHomeTeam() + mGSONMain.getStatFixtures().getFixtures().get(i).getResult().getGoalsAwayTeam()) > 1) {
                        count++;
                    }

                    // TESTING
                    Log.d(TAG, String.valueOf(mGSONMain.getStatFixtures().getFixtures().get(i).getResult().getGoalsHomeTeam() + " V " + mGSONMain.getStatFixtures().getFixtures().get(i).getResult().getGoalsAwayTeam()));
                }
            }
            else {
                return 0;
            }
        }
        else {
            numberOfMatches = mGSONMain.getCount();

            for (int i = 0; i < numberOfMatches; i++) {
                if ((mGSONMain.getFixtures().get(i).getResult().getGoalsHomeTeam() + mGSONMain.getFixtures().get(i).getResult().getGoalsAwayTeam()) > 1) {
                    count++;
                }
            }
        }

        percentageOfBtts = (int)(((double)count/(double)numberOfMatches) * 100);


        return percentageOfBtts;
    }

    public float calculateGoalsFor (JSONObject response, String teamName) {
        int count = 0;
        float avgGoals = 0;

        Gson gson = new Gson();
        mGSONMain = gson.fromJson(response.toString(), GSONMain.class);

        numberOfMatches = mGSONMain.getStatFixtures().getCount();



        if (numberOfMatches > 0) {
            for (int i = 0; i < numberOfMatches; i++) {
                if (mGSONMain.getFixtures().get(i).getHomeTeamName() == teamName) {
                    count += mGSONMain.getFixtures().get(i).getResult().getGoalsHomeTeam();
                }
                else {
                    count += mGSONMain.getFixtures().get(i).getResult().getGoalsAwayTeam();
                }
            }
        }
        else {
            return 0;
        }

        return avgGoals;
    }

    public void calculateGriffsRule (JSONObject response) {
        double points = 0;

        Gson gson = new Gson();
        mGSONMain = gson.fromJson(response.toString(), GSONMain.class);

        numberOfMatches = mGSONMain.getCount();

        for (int i = 0; i < numberOfMatches; i++) {
            // TESTING
            Log.d(TAG, String.valueOf(mGSONMain.getFixtures().get(i).getResult().getGoalsHomeTeam() + " V " + mGSONMain.getFixtures().get(i).getResult().getGoalsAwayTeam()));


            if ((mGSONMain.getFixtures().get(i).getResult().getGoalsAwayTeam() + mGSONMain.getFixtures().get(i).getResult().getGoalsHomeTeam()) > 2) {
                points += 0.5;
            }
            else {
                points -= 0.5;
            }
            if ((mGSONMain.getFixtures().get(i).getResult().getGoalsHomeTeam() > 0) && (mGSONMain.getFixtures().get(i).getResult().getGoalsAwayTeam() > 0)) {
                points += 0.75;
            }
            else {
                points -= 0.75;
            }
        }

        double testing = points;
        double tester = points;
    }
}
