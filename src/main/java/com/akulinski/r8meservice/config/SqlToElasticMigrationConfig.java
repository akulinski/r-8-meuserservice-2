package com.akulinski.r8meservice.config;

import com.akulinski.r8meservice.repository.UserProfileRepository;
import com.akulinski.r8meservice.repository.UserRepository;
import com.akulinski.r8meservice.repository.search.UserProfileSearchRepository;
import com.akulinski.r8meservice.repository.search.UserSearchRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import javax.transaction.Transactional;

@Configuration
public class SqlToElasticMigrationConfig {
    private final UserRepository userRepository;
    private final UserSearchRepository userSearchRepository;

    private final UserProfileRepository userProfileRepository;
    private final UserProfileSearchRepository userProfileSearchRepository;


    public SqlToElasticMigrationConfig(UserRepository userRepository, UserSearchRepository userSearchRepository, UserProfileRepository userProfileRepository, UserProfileSearchRepository userProfileSearchRepository) {
        this.userRepository = userRepository;
        this.userSearchRepository = userSearchRepository;
        this.userProfileRepository = userProfileRepository;
        this.userProfileSearchRepository = userProfileSearchRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void migrateSql(){
        userRepository.findAllStream().forEach(user->{
            userSearchRepository.findById(user.getId()).ifPresentOrElse(pr->{},()->{
                userSearchRepository.save(user);
            });
        });
        userProfileRepository.findAllStream().forEach(userProfile -> {
            userProfileSearchRepository.findById(userProfile.getId()).ifPresentOrElse(pr->{}, ()->{
                userProfileSearchRepository.save(userProfile);
            });
        });
    }
}
