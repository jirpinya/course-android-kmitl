package kmitl.jirpinya58070014.lab11;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CounterViewModel viewModel;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(CounterViewModel.class);
        result = findViewById(R.id.result);
        displayCounter();
    }


    private void displayCounter() {
        result.setText(String.valueOf(viewModel.getCounter()));
    }

    public void clickMe(View view) {
        viewModel.setCounter(viewModel.getCounter()+1);
        displayCounter();
    }

}
