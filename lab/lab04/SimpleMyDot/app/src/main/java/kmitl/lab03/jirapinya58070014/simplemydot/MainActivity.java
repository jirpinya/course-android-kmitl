package kmitl.lab03.jirapinya58070014.simplemydot;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.Manifest;

import java.util.Random;

import kmitl.lab03.jirapinya58070014.simplemydot.model.Colors;
import kmitl.lab03.jirapinya58070014.simplemydot.model.Dot;
import kmitl.lab03.jirapinya58070014.simplemydot.model.Dots;
import kmitl.lab03.jirapinya58070014.simplemydot.view.DotView;
import kmitl.lab03.jirapinya58070014.simplemydot.model.Screenshot;

public class MainActivity extends AppCompatActivity
        implements Dots.OnDotsChangeListener, DotView.OnDotViewPressListener {

    private DotView dotView;
    private Dots dots;
    private ImageView imageView;
    private final int WRITE_EXTERNAL_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
        dotView = (DotView) findViewById(R.id.dotView);
        dotView.setOnDotViewPressListener(this);

        dots = new Dots();
        dots.setListener(this);

        //--- Click Share ---//
        Button share = (Button) findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                askPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,WRITE_EXTERNAL_REQUEST_CODE);

                    //Screenshot
                    Bitmap image = Screenshot.takescreenshotOfRootView(imageView);
                    Uri uriImage = Screenshot.saveBitmap(image);

                    //Share
                    startActivity(Intent.createChooser(createShareIntent(uriImage), " How do you want to share? "));

            }
        });
    }

    public void onRandomDot(View view) {
        Random random = new Random();
        int centerX = random.nextInt(dotView.getWidth());
        int centerY = random.nextInt(dotView.getHeight());
        int r = ((int) (Math.random() * 60)) + 20;
        Dot newDot = new Dot(centerX, centerY, r, new Colors().getColor());
        dots.addDot(newDot);
    }

    @Override
    public void onDotsChanged(Dots dots) {
        dotView.setDots(dots);
        dotView.invalidate();
    }

    //Share Image
    private Intent createShareIntent(Uri uriImage) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/*");
        shareIntent.putExtra(Intent.EXTRA_STREAM, uriImage);
        return shareIntent;
    }

    public void onRemoveAll(View view) {
        dots.clearAll();
    }

    //--- Click on Dot ---//
    @Override
    public void onDotViewPressed(final int x, final int y) {
        int dotPosition = dots.findDot(x, y);  //get index in List
        int r = ((int) (Math.random() * 60)) + 20;

        //Don't have dot
        if (dotPosition == -1) {
            Dot newDot = new Dot(x, y, r, new Colors().getColor());
            dots.addDot(newDot);

         //Have dot
        } else {
            dots.editDot(dotPosition, r, this);
        }
    }

    private void askPermission(String permission, int requestCode){
        if(ContextCompat.checkSelfPermission(this,permission) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new  String[]{permission}, requestCode);
        }
        else{
            Toast.makeText(this, "Permission is Already Granted", Toast.LENGTH_SHORT).show();
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        switch (requestCode) {
            case WRITE_EXTERNAL_REQUEST_CODE:
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "WRITE_EXTERNAL Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "WRITE_EXTERNAL Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
