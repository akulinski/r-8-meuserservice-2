package com.akulinski.r8meservice.web.rest.vm;


import com.akulinski.r8meservice.domain.User;
import com.akulinski.r8meservice.domain.UserProfile;

import java.io.Serializable;
import java.util.function.Function;

public class UserProfileVM implements Serializable {

    private final String username;
    private final Double currentRating;
    private final String link;
    private final Integer followersCount;
    private final Integer commentsCount;

    public UserProfileVM(String username, Double currentRating, String link, Integer followersCount, Integer commentsCount) {
        this.username = username;
        this.currentRating = currentRating;
        this.link = link;
        this.followersCount = followersCount;
        this.commentsCount = commentsCount;
    }

    public String getUsername() {
        return username;
    }

    public Double getCurrentRating() {
        return currentRating;
    }

    public String getLink() {
        return link;
    }

    public Integer getFollowersCount() {
        return followersCount;
    }

    public Integer getCommentsCount() {
        return commentsCount;
    }

    public static Function<UserProfile, UserProfileVM> mapUserProfileToVMFunction() {
        return UserProfile -> {

            User user = UserProfile.getUser();
            UserProfileVM userProfileVM = new UserProfileVM(user.getLogin(), 0.0, user.getImageUrl(), 0,0);
            return userProfileVM;
        };
    }
}
