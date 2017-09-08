package kmitl.lab03.jirapinya58070014.simplemydot.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

import kmitl.lab03.jirapinya58070014.simplemydot.model.Dot;

import static java.lang.Math.random;

public class DotView extends View {

    private Paint paint;
    private Dot dot;
    private ArrayList<Dot> allDot;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(this.allDot != null) {
            for (Dot dot: allDot) {
                if (dot != null) {
                    paint.setColor(Color.rgb(dot.getColorRed(), dot.getColorGreen(), dot.getColorBlue()));   //setColor
                    canvas.drawCircle(dot.getCenterX(), dot.getCenterY(), dot.getRadius(), paint);           //DrawCircle
                }
            }
        }
    }

    public DotView(Context context) {
        super(context);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
    }

    public void setDot(Dot dot) {
        this.dot = dot;
    }

    public void setAllDot(ArrayList<Dot> allDot) {
        this.allDot = allDot;
    }
}
