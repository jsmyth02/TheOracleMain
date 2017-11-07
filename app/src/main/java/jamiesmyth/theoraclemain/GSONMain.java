package jamiesmyth.theoraclemain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GSONMain implements Serializable{
    @SerializedName("count")
    @Expose
    private int count;

    @SerializedName("fixtures")
    @Expose
    private List<GSONFixtures> fixtures;

    @SerializedName("fixture")
    @Expose
    private GSONFixtures fixture;

    @SerializedName("head2head")
    @Expose
    private GSONHeadToHead statFixtures;

    public int getCount() {
        return count;
    }

    public GSONFixtures getFixture() {
        return fixture;
    }

    public List<GSONFixtures> getFixtures() {
        return fixtures;
    }

    public GSONHeadToHead getStatFixtures() {
        return statFixtures;
    }
}
