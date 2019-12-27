package com.akulinski.r8meservice.config;

import com.akulinski.r8meservice.domain.*;
import com.akulinski.r8meservice.repository.*;
import com.akulinski.r8meservice.repository.search.*;
import com.akulinski.r8meservice.service.UserService;
import com.akulinski.r8meservice.service.dto.UserDTO;
import com.akulinski.r8meservice.service.util.RandomUtil;
import com.github.javafaker.Faker;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.util.List;
import java.util.Random;
import java.util.Set;

@Configuration
public class FakerConfig {
    private final Faker faker;
    private final UserRepository userRepository;
    private final UserSearchRepository userSearchRepository;

    private final UserProfileRepository userProfileRepository;
    private final UserProfileSearchRepository userProfileSearchRepository;


    private final CommentRepository commentRepository;
    private final CommentSearchRepository commentSearchRepository;

    private final CommentXProfileRepository commentXProfileRepository;
    private final CommentXProfileSearchRepository commentXProfileSearchRepository;


    private final RateRepository rateRepository;
    private final RateSearchRepository rateSearchRepository;

    private final RateXProfileRepository rateXProfileRepository;
    private final RateXProfileSearchRepository rateXProfileSearchRepository;


    private final UserService userService;
    private final Random random;

    public FakerConfig(UserRepository userRepository, UserSearchRepository userSearchRepository, UserProfileRepository userProfileRepository, UserProfileSearchRepository userProfileSearchRepository, CommentRepository commentRepository,
                       CommentSearchRepository commentSearchRepository, CommentXProfileRepository commentXProfileRepository, CommentXProfileSearchRepository commentXProfileSearchRepository, RateRepository rateRepository, RateSearchRepository rateSearchRepository, RateXProfileRepository rateXProfileRepository, RateXProfileSearchRepository rateXProfileSearchRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userSearchRepository = userSearchRepository;
        this.userProfileRepository = userProfileRepository;
        this.userProfileSearchRepository = userProfileSearchRepository;
        this.commentRepository = commentRepository;
        this.commentSearchRepository = commentSearchRepository;
        this.commentXProfileRepository = commentXProfileRepository;
        this.commentXProfileSearchRepository = commentXProfileSearchRepository;
        this.rateRepository = rateRepository;
        this.rateSearchRepository = rateSearchRepository;
        this.rateXProfileRepository = rateXProfileRepository;
        this.rateXProfileSearchRepository = rateXProfileSearchRepository;
        this.userService = userService;
        this.random = new Random();
        this.faker = new Faker();
    }


    @EventListener(ApplicationReadyEvent.class)
    public void mockData() {
        if (userRepository.count() < 10) {

            for (int i = 0; i < 10; i++) {

                final List<User> users = userRepository.findAll();

                final UserDTO userDTO = getUserDTO();
                final var user = userService.createUser(userDTO);

                UserProfile userProfile = getUserProfile(user);
                userProfileRepository.save(userProfile);
                userProfileSearchRepository.save(userProfile);

                for(int j=0; j<10; j++){
                    try {
                        setUpComment(users, userProfile);
                        setUpRate(users, userProfile);
                    }catch (Exception ex){
                        //ignore
                    }
                }
            }
        }
    }

    private void setUpRate(List<User> users, UserProfile userProfile) {
        Rate rate = new Rate();
        rate.setQuestion(faker.witcher().location());
        rate.setValue(faker.random().nextDouble());
        rate = rateRepository.save(rate);
        rateSearchRepository.save(rate);

        RateXProfile rateXProfile = new RateXProfile();
        rateXProfile.setRated(userProfile);
        rateXProfile.setRater(userProfileRepository.findByUser(users.get(random.nextInt(users.size()-1))).orElse(null));
        rateXProfile.setRate(rate);
        rateXProfileRepository.save(rateXProfile);
        rateXProfileSearchRepository.save(rateXProfile);

    }

    private void setUpComment(List<User> users, UserProfile userProfile) {
        Comment comment = new Comment();
        comment.setComment(faker.witcher().quote());

        comment = commentRepository.save(comment);
        commentSearchRepository.save(comment);

        CommentXProfile commentXProfile = new CommentXProfile();
        commentXProfile.setReceiver(userProfile);
        commentXProfile.setPoster(userProfileRepository.findByUser(users.get(random.nextInt(users.size()-1))).orElse(null));
        commentXProfile.setComment(comment);
        commentXProfileRepository.save(commentXProfile);
        commentXProfileSearchRepository.save(commentXProfile);
    }

    private UserProfile getUserProfile(User user) {
        return userProfileRepository.findByUser(user).get();
    }

    private UserDTO getUserDTO() {
        final var userDTO = new UserDTO();
        userDTO.setAuthorities(Set.of("USER"));
        userDTO.setActivated(true);
        userDTO.setEmail(faker.internet().emailAddress()+RandomUtil.generateResetKey());
        userDTO.setImageUrl(faker.avatar().image());
        userDTO.setLogin(faker.name().username()+RandomUtil.generateActivationKey());
        return userDTO;
    }
}
