package db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DaoFile {
    @Insert
    public void insertRecord(EntityFile entityFile);

    @Delete
    public void deleteRecord(EntityFile entityFile);

    @Query("SELECT * FROM EntityFile")
    public List<EntityFile> getList();

    @Update
    void updateRecord(EntityFile entityFile);

    @Query("SELECT * FROM EntityFile WHERE username =:user AND password =:pass")
    EntityFile getUser(String user,String pass);
}
