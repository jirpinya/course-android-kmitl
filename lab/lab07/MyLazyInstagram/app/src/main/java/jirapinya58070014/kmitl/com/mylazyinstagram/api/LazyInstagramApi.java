package jirapinya58070014.kmitl.com.mylazyinstagram.api;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LazyInstagramApi {

    String BASE = "https://us-central1-retrofit-course.cloudfunctions.net";

    @GET("/getProfile")
    Call<UserProfile> getProfile(@Query("user") String user);

}
