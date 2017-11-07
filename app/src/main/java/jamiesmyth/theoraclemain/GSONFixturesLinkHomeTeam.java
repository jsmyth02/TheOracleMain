package jamiesmyth.theoraclemain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GSONFixturesLinkHomeTeam implements Serializable {
    @SerializedName("href")
    @Expose
    private String fixtureHomeLink;

    public String getFixtureHomeLink()
    {
        return fixtureHomeLink;
    }

    public void setFixtureHomeLink(String fixtureHomeLink)
    {
        this.fixtureHomeLink = fixtureHomeLink;
    }
}
