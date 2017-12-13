package damasterham.learningandroid;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.support.annotation.Nullable;
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
import damasterham.learningandroid.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity
{
    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        final TextView tv = findViewById(R.id.hellotext);
        tv.setText("");

        final List<Dude> dudes = viewModel.getDudes();

        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
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
}


