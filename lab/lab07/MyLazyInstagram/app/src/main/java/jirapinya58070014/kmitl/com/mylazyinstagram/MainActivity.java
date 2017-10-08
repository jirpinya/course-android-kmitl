package jirapinya58070014.kmitl.com.mylazyinstagram;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import jirapinya58070014.kmitl.com.mylazyinstagram.adapter.PostAdapter;
import jirapinya58070014.kmitl.com.mylazyinstagram.api.LazyInstagramApi;
import jirapinya58070014.kmitl.com.mylazyinstagram.api.UserProfile;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private boolean gridLayout = true;
    private String user = "android";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getUserProfile(user);

        //-------------------- SelectViewBtn --------------------//
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


        //----------------------- Spinner ---------------------//
        Spinner spinner = (Spinner) findViewById(R.id.userSpinner);
        spinner.setOnItemSelectedListener(this);

        List<String> categories = new ArrayList<String>();
        categories.add("@android");
        categories.add("@nature");
        categories.add("@cartoon");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    //--------------------- Connect Retrofit -------------------//
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
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if (response.isSuccessful()) {
                    UserProfile userProfile = response.body();
                    display(userProfile, gridLayout);
                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {
            }
        });

    }

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

        //PostAdapter
        PostAdapter postAdapter = new PostAdapter(this);
        postAdapter.setData(userProfile.getPosts(), gridLayout);

        //RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        if (gridLayout) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }

        recyclerView.setAdapter(postAdapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ((TextView) parent.getChildAt(0)).setTextColor(Color.GRAY);
        ((TextView) parent.getChildAt(0)).setTextSize(15);

        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        if (item == "@android") user = "android";
        if (item == "@nature") user = "nature";
        if (item == "@cartoon") user = "cartoon";

        getUserProfile(user);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}