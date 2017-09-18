package kmitl.lab03.jirapinya58070014.simplemydot;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import kmitl.lab03.jirapinya58070014.simplemydot.fragment.DotFragment;
import kmitl.lab03.jirapinya58070014.simplemydot.fragment.EditDotFragment;
import kmitl.lab03.jirapinya58070014.simplemydot.model.Dot;
import kmitl.lab03.jirapinya58070014.simplemydot.model.Dots;

public class MainActivity extends AppCompatActivity
        implements DotFragment.DotFragmentListener{

//    private final int WRITE_EXTERNAL_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            initialFragment();
        }
    }

    private void initialFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, DotFragment.newInstance())
                .commit();
    }

    private void viewFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.EditDot_fragmentContainer, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void DotLongPressSelected(Dot dot, Dots dots, int dotPosition) {
        viewFragment(EditDotFragment.newInstance(dot, dots, dotPosition));
    }


//    //ClickShare
//    public void onClickShare(View view) {
//        //Permission
//        if (askPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, WRITE_EXTERNAL_REQUEST_CODE)) {
//            //Screenshot
//            Bitmap image = Screenshot.takescreenshotOfRootView(imageView);
//            Uri screenshotUri = Screenshot.getImageUri(this.getApplicationContext(), image);
//
//            //Share
//            createShareIntent(screenshotUri);
//        }
//    }
//
//    //--- Click on Dot ---//
//    @Override
//    public void onDotViewPressed(final int x, final int y) {
//        int dotPosition = dots.findDot(x, y);  //get index in List
//        int r = ((int) (Math.random() * 60)) + 20;
//
//        //Don't have dot
//        if (dotPosition == -1) {
//            Dot newDot = new Dot(x, y, r, new Colors().getColor());
//            dots.addDot(newDot);
//
//            //Have dot
//        } else {
//            dots.editDot(dotPosition, r, this);
//        }
//    }
//
//    //------- Permission ------//
//    private boolean askPermission(String permission, int requestCode) {
//        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
//            return false;
//        } else {
//            Toast.makeText(this, "Permission is Already Granted", Toast.LENGTH_SHORT).show();
//            return true;
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        switch (requestCode) {
//            case WRITE_EXTERNAL_REQUEST_CODE:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    Toast.makeText(this, "WRITE_EXTERNAL Permission Granted", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(this, "WRITE_EXTERNAL Permission Denied", Toast.LENGTH_SHORT).show();
//                }
//                return;
//        }
//    }
}
