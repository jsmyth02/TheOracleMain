package jamiesmyth.theoraclemain;

import android.util.Log;

public class CreateAPIAddress {
    public String getFixtureListAPI(String weekNumber, String competition) {
        int compId = getCompetitionId(competition);

        String fixtureListAPICall = "http://api.football-data.org/v1/competitions/" + compId + "/fixtures?matchday=" + weekNumber;

        return fixtureListAPICall;
    }

    public int getCompetitionId (String competition) {
        int compId = 0;

        switch (competition){
            case "Premiership": compId = 445;
                break;
            case "Championship": compId = 446;
                break;
            case "League One": compId = 447;
                break;
            case "League Two": compId = 448;
                break;
            default:
                Log.e("", "no case");
                break;
        }

        return compId;
    }
}
