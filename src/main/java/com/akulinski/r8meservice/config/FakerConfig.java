package com.akulinski.r8meservice.config;

import com.akulinski.r8meservice.domain.*;
import com.akulinski.r8meservice.repository.*;
import com.akulinski.r8meservice.repository.search.RateSearchRepository;
import com.akulinski.r8meservice.repository.search.RateXProfileSearchRepository;
import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Configuration
@Profile("dev")
public class FakerConfig {
    private final Logger log = LoggerFactory.getLogger(FakerConfig.class);

    private final Faker faker;
    private final UserRepository userRepository;

    private final UserProfileRepository userProfileRepository;


    private final CommentRepository commentRepository;

    private final CommentXProfileRepository commentXProfileRepository;

    private final RateRepository rateRepository;
    private final RateSearchRepository rateSearchRepository;

    private final RateXProfileRepository rateXProfileRepository;
    private final RateXProfileSearchRepository rateXProfileSearchRepository;


    private final Random random;

    @Value("${mock.comments}")
    private long comments;

    public FakerConfig(UserRepository userRepository, UserProfileRepository userProfileRepository, CommentRepository commentRepository,
                       CommentXProfileRepository commentXProfileRepository, RateRepository rateRepository, RateSearchRepository rateSearchRepository, RateXProfileRepository rateXProfileRepository, RateXProfileSearchRepository rateXProfileSearchRepository) {
        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
        this.commentRepository = commentRepository;
        this.commentXProfileRepository = commentXProfileRepository;
        this.rateRepository = rateRepository;
        this.rateSearchRepository = rateSearchRepository;
        this.rateXProfileRepository = rateXProfileRepository;
        this.rateXProfileSearchRepository = rateXProfileSearchRepository;
        this.random = new Random();
        this.faker = new Faker();
    }


    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void mockData() {


        List<User> emptyProfiles = userRepository.findAll().stream().filter(user -> userProfileRepository.findByUser(user).isEmpty()).collect(Collectors.toList());

        emptyProfiles.forEach(user -> {
                try {
                    createUserProfileWithComments(user);
                } catch (Exception ex) {
                    log.error(ex.getLocalizedMessage());
                }
            }
        );
    }

    private UserProfile createUserProfileWithComments(User user) {
        final UserProfile userProfile = createUserProfile(user);
        final List<User> users = userRepository.findAll();

        for (int j = 0; j < comments; j++) {
            try {
                User randomUser = users.get(random.nextInt(users.size() - 1));
                UserProfile randomProfile = userProfileRepository.findByUser(randomUser).orElse(null);

                while (randomProfile == null) {
                    randomUser = users.get(random.nextInt(users.size() - 1));
                    randomProfile = userProfileRepository.findByUser(randomUser).orElse(null);

                }

                setUpComment(randomProfile, userProfile);
                //setUpRate(users, userProfile);
            } catch (Exception ex) {
                log.error(ex.getLocalizedMessage());
            }
        }

        return userProfile;
    }

    private UserProfile createUserProfile(User user) {
        final var userProfile = new UserProfile();
        userProfile.setUser(user);
        userProfileRepository.save(userProfile);
        return userProfile;
    }

    private void setUpRate(List<User> users, UserProfile userProfile) {
        Rate rate = new Rate();
        rate.setQuestion(faker.witcher().location());
        rate.setValue(faker.random().nextDouble());
        rate = rateRepository.save(rate);
        rateSearchRepository.save(rate);

        RateXProfile rateXProfile = new RateXProfile();
        rateXProfile.setRated(userProfile);
        rateXProfile.setRater(userProfileRepository.findByUser(users.get(random.nextInt(users.size() - 1))).orElse(null));
        rateXProfile.setRate(rate);
        rateXProfileRepository.save(rateXProfile);
        rateXProfileSearchRepository.save(rateXProfile);
    }

    private void setUpComment(UserProfile poster, UserProfile userProfile) {
        try {
            Comment comment = new Comment();
            comment.setComment(faker.witcher().quote());

            comment = commentRepository.save(comment);

            CommentXProfile commentXProfile = new CommentXProfile();
            commentXProfile.setReceiver(userProfile);

            CommentID commentID = new CommentID(userProfile.getId(), poster.getId(), comment.getId());
            commentXProfile.setCommentID(commentID);
            commentXProfile.setComment(comment);
            commentXProfile.setReceiver(userProfile);
            commentXProfile.setPoster(poster);
            commentXProfile = commentXProfileRepository.save(commentXProfile);

            comment.setCommentXProfile(commentXProfile);
            commentRepository.save(comment);
        } catch (Exception ex) {
            log.error(ex.getLocalizedMessage());
        }
    }
}
