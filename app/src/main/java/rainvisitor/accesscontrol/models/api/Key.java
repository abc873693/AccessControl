package rainvisitor.accesscontrol.models.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ray on 2017/8/8.
 */

public class Key {

    @SerializedName("key")
    private String Key;

    @SerializedName("ciTime")
    private String StartTime;

    @SerializedName("coTime")
    private String EndTime;

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    @Override
    public String toString() {
        return "Key{" +
                "Key='" + Key + '\'' +
                ", StartTime='" + StartTime + '\'' +
                ", EndTime='" + EndTime + '\'' +
                '}';
    }
}
