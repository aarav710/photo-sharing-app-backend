package com.photosharing.app.posts;

import java.util.List;

public interface PostCustomRepo {
    List<Post> findFeedPosts(String username);
}
