package jamiesmyth.theoraclemain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GSONFixtureResult implements Serializable {
    @SerializedName("goalsHomeTeam")
    @Expose
    private int goalsHomeTeam;
    @SerializedName("goalsAwayTeam")
    @Expose
    private int goalsAwayTeam;

    public int getGoalsHomeTeam() {
        return goalsHomeTeam;
    }

    public int getGoalsAwayTeam() {
        return goalsAwayTeam;
    }
}
