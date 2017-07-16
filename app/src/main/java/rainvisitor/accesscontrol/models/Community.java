package rainvisitor.accesscontrol.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ray on 2017/7/16.
 */

public class Community {
    @SerializedName("data")
    private List<Building> buildingList;

    public Community(List<Building> buildingList) {
        this.buildingList = buildingList;
    }

    public List<Building> getBuildingList() {
        return buildingList;
    }

    public void setBuildingList(List<Building> buildingList) {
        this.buildingList = buildingList;
    }

    @Override
    public String toString() {
        return "Community{" +
                "buildingList=" + buildingList +
                '}';
    }
}
