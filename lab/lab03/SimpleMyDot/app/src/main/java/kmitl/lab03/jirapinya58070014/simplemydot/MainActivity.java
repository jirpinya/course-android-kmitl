package kmitl.lab03.jirapinya58070014.simplemydot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

import kmitl.lab03.jirapinya58070014.simplemydot.model.Dot;
import kmitl.lab03.jirapinya58070014.simplemydot.view.DotView;

import static android.R.attr.centerX;

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

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            float x =  e.getX();
            float y =  e.getY()-200;
            CreateDot(x, y);
        }
        return true;
    }

    public void CreateDot(float x, float y){
        //Radius
        int r = ((int)(Math.random()*80))+20;

        //RandomColor
        Random random = new Random();
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);

        dot = new Dot (this, x, y, r);
        dot.setColor(red, green, blue);
        dot.setCenterX(x);
        dot.setCenterY(y);
        dot.setRadius(r);

        allDot.add(dot);
    }

    //Click Random
    public void onRandomDot(View view) {
        Random random = new Random();

        //Position & Radius
        int r = ((int)(Math.random()*80))+20;
        int centerX = random.nextInt(this.dotView.getWidth());
        int centerY = random.nextInt(this.dotView.getHeight());
        CreateDot(centerX, centerY);

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
