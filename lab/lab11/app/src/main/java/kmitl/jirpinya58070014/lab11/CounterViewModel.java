package kmitl.jirpinya58070014.lab11;

import android.arch.lifecycle.ViewModel;
import android.view.View;


public class CounterViewModel extends ViewModel {
    private int counter;

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
