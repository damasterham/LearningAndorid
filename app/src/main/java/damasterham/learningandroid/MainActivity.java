package damasterham.learningandroid;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.text.Layout;
import android.util.Log;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.Executor;

import damasterham.learningandroid.data.AppDb;
import damasterham.learningandroid.data.dao.DudeDao;
import damasterham.learningandroid.data.entitiy.Dude;
import damasterham.learningandroid.utility.ThreadExecutor;

public class MainActivity extends AppCompatActivity
{
    private static final String DB_NAME = "sampledb";

    private AppDb mAppDb;
    private DudeDao mDudeDao;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Build DB
        mAppDb = Room.databaseBuilder(this, AppDb.class, DB_NAME).build();
        mDudeDao = mAppDb.dudeDao();

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                // Initialize some dudes
                if (mDudeDao.getAll().size() == 0)
                {
                    mDudeDao.insertAll(new Dude("Bente", "SuperBente"),
                            new Dude("Jens", "HammerJens"),
                            new Dude("Erik", "ErikVonPopidan"));
                    //Log.d("Dudes created", "onCreate: added " + mDudeDao.getAll().toString());
                }
            }
        }).start();

    }

    @Override
    protected void onStart()
    {
        super.onStart();

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                final List<Dude> dudes = mDudeDao.getAll();

                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        TextView tv = findViewById(R.id.hellotext);
                        tv.setText("");

                        for (Dude dude: dudes)
                        {
                            tv.append(dude.getName());
                            tv.append("\n");
                            tv.append(dude.getNickName());
                            tv.append("\n\n");
                        }
                    }
                });


            }
        }).start();
    }

}


