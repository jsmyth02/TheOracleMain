package jamiesmyth.theoraclemain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GSONFixtures implements Serializable {
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("homeTeamName")
    @Expose
    private String homeTeamName;
    @SerializedName("awayTeamName")
    @Expose
    private String awayTeamName;
    @SerializedName("_links")
    @Expose
    private GSONFixturesLinks links;
    @SerializedName("result")
    @Expose
    private GSONFixtureResult result;
    @SerializedName("count")
    @Expose
    private int count;

    public String getDate()
    {
        return date;
    }

    public String getHomeTeamName()
    {
        return homeTeamName;
    }

    public String getAwayTeamName ()
    {
        return awayTeamName;
    }

    public GSONFixturesLinks getLinks() {
        return links;
    }

    public GSONFixtureResult getResult() {
        return result;
    }

    public int getCount() {
        return count;
    }
}
