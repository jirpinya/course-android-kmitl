package kmitl.lab03.jirapinya58070014.simplemydot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

import kmitl.lab03.jirapinya58070014.simplemydot.model.Dot;
import kmitl.lab03.jirapinya58070014.simplemydot.view.DotView;

public class MainActivity extends AppCompatActivity implements Dot.OnDotChangedListener {

    private DotView dotView;
    private Dot dot;
    private ArrayList<Dot> allDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dotView = (DotView) findViewById(R.id.dotView);

        dot = new Dot (this, 0,0,30);
        allDot = new ArrayList<>();
    }

    //Click Random
    public void onRandomDot(View view) {

        Random random = new Random();

        //Position & Radius
        int r = ((int)(Math.random()*100))+20;
        int centerX = random.nextInt(this.dotView.getWidth());
        int centerY = random.nextInt(this.dotView.getHeight());

        dot = new Dot (this, centerX, centerY, r);

        dot.setCenterX(centerX);
        dot.setCenterY(centerY);
        dot.setRadius(r);

        //RandomColor
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        dot.setColor(red, green, blue);
        allDot.add(dot);

    }

    //Click ClearAll
    public void clearAll(View view) {
        allDot.clear();
        dotView.invalidate();  //update หน้าจอ
    }

    @Override
    public void onDotChanged(Dot dot) {
        //Draw dot model to view
        dotView.setAllDot(allDot);
        dotView.invalidate();


    }

}
