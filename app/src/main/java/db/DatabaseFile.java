package db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {EntityFile.class},version = 1, exportSchema = false)
public abstract class DatabaseFile extends RoomDatabase {
    public abstract DaoFile dao();
    private static DatabaseFile database;
    public static synchronized DatabaseFile getDatabase(Context context){
        if(database == null){
            database = Room.databaseBuilder(context, DatabaseFile.class,"database-storage")
                    .fallbackToDestructiveMigration()
                    .build();
            return database;
        }
        else
            return database;
    }
}
