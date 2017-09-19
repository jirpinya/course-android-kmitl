package kmitl.lab03.jirapinya58070014.simplemydot.fragment;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

import kmitl.lab03.jirapinya58070014.simplemydot.R;
import kmitl.lab03.jirapinya58070014.simplemydot.model.Colors;
import kmitl.lab03.jirapinya58070014.simplemydot.model.Dot;
import kmitl.lab03.jirapinya58070014.simplemydot.model.Dots;
import kmitl.lab03.jirapinya58070014.simplemydot.model.Screenshot;
import kmitl.lab03.jirapinya58070014.simplemydot.view.DotView;


public class DotFragment extends Fragment implements Dots.OnDotsChangeListener,
        DotView.OnDotViewPressListener, View.OnClickListener {

    private DotView dotView;
    private static Dots dots;
    private ImageView imageView;
    private final int WRITE_EXTERNAL_REQUEST_CODE = 2;

    public static DotFragment newInstance() {
        DotFragment fragment = new DotFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dot, container, false);
        imageView = (ImageView) rootView.findViewById(R.id.imageView);
        dotView = (DotView) rootView.findViewById(R.id.dotView);

        dotView.setOnDotViewPressListener(this);

        dots = new Dots();
        dots.setListener(this);

        Button btnRandomDot = (Button) rootView.findViewById(R.id.random);
        Button btnClearDot = (Button) rootView.findViewById(R.id.removeAll);
        Button btnShare = (Button) rootView.findViewById(R.id.share);
        Button btnUndo = (Button) rootView.findViewById(R.id.undo);

        btnRandomDot.setOnClickListener(this);
        btnClearDot.setOnClickListener(this);
        btnShare.setOnClickListener(this);
        btnUndo.setOnClickListener(this);

        return rootView;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.random:
                RandomDot();
                break;
            case R.id.removeAll:
                RemoveAll();
                break;
            case R.id.share:
                ClickShare();
                break;
            case R.id.undo:
                ClickUndo();
                break;
        }
    }

    public void RandomDot() {
        Random random = new Random();
        int centerX = random.nextInt(dotView.getWidth());
        int centerY = random.nextInt(dotView.getHeight());
        int r = ((int) (Math.random() * 60)) + 20;
        Dot newDot = new Dot(centerX, centerY, r, new Colors().getColor());
        dots.addDot(newDot);
    }


    public void onDotsChanged(Dots dots) {
        dotView.setDots(dots);
        dotView.invalidate();
    }

    //Share Image
    private void createShareIntent(Uri uriImage) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/*");
        shareIntent.putExtra(Intent.EXTRA_STREAM, uriImage);
        try {
            startActivity(Intent.createChooser(shareIntent, " How do you want to share? "));
        } catch (ActivityNotFoundException e) {
        }
    }


    //Click Remove
    public void RemoveAll() {
        dots.clearAll();
    }

    //ClickUndo
    public void ClickUndo() {
        dots.undoDot();
    }

    //ClickShare
    public void ClickShare() {
        //Permission
        if (askPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, WRITE_EXTERNAL_REQUEST_CODE)) {
            //Screenshot
            Bitmap image = Screenshot.takescreenshotOfRootView(imageView);
            Uri screenshotUri = Screenshot.getImageUri(this.getContext(), image);

            //Share
            createShareIntent(screenshotUri);
        }
    }

    //--- Click on Dot ---//
    @Override
    public void onDotViewPressed(final int x, final int y) {
        final int dotPosition = dots.findDot(x, y);  //get index in List
        final int r = ((int) (Math.random() * 60)) + 20;
        //Don't have dot
        if (dotPosition == -1) {
            Dot newDot = new Dot(x, y, r, new Colors().getColor());
            dots.addDot(newDot);

            //Have dot
        } else {
            dots.removeBy(dotPosition);
        }
    }

    //--- LongPass on Dot ---//
    @Override
    public void onDotViewLongPressed(int x, int y) {
        final int dotPosition = dots.findDot(x, y);

        //Have Dot
        if (dotPosition != -1) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

            builder.setItems(new CharSequence[]{" Edit Dot", " Delete"},
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case 0:
                                    DotFragmentListener listener = getDotFragmentListener();
                                    Dot dot = dots.getAllDot().get(dotPosition);
                                    listener.DotLongPressSelected(dot, dots, dotPosition);
                                    dialog.dismiss();
                                    break;
                                case 1:
                                    dots.removeBy(dotPosition);
                                    dialog.dismiss();
                                    break;
                            }
                        }
                    });
            builder.show();
        }

    }

    public interface DotFragmentListener {
        void DotLongPressSelected(Dot dot, Dots dots, int dotPosition);
    }

    private DotFragmentListener getDotFragmentListener() {
        Fragment fragment = getParentFragment();
        try {
            if (fragment != null) {
                return (DotFragmentListener) fragment;
            } else {
                return (DotFragmentListener) getActivity();
            }
        } catch (ClassCastException ignored) {
        }
        return null;
    }


    //------- Permission ------//
    private boolean askPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(getActivity(), permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{permission}, requestCode);
            return false;
        } else {
            Toast.makeText(getActivity(), "Permission is Already Granted", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case WRITE_EXTERNAL_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getActivity(), "WRITE_EXTERNAL Permission Granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "WRITE_EXTERNAL Permission Denied", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }

}
