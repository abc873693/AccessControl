package rainvisitor.accesscontrol.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ray on 2017/7/16.
 *
 */

public class Room {
    @SerializedName("title")
    private String title;
    private boolean clicked = false;

    public Room() {
    }

    public Room(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    @Override
    public String toString() {
        return "Room{" +
                "title='" + title + '\'' +
                '}';
    }
}
