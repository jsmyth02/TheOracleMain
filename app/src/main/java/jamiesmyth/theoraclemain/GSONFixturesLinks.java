package jamiesmyth.theoraclemain;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GSONFixturesLinks implements Serializable {
    @SerializedName("self")
    @Expose
    private GSONFixturesLinkSelf fixtureLink;
    @SerializedName("homeTeam")
    @Expose
    private GSONFixturesLinkHomeTeam homeFixtureLink;
    @SerializedName("awayTeam")
    @Expose
    private GSONFixturesLinkAwayTeam awayFixtureLink;

    public GSONFixturesLinkSelf getFixtureLink()
    {
        return fixtureLink;
    }

    public GSONFixturesLinkHomeTeam getHomeFixtureLink()
    {
        return homeFixtureLink;
    }

    public GSONFixturesLinkAwayTeam getAwayFixtureLink()
    {
        return awayFixtureLink;
    }

}
