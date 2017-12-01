package damasterham.learningandroid.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import damasterham.learningandroid.data.entitiy.Dude;

@Dao
public interface DudeDao
{
    String GET_ALL = "SELECT * FROM dude";
    String LOAD_ALL_BY_IDS = "SELECT * FROM dude WHERE id IN (:ids)";

    @Query(GET_ALL)
    LiveData<List<Dude>> getAllAsLiveData();

    @Query(GET_ALL)
    List<Dude> getAll();

    @Query(LOAD_ALL_BY_IDS)
    LiveData<List<Dude>> loadAllByIdsAsLiveData(long[] ids);

    @Query(LOAD_ALL_BY_IDS)
    List<Dude> loadAllByIds(long[] ids);


    @Query("SELECT * FROM dude WHERE id = :id")
    Dude findById(long id);

    @Query("SELECT * FROM dude WHERE name LIKE :name")
    Dude findByName(String name);

    @Insert
    void insertAll(Dude... dudes);

    @Insert
    void insert(Dude dude);

    @Delete
    void deleteByIds(Dude... dudes);

    @Delete
    void delete(Dude dude);
}
