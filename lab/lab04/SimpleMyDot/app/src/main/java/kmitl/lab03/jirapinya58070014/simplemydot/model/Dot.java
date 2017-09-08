package kmitl.lab03.jirapinya58070014.simplemydot.model;

public class Dot {

    public interface OnDotChangedListener {
        void onDotChanged(Dot dot);
    }

    private OnDotChangedListener listener;

    public void setListener(OnDotChangedListener listener) {
        this.listener = listener;
    }

    private float centerX;
    private float centerY;
    private int radius;
    private int r, g, b;

    public Dot(OnDotChangedListener listener, float centerX, float centerY, int radius) {
        this.listener = listener;
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }

    //Color
    public void setColor(int r, int g, int b){
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public int getColorRed() {return r;}

    public int getColorGreen() {return g;}

    public int getColorBlue() {return b;}


    //Position
    public void setCenterX(float centerX) {
        this.centerX = centerX;
        this.listener.onDotChanged(this);   //this=dot
    }

    public void setCenterY(float centerY) {
        this.centerY = centerY;
        this.listener.onDotChanged(this);
    }

    public float getCenterX() {
        return centerX;
    }

    public float getCenterY() {
        return centerY;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
