package rainvisitor.accesscontrol.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ray on 2017/7/16.
 *
 */

public class Building {
    @SerializedName("title")
    private String title;
    @SerializedName("roomList")
    private List<Room> roomList;
    private boolean clicked;

    public Building() {
    }

    public Building(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    @Override
    public String toString() {
        return "Building{" +
                "title='" + title + '\'' +
                ", roomList=" + roomList +
                '}';
    }
}
