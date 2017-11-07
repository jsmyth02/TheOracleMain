package jamiesmyth.theoraclemain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GSONFixturesLinkAwayTeam implements Serializable {
    @SerializedName("href")
    @Expose
    private String fixtureAwayLink;

    public String getFixtureAwayLink()
    {
        return fixtureAwayLink;
    }

    public void setFixtureAwayLink(String fixtureAwayLink)
    {
        this.fixtureAwayLink = fixtureAwayLink;
    }
}
