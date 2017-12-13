package damasterham.learningandroid.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import javax.annotation.Nonnull;

import damasterham.learningandroid.App;
import damasterham.learningandroid.data.AppDb;
import damasterham.learningandroid.data.dao.DudeDao;
import damasterham.learningandroid.data.entitiy.Dude;

import static android.content.ContentValues.TAG;

public class MainViewModel extends AndroidViewModel
{

    AppDb appDb;
    DudeDao dudeDao;
    Executor ex;
    List<Dude> dudes;

    //private final MediatorLiveData<List<Dude>> dudes;

    public MainViewModel(@NonNull Application application)
    {
        super(application);
        initialize();
//
//          dudes = new MediatorLiveData<>();
//        dudes.setValue(null);nitialize();
    }

    public void initialize()
    {
        appDb = getApp().getAppDb();
        dudeDao = appDb.dudeDao();
        ex = getApp().getExecutor();

        // Get dudes
        ex.execute(new Runnable(){

            @Override
            public void run()
            {

                final List<Dude> tempDudes = dudeDao.getAll();
                // If none, initialize som data
                if (tempDudes == null || tempDudes.size() == 0)
                {
                    dudeDao.insertAll(new Dude("Bente", "SuperBente"),
                                    new Dude("Jens", "HammerJens"),
                                    new Dude("Erik", "ErikVonPopidan"));
                            //Log.d("Dudes created", "onCreate: added " + mDudeDao.getAll().toString());
                    dudes = dudeDao.getAll();
                }
                else
                    dudes = tempDudes;
            }
        });
    }

    private App getApp()
    {
        return (App)getApplication();
    }

    public List<Dude> getDudes()
    {
        return dudes;
    }
}
