package rainvisitor.accesscontrol.models.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ray on 2017/7/24.
 */

public class Check_key_response {

    @SerializedName("status")
    private String Status;

    @SerializedName("message")
    private String Message;

    public Check_key_response() {
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    @Override
    public String toString() {
        return "Check_key_response{" +
                "Status='" + Status + '\'' +
                ", Message='" + Message + '\'' +
                '}';
    }
}
