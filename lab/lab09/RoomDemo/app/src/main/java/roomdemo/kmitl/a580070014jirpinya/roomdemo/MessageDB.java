package roomdemo.kmitl.a580070014jirpinya.roomdemo;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {MessageInfo.class}, version = 1)

public abstract class MessageDB extends RoomDatabase {
    public abstract MessageInfoDAO getMessageInfoDAO();
}
