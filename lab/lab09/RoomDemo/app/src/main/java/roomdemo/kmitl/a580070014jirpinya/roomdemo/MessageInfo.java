package roomdemo.kmitl.a580070014jirpinya.roomdemo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "MESSAGE_INFO")

class MessageInfo {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "TEXT")
    private String text;

    @ColumnInfo(name = "TIME")
    private String time;

    @Override
    public String toString(){
        return String.format("%s :: %s", text, time);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
