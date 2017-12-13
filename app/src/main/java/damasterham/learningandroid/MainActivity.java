package damasterham.learningandroid;

import android.arch.lifecycle.ViewModelProviders;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import damasterham.learningandroid.data.dao.DudeDao;
import damasterham.learningandroid.data.entitiy.Dude;
import damasterham.learningandroid.rest.RestService;
import damasterham.learningandroid.rest.entities.Info;
import damasterham.learningandroid.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity
{
    //Deprecated
    MainViewModel viewModel;

    private App app;
    private DudeDao dudeDao;
    private RestService restService;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Deprecated
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        app = (App)getApplication();
        dudeDao = app.getAppDb().dudeDao();
        restService = app.getRestService();

    }

    @Override
    protected void onStart()
    {
        super.onStart();

        // Gets Dudes from local Database and binds to UI using runOnUiThread
//        final TextView tv = findViewById(R.id.hellotext);
//        tv.setText("");
//
//        final List<Dude> dudes = viewModel.getDudes();
//
//        runOnUiThread(new Runnable()
//        {
//            @Override
//            public void run()
//            {
//                for (Dude dude: dudes)
//                {
//                    tv.append(dude.getName());
//                    tv.append("\n");
//                    tv.append(dude.getNickName());
//                    tv.append("\n\n");
//                }
//            }
//        });

        new GetDudesLocalData().execute();

        // Gets and binds a runner info with id 22
        new GetRunnerInfoRest().execute((long)22);


    }

    private class GetDudesLocalData extends AsyncTask<Void, Void, List<Dude>>
    {
        @Override
        protected List<Dude> doInBackground(Void... voids)
        {
            return dudeDao.getAll();
        }

        @Override
        protected void onPostExecute(List<Dude> dudes)
        {
            super.onPostExecute(dudes);

            final TextView tv = findViewById(R.id.hellotext);
            tv.setText("");
            for (Dude dude: dudes)
                {
                    tv.append(dude.getName());
                    tv.append("\n");
                    tv.append(dude.getNickName());
                    tv.append("\n\n");
                }
        }
    }

    private class GetRunnerInfoRest extends AsyncTask<Long, Void, Info>
    {
        @Override
        protected Info doInBackground(Long... longs)
        {
            long id = longs[0];

            return restService.getRunnerInfo(id);
        }

        @Override
        protected void onPostExecute(Info info)
        {
            super.onPostExecute(info);

            final TextView tv = findViewById(R.id.textViewRestValue);
            tv.setText(info.toString());
        }
    }
}


