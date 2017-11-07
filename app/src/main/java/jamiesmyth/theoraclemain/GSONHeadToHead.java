package jamiesmyth.theoraclemain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GSONHeadToHead implements Serializable {
    @SerializedName("_links")
    @Expose
    private GSONFixturesLinks links;
    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("fixtures")
    @Expose
    private List<GSONFixtures> fixtures;

    public GSONFixturesLinks getLinks() {
        return links;
    }

    public int getCount() {
        return count;
    }

    public List<GSONFixtures> getFixtures() {
        return fixtures;
    }
}
