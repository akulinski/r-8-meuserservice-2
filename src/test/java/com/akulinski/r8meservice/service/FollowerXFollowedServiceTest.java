//package com.akulinski.r8meservice.service;
//
//import com.akulinski.r8meservice.domain.FollowerXFollowed;
//import com.akulinski.r8meservice.domain.User;
//import com.akulinski.r8meservice.domain.UserProfile;
//import com.akulinski.r8meservice.repository.FollowerXFollowedRepository;
//import com.akulinski.r8meservice.repository.UserProfileRepository;
//import com.akulinski.r8meservice.repository.UserRepository;
//import com.akulinski.r8meservice.web.rest.vm.UserProfileVM;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//
//@ExtendWith(MockitoExtension.class)
//class FollowerXFollowedServiceTest {
//
//    private static final String USERNAME = "R.Zielinski";
//    private User user1;
//    private User user2;
//    private FollowerXFollowed followerXFollowed;
//    private List<FollowerXFollowed> followerXFollowedList;
//    private UserProfile followed;
//    private UserProfile follower;
//    private UserProfileVM userProfileVM;
//
//    @Mock
//    private UserProfileRepository userProfileRepository;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private FollowerXFollowedRepository followerXFollowedRepository;
//
//    @InjectMocks
//    private FollowerXFollowedService followerXFollowedService;
//
//    @BeforeEach
//    void setUp(){
//        user1.setLogin("Login1");
//        user1.setImageUrl("Url1");
//        user2.setLogin("Login2");
//        user2.setImageUrl("Url2");
//        followed.setUser(user1);
//        follower.setUser(user2);
//        followerXFollowed = new FollowerXFollowed();
//        followerXFollowed.setId(1L);
//        followerXFollowed.setFollowed(followed);
//        followerXFollowed.setFollower(follower);
//        followerXFollowedList.add(followerXFollowed);
//        userProfileVM = new UserProfileVM("Login2", 0.0, "Url2", 0,0);
//    }
//
//    @Test
//    void findAllUserFollowers() {
//        when(followerXFollowedRepository.findAllByFollowed(any())).thenReturn(followerXFollowedList);
//        List<UserProfileVM> userProfileVMReturnList = followerXFollowedService.findAllUserFollowers();
//        UserProfileVM userProfileVMReturn = userProfileVMReturnList.get(0);
//
//        assertEquals(userProfileVM.getUsername(), userProfileVMReturn.getUsername());
//        assertEquals(userProfileVM.getLink(), userProfileVMReturn.getLink());
//    }
//}
