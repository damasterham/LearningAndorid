package damasterham.learningandroid.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.persistence.room.Room;
import android.content.Context;

import java.util.List;
import java.util.concurrent.Executor;

import damasterham.learningandroid.data.AppDb;
import damasterham.learningandroid.data.dao.DudeDao;
import damasterham.learningandroid.data.entitiy.Dude;
import damasterham.learningandroid.utility.ThreadExecutor;

public class MainViewModel extends ViewModel
{
    private static final String DB_NAME = "sampledb";

    AppDb db;
    DudeDao dudeDao;
    Executor ex;

    LiveData<List<Dude>> dudes;

    public MainViewModel(Context context)
    {
        db = Room.databaseBuilder(context, AppDb.class, DB_NAME).build();
        dudeDao = db.dudeDao();
        ex = new ThreadExecutor();


        // Get dudes
        ex.execute(new Runnable()
        {
            @Override
            public void run()
            {
                dudes = dudeDao.getAll();
            }
        });

        // If none, initialize som data
        if (dudes.getValue().size() == 0)
        {
            ex.execute(new Runnable()
            {
                @Override
                public void run()
                {
                    dudeDao.insertAll(new Dude("Bente", "SuperBente"),
                            new Dude("Jens", "HammerJens"),
                            new Dude("Erik", "ErikVonPopidan"));
                    //Log.d("Dudes created", "onCreate: added " + mDudeDao.getAll().toString());
                }
            });
        }
    }

    public LiveData<List<Dude>> getDudes()
    {
        return dudes;
    }
}
