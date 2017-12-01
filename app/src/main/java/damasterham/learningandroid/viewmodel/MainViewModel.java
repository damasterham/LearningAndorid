package damasterham.learningandroid.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;
import java.util.concurrent.Executor;

import javax.annotation.Nonnull;

import damasterham.learningandroid.App;
import damasterham.learningandroid.data.AppDb;
import damasterham.learningandroid.data.dao.DudeDao;
import damasterham.learningandroid.data.entitiy.Dude;

public class MainViewModel extends AndroidViewModel
{

    AppDb appDb;
    DudeDao dudeDao;
    Executor ex;

    List<Dude> dudes;

    public MainViewModel(@NonNull Application application)
    {
        super(application);
    }

    public void initialize()
    {
        appDb = getApp().getAppDb();
        dudeDao = appDb.dudeDao();
        ex = getApp().getExecutor();

        // Get dudes
        ex.execute(new Runnable()
        {
            @Override
            public void run()
            {
                dudes = dudeDao.getAll();

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
        });
    }

    private App getApp()
    {
        return (App)getApplication();
    }

    public LiveData<List<Dude>> getDudes()
    {
        return dudes;
    }
}
