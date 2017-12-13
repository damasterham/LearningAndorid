package damasterham.learningandroid.utility;

import android.support.annotation.NonNull;

import java.util.concurrent.Executor;

public class ThreadExecutor implements Executor
{
    public void execute(Runnable r)
    {
        new Thread(r).start();
    }


//    @Override
//    public void execute(@NonNull Runnable runnable)
//    {
//        runnable.run();
//    }
}
