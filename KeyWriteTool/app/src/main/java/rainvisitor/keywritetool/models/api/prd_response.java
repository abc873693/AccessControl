package rainvisitor.keywritetool.models.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ray on 2017/8/8.
 */

public class prd_response {

    @SerializedName("status")
    private String Status;

    @SerializedName("message")
    private String Message;

    @SerializedName("data")
    private List<Key> KeyList;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public List<Key> getKeyList() {
        return KeyList;
    }

    public void setKeyList(List<Key> keyList) {
        KeyList = keyList;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    @Override
    public String toString() {
        return "prd_response{" +
                "Status='" + Status + '\'' +
                ", Message='" + Message + '\'' +
                ", KeyList=" + KeyList +
                '}';
    }
}
