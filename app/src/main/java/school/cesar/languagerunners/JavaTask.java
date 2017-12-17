package school.cesar.languagerunners;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Locale;

public class JavaTask extends AsyncTask<Integer, Integer, Long> {

    private ProgressBar progressBar;
    private TextView txvTime;
    private Timer timer;

    public JavaTask(ProgressBar progressBar, TextView txvTime, Timer timer) {

        this.progressBar = progressBar;
        this.txvTime = txvTime;
        this.timer = timer;

    }

    @Override
    protected void onPreExecute() {

        progressBar.setProgress(0);
        txvTime.setVisibility(View.GONE);

    }

    @Override
    protected Long doInBackground(Integer... distances) {

        int distance = distances.length > 0 ? distances[0] : MainActivity.DEFAULT_DISTANCE;

        progressBar.setMax(distance);
        long sum = 0;

        for (int x = 1; x <= distance; x++) {
            for (int y = 1; y <= distance; y++) {
                for (int z = 1; z <= distance; z++) {

                    if (!timer.isRunning())
                        return sum;

                    sum++;

                }
            }
            publishProgress(1);
        }

        return sum;

    }

    @Override
    protected void onProgressUpdate(Integer... progress) {

        progressBar.setProgress(progressBar.getProgress() + progress[0]);

    }

    @Override
    protected void onPostExecute(Long aLong) {

        txvTime.setText(String.format(Locale.getDefault(), "%02d:%02d:%02d", timer.getHours(), timer.getMinutes(), timer.getSeconds()));
        txvTime.setVisibility(View.VISIBLE);

    }

}
