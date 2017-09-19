package kmitl.lab03.jirapinya58070014.simplemydot;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import kmitl.lab03.jirapinya58070014.simplemydot.fragment.DotFragment;
import kmitl.lab03.jirapinya58070014.simplemydot.fragment.EditDotFragment;
import kmitl.lab03.jirapinya58070014.simplemydot.model.Dot;
import kmitl.lab03.jirapinya58070014.simplemydot.model.Dots;

public class MainActivity extends AppCompatActivity
        implements DotFragment.DotFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            initialFragment();
        }
    }

    private void initialFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, DotFragment.newInstance())
                .commit();
    }

    private void viewFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.EditDot_fragmentContainer, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void DotLongPressSelected(Dot dot, Dots dots, int dotPosition) {
        viewFragment(EditDotFragment.newInstance(dot, dots, dotPosition));
    }
}
