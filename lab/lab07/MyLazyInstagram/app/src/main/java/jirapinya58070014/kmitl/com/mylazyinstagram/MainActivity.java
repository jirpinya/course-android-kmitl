package jirapinya58070014.kmitl.com.mylazyinstagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import jirapinya58070014.kmitl.com.mylazyinstagram.adapter.PostAdapter;
import jirapinya58070014.kmitl.com.mylazyinstagram.api.LazyInstagramApi;
import jirapinya58070014.kmitl.com.mylazyinstagram.api.UserProfile;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getUserProfile("nature");

        PostAdapter postAdapter =
                new PostAdapter(this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));    //ซ้ายไปขวา Linear บนลงล่าง
        recyclerView.setAdapter(postAdapter);
    }

    //สร้างส่วนเชื่อมต่อ Retrofit  สำหรับดึงข้อมูลจาก Api
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
        LazyInstagramApi lazyInstagramApi
                = retrofit.create(LazyInstagramApi.class);

        Call<UserProfile> call = lazyInstagramApi.getProfile(usrName);
        call.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if (response.isSuccessful()) {
                    UserProfile userProfile = response.body();
                    TextView textName = (TextView) findViewById(R.id.textUser);
                    textName.setText("@"+userProfile.getUser());

                    ImageView imageProfile = (ImageView) findViewById(R.id.imageProfile);
                    Glide.with(MainActivity.this).load(userProfile.getUrlProfile()).into(imageProfile);

                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {

            }
        });

    }
}