package school.cesar.languagerunners;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public static final int DEFAULT_DISTANCE = 10;

    @BindView(R.id.btnStart)
    protected Button btnStart;

    @BindView(R.id.txvTimer)
    protected TextView txvTimer;

    @BindView(R.id.spnDistance)
    protected Spinner spnDistance;

    @BindView(R.id.txvJavaTime)
    protected TextView txvJavaTime;

    @BindView(R.id.progressJava)
    protected ProgressBar progressJava;

    protected JavaTask javaTask;

    private Timer timer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        timer = new Timer();

        timer.setConsumer(this::tickTimer);
        btnStart.setOnClickListener(this::pressStartButton);

    }

    private void pressStartButton(View view) {

        if (!timer.isRunning()) {

            startTimer();

        } else {

            stopTimer();

        }

    }

    private void tickTimer(int hours, int minutes, int seconds, int milliseconds) {

        txvTimer.setText(String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds));

    }

    private void startTimer() {

        timer.reset();
        btnStart.setText(R.string.stop);
        timer.start();

        new JavaTask(progressJava, txvJavaTime, timer).execute(getDistance());

    }

    private void stopTimer() {

        timer.pause();
        btnStart.setText(R.string.restart);

    }

    private int getDistance() {

        try {

            return Integer.parseInt(spnDistance.getSelectedItem().toString());

        } catch (NumberFormatException exception) {

            return DEFAULT_DISTANCE;

        }

    }

}
