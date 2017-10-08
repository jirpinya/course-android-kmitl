package jirapinya58070014.kmitl.com.mylazyinstagram.api;


import java.util.List;

public class UserProfile {
    private String user;
    private String urlProfile;
    private String bio;
    private int follower;
    private int following;
    private boolean isFollow;
    private int post;
    private List<PostModel> posts;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUrlProfile() {
        return urlProfile;
    }

    public void setUrlProfile(String urlProfile) {
        this.urlProfile = urlProfile;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getFollower() {
        return follower;
    }

    public void setFollower(int follower) {
        this.follower = follower;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public boolean isFollow() {
        return isFollow;
    }

    public void setFollow(boolean follow) {
        isFollow = follow;
    }

    public int getPost() {
        return post;
    }

    public void setPost(int post) {
        this.post = post;
    }

    public void setPosts(List<PostModel> posts) {
        this.posts = posts;
    }

    public List<PostModel> getPosts() {
        return posts;
    }

}

