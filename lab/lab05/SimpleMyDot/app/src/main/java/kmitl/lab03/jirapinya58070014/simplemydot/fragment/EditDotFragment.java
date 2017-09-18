package kmitl.lab03.jirapinya58070014.simplemydot.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import net.margaritov.preference.colorpicker.ColorPickerDialog;

import kmitl.lab03.jirapinya58070014.simplemydot.R;
import kmitl.lab03.jirapinya58070014.simplemydot.model.Dot;
import kmitl.lab03.jirapinya58070014.simplemydot.model.Dots;

public class EditDotFragment extends Fragment implements View.OnClickListener{

    private static final String DOT = "dot";
    private static final String DOTS = "dots";
    private static final String POSITION = "position";

    private Button Save;
    private Button Cancel;
    private Dot dot;
    private Dots dots;
    private int dotPosition;
    private EditText CenterX;
    private EditText CenterY;
    private EditText Radius;
    private View ColorBar;
    private int color;
    private ColorPickerDialog colorPickerDialog;

    public static EditDotFragment newInstance(Dot dot, Dots dots, int dotPosition) {
        EditDotFragment fragment = new EditDotFragment();
        Bundle args = new Bundle();
        args.putParcelable(DOT, dot);
        args.putParcelable(DOTS, dots);
        args.putInt(POSITION, dotPosition);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_edit_dot, container, false);

        dot = getArguments().getParcelable(DOT);
        dots = getArguments().getParcelable(DOTS);
        dotPosition = getArguments().getInt(POSITION);

        CenterX = (EditText) rootView.findViewById(R.id.centerX);
        CenterY = (EditText) rootView.findViewById(R.id.centerY);
        Radius = (EditText) rootView.findViewById(R.id.radius);

        ColorBar = rootView.findViewById(R.id.colorBar);
        Save = (Button) rootView.findViewById(R.id.saveBtn);
        Cancel = (Button) rootView.findViewById(R.id.cancelBtn);

        ColorBar.setOnClickListener(this);
        Save.setOnClickListener(this);
        Cancel.setOnClickListener(this);

        if (dot != null) {
            CenterX.setText(String.valueOf(dot.getCenterX()));
            CenterY.setText(String.valueOf(dot.getCenterY()));
            Radius.setText(String.valueOf(dot.getRadius()));
            ColorBar.setBackgroundColor(dot.getColor());

        }
        return rootView;}

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cancelBtn:
                getActivity().onBackPressed();
                break;
            case R.id.saveBtn:
                save();
                getActivity().onBackPressed();
                break;
            case R.id.colorBar:
                color = dot.getColor();
                colorPickerDialog = new ColorPickerDialog(getContext(), color);
                colorPickerDialog.setAlphaSliderVisible(true);
                colorPickerDialog.setHexValueEnabled(true);
                colorPickerDialog.setOnColorChangedListener(new ColorPickerDialog.OnColorChangedListener() {
                    @Override
                    public void onColorChanged(int newColor) {
                        color = newColor;
                        ColorBar.setBackgroundColor(newColor);
                    }
                });
                colorPickerDialog.show();
                break;
        }
    }

    private void save() {
        dot.setCenterX(Integer.parseInt(String.valueOf(CenterX.getText())));
        dot.setCenterY(Integer.parseInt(String.valueOf(CenterY.getText())));
        dot.setRadius(Integer.parseInt(String.valueOf(Radius.getText())));
        dot.setColor(color);
        dots.editAttributeDot(dotPosition, dot);
    }
}


