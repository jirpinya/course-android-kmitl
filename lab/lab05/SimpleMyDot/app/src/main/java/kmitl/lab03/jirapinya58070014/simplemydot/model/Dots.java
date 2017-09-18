package kmitl.lab03.jirapinya58070014.simplemydot.model;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;

public class Dots implements Parcelable {


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {}

    public interface OnDotsChangeListener {void onDotsChanged(Dots dots);}

    private OnDotsChangeListener listener;

    public void setListener(OnDotsChangeListener listener) {
        this.listener = listener;
    }

    private List<Dot> allDot = new ArrayList<>();

    public List<Dot> getAllDot() {
        return allDot;
    }

    public void addDot(Dot dot) {
        this.allDot.add(dot);
        this.listener.onDotsChanged(this);
    }

    public void removeBy(int position) {
        allDot.remove(position);
        this.listener.onDotsChanged(this);
    }

    public void clearAll() {
        allDot.clear();
        this.listener.onDotsChanged(this);
    }

    public void undoDot() {
        if(this.allDot.size()!=0) {
            allDot.remove(this.allDot.size() - 1);
            this.listener.onDotsChanged(this);
        }
    }

    public void changeColor(int index) {
        int newColor = new Colors().getColor();
        while (getColorDot(index) == newColor) {
            newColor = new Colors().getColor();
        }
        allDot.get(index).setColor(newColor);
        this.listener.onDotsChanged(this);
    }

    public void changeSize(int index, int r) {
        allDot.get(index).setRadius(r);
        this.listener.onDotsChanged(this);
    }

    public int getColorDot(int index) {
        return allDot.get(index).getColor();
    }

    public int findDot(int x, int y) {
        for (int index = 0; index < allDot.size(); index++) {
            int centerX = allDot.get(index).getCenterX();
            int centerY = allDot.get(index).getCenterY();
            double distance = Math.sqrt(Math.pow(centerX - x, 2)) +
                    Math.sqrt(Math.pow(centerY - y, 2));
            if (distance <= allDot.get(index).getRadius()) {
                return index;
            }
        }
        return -1;
    }

    public  void editAttributeDot(int position, Dot dot){
        allDot.set(position, dot);
        this.listener.onDotsChanged(this);
    }

    public void editDot(final int index, final int r, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setItems(new CharSequence[]{"Change Colors", "Change Size", "Delete"},
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                changeColor(index);
                                dialog.dismiss();
                                break;
                            case 1:
                                changeSize(index, r);
                                dialog.dismiss();
                                break;
                            case 2:
                                removeBy(index);
                                dialog.dismiss();
                                break;
                        }
                    }
                });
        builder.show();
    }
}
