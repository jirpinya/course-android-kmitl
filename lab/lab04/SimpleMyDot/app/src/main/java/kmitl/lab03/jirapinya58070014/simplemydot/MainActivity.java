package kmitl.lab03.jirapinya58070014.simplemydot;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

import kmitl.lab03.jirapinya58070014.simplemydot.model.Colors;
import kmitl.lab03.jirapinya58070014.simplemydot.model.Dot;
import kmitl.lab03.jirapinya58070014.simplemydot.model.Dots;
import kmitl.lab03.jirapinya58070014.simplemydot.view.DotView;
import kmitl.lab03.jirapinya58070014.simplemydot.view.Screenshot;


public class MainActivity extends AppCompatActivity
implements Dots.OnDotsChangeListener, DotView.OnDotViewPressListener{

    private DotView dotView;
    private Dots dots;
    private View main;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main = findViewById(R.id.main);
        imageView = (ImageView) findViewById(R.id.imageView);
        dotView = (DotView) findViewById(R.id.dotView);
        dotView.setOnDotViewPressListener(this);

        dots = new Dots();
        dots.setListener(this);

        //Click Share
        Button share = (Button) findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap b = Screenshot.takescreenshotOfRootView(imageView);
                imageView.setImageBitmap(b);
                main.setBackgroundColor(Color.parseColor("#999999"));
                Screenshot pic = new Screenshot();
                pic.saveBitmap(b);
                pic.shareIt();

            }
        });

    }

    public void onRandomDot(View view) {
        Random random = new Random();
        int centerX = random.nextInt(dotView.getWidth());
        int centerY = random.nextInt(dotView.getHeight());
        Dot newDot = new Dot(centerX, centerY, 30, new Colors().getColor());
        dots.addDot(newDot);
    }

    @Override
    public void onDotsChanged(Dots dots) {
        dotView.setDots(dots);
        dotView.invalidate();
    }

    public void onRemoveAll(View view) {
        dots.clearAll();
    }

    @Override
    public void onDotViewPressed(int x, int y) {
        int dotPosition = dots.findDot(x, y);
        if(dotPosition == -1) {
            Dot newDot = new Dot(x, y, 30, new Colors().getColor());
            dots.addDot(newDot);
        } else {
            dots.removeBy(dotPosition);
        }
    }

    public void share(View view) {
    }
}
