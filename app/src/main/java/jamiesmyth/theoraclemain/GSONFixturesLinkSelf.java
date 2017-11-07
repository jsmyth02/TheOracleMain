package jamiesmyth.theoraclemain;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GSONFixturesLinkSelf implements Serializable {
    @SerializedName("href")
    @Expose
    private String fixtureSelfLink;

    public String getFixtureSelfLink()
    {
        return fixtureSelfLink;
    }

    public void setFixtureSelfLink(String fixtureSelfLink)
    {
        this.fixtureSelfLink = fixtureSelfLink;
    }
}
