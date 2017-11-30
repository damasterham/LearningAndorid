package damasterham.learningandroid.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import damasterham.learningandroid.data.entitiy.Dude;

@Dao
public interface DudeDao
{
    @Query("SELECT * FROM dude")
    List<Dude> getAll();

    @Query("SELECT * FROM dude WHERE id IN (:userIds)")
    List<Dude> loadAllByIds(long[] userIds);

    @Query("SELECT * FROM dude WHERE id = :userId")
    Dude findById(long id);

    @Query("SELECT * FROM dude WHERE name LIKE :name")
    Dude findByName(String name);

    @Insert
    void insertAll(Dude... dudes);

    @Insert
    void insert(Dude dude);

    @Delete
    void delete(Dude dude);

    @Delete
    void deleteAll();
}
