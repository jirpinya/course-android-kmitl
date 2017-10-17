package jirapinya58070014.kmitl.com.mylazyinstagram.controller;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import jirapinya58070014.kmitl.com.mylazyinstagram.R;
import jirapinya58070014.kmitl.com.mylazyinstagram.adapter.PostAdapter;
import jirapinya58070014.kmitl.com.mylazyinstagram.api.LazyInstagramApi;
import jirapinya58070014.kmitl.com.mylazyinstagram.model.UserProfile;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private boolean gridLayout = true;
    private String user = "nature";
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getUserProfile(user);
        animationLinearLayout();
        showProgressDialog();

        setFollowBtn();
        setViewFormat();
        selectSpinner();
    }

    //--------------------------- Connect Retrofit -------------------------//
    private void getUserProfile(String usrName) {

        OkHttpClient client = new OkHttpClient
                .Builder()
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LazyInstagramApi.BASE)
                .client(client)
                .addConverterFactory(
                        GsonConverterFactory.create())
                .build();

        //สร้างการเชื่อมต่อ = factory
        LazyInstagramApi lazyInstagramApi = retrofit.create(LazyInstagramApi.class);

        Call<UserProfile> call = lazyInstagramApi.getProfile(usrName);
        call.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(@NonNull Call<UserProfile> call, @NonNull Response<UserProfile> response) {
                if (response.isSuccessful()) {
                    if (mProgressDialog.isShowing()) {
                        mProgressDialog.dismiss();
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        animationLinearLayout();
                    }
                    UserProfile userProfile = response.body();
                    display(userProfile, gridLayout);
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserProfile> call, @NonNull Throwable t) {
            }
        });

    }

    //----------------------------- Display ---------------------------//
    private void display(UserProfile userProfile, boolean gridLayout) {

        TextView textPost = (TextView) findViewById(R.id.textPost);
        textPost.setText(Integer.toString(userProfile.getPost()));

        TextView textFollower = (TextView) findViewById(R.id.TextFollower);
        textFollower.setText(Integer.toString(userProfile.getFollower()));

        TextView textFollwing = (TextView) findViewById(R.id.textFollowing);
        textFollwing.setText(Integer.toString(userProfile.getFollowing()));

        TextView textBio = (TextView) findViewById(R.id.textBio);
        textBio.setText(userProfile.getBio());

        ImageView imageProfile = (ImageView) findViewById(R.id.imageProfile);
        Glide.with(MainActivity.this).load(userProfile.getUrlProfile()).into(imageProfile);


        //RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);

        //PostAdapter
        PostAdapter postAdapter = new PostAdapter(this);
        postAdapter.setData(userProfile.getPosts(), gridLayout);

        if (gridLayout) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }

        recyclerView.setAdapter(postAdapter);

    }

    //----------------------------- Spinner ---------------------------//
    private void selectSpinner() {
        Spinner spinner = (Spinner) findViewById(R.id.userSpinner);
        spinner.setOnItemSelectedListener(this);

        List<String> categories = new ArrayList<>();
        categories.add("@nature");
        categories.add("@android");
        categories.add("@cartoon");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ((TextView) parent.getChildAt(0)).setTextColor(Color.GRAY);
        ((TextView) parent.getChildAt(0)).setTextSize(15);

        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        if (item.equals("@android")) user = "android";
        if (item.equals("@nature")) user = "nature";
        if (item.equals("@cartoon")) user = "cartoon";
        getUserProfile(user);
        mProgressDialog.show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    //------------------------ SelectViewBtn -----------------------//
    private void setViewFormat() {

        final ImageView btnListView = (ImageView) findViewById(R.id.btnListView);
        final ImageView btnGridView = (ImageView) findViewById(R.id.btnGridView);

        btnListView.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {
                gridLayout = false;
                btnGridView.setVisibility(View.VISIBLE);
                btnListView.setVisibility(View.INVISIBLE);
                getUserProfile(user);
            }
        });

        btnGridView.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {
                gridLayout = true;
                btnGridView.setVisibility(View.INVISIBLE);
                btnListView.setVisibility(View.VISIBLE);
                getUserProfile(user);
            }
        });

    }


    //--------------------------- FollowBtn--------------------------//
    private void setFollowBtn() {

        final ImageView followBtn = (ImageView) findViewById(R.id.followBtn);
        final ImageView followingBtn = (ImageView) findViewById(R.id.followingBtn);
        followBtn.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {
                followingBtn.setVisibility(View.VISIBLE);
                followBtn.setVisibility(View.INVISIBLE);
            }
        });
        followingBtn.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {
                followBtn.setVisibility(View.VISIBLE);
                followingBtn.setVisibility(View.INVISIBLE);
            }
        });
    }

    //----------------------------- Loading ---------------------------//
    private void showProgressDialog() {
        mProgressDialog = new ProgressDialog(MainActivity.this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();
    }

    //----------------------------- Animation ---------------------------//
    private void animationLinearLayout() {
        LinearLayout linearBottom = (LinearLayout) findViewById(R.id.linearBottom);
        LinearLayout linearTop = (LinearLayout) findViewById(R.id.linearTop);
        Animation uptodown = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        Animation downtoup = AnimationUtils.loadAnimation(this, R.anim.downtoup);
        linearTop.setAnimation(uptodown);
        linearBottom.setAnimation(downtoup);
    }

}