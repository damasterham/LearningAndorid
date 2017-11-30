package damasterham.learningandroid.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import damasterham.learningandroid.data.dao.DudeDao;
import damasterham.learningandroid.data.entitiy.Dude;

@Database(entities = {Dude.class},
version = 1)
public abstract class AppDb extends RoomDatabase
{
    public abstract DudeDao dudeDao();
}