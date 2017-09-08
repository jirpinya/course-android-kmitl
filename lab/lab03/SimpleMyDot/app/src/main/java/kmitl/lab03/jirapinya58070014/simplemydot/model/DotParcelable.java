package kmitl.lab03.jirapinya58070014.simplemydot.model;

import android.os.Parcel;
import android.os.Parcelable;

public class DotParcelable implements Parcelable {
    private int centerX;
    private int centerY;
    private int Radius;

    public DotParcelable(int centerX, int centerY, int radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        Radius = radius;
    }

    protected DotParcelable(Parcel in) {
        centerX = in.readInt();
        centerY = in.readInt();
        Radius = in.readInt();
    }

    //เขียนค่าเข้าParcelable
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(centerX);
        dest.writeInt(centerY);
        dest.writeInt(Radius);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DotParcelable> CREATOR = new Creator<DotParcelable>() {
        @Override
        public DotParcelable createFromParcel(Parcel in) {
            return new DotParcelable(in);
        }

        @Override
        public DotParcelable[] newArray(int size) {
            return new DotParcelable[size];
        }
    };

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public int getRadius() {
        return Radius;
    }

    public void setRadius(int radius) {
        Radius = radius;
    }

    public static Creator<DotParcelable> getCREATOR() {
        return CREATOR;
    }
}
