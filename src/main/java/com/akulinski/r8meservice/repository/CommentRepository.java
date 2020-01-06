package com.akulinski.r8meservice.repository;
import com.akulinski.r8meservice.domain.Comment;
import com.akulinski.r8meservice.domain.CommentXProfile;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * Spring Data  repository for the Comment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findByCommentXProfile(CommentXProfile commentXProfile);
}
