package kmitl.lab03.jirapinya58070014.simplemydot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import kmitl.lab03.jirapinya58070014.simplemydot.model.DotParcelable;
import kmitl.lab03.jirapinya58070014.simplemydot.model.DotSerializable;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView tvValueX = (TextView) findViewById(R.id.tvValueX);
        TextView tvDot = (TextView) findViewById(R.id.tvDot);
        int x = getIntent().getIntExtra("xValue", 0);
        DotSerializable dotSerializable = (DotSerializable) getIntent().getSerializableExtra("dotSerializable");
        tvValueX.setText(String.valueOf(x));

//        tvDot.setText("centerX : " + dotSerializable.getCenterX() +
//                "centerY :" + dotSerializable.getCenterY() +
//                "Radius : " + dotSerializable.getRadius());
        tvDot.setText(dotSerializable.toString());

        tvDot.setTextColor(dotSerializable.getColor());

        DotParcelable dotParcelable = getIntent().getParcelableExtra("dotParcelable");
        tvDot.setText("CenterX : " + dotParcelable.getCenterX() + "\nCenterY : " + dotParcelable.getCenterY() + "\nRadius : " + dotParcelable.getRadius());
    }
}
