package damasterham.learningandroid;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Intent;

import java.util.concurrent.Executor;

import damasterham.learningandroid.data.AppDb;
import damasterham.learningandroid.utility.ThreadExecutor;

public class App extends Application
{
    private static final String DB_NAME = "sampledb";

    private AppDb appDb;
    private Executor executor;

    public App()
    {
        appDb = Room.databaseBuilder(this, AppDb.class, DB_NAME).build();
        executor = new ThreadExecutor();

//        Intent start = new Intent(this, MainActivity.class);
//        startActivity(start);
    }

    public AppDb getAppDb()
    {
        return appDb;
    }

    public Executor getExecutor()
    {
        return executor;
    }
}
