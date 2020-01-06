package com.akulinski.r8meservice.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Objects;

/**
 * A CommentXProfile.
 */
@Entity
@Table(name = "comment_x_profile")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CommentXProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private CommentID commentID;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("receiver")
    private UserProfile receiver;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("poster")
    private UserProfile poster;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId("comment")
    private Comment comment;

    public CommentXProfile() {
    }

    public UserProfile getReceiver() {
        return receiver;
    }

    public CommentXProfile receiver(UserProfile receiver, UserProfile poster, Comment comment) {
        this.receiver = receiver;
        this.poster = poster;
        this.comment = comment;
        this.commentID = new CommentID(receiver.getId(), poster.getId(), comment.getId());
        return this;
    }

    public CommentID getCommentID() {
        return commentID;
    }

    public void setCommentID(CommentID commentID) {
        this.commentID = commentID;
    }

    public void setReceiver(UserProfile receiver) {
        this.receiver = receiver;
    }

    public UserProfile getPoster() {
        return poster;
    }

    public void setPoster(UserProfile poster) {
        this.poster = poster;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentXProfile)) return false;
        CommentXProfile that = (CommentXProfile) o;
        return Objects.equals(getCommentID(), that.getCommentID()) &&
            Objects.equals(getReceiver(), that.getReceiver()) &&
            Objects.equals(getPoster(), that.getPoster()) &&
            Objects.equals(getComment(), that.getComment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCommentID(), getReceiver(), getPoster(), getComment());
    }

    @Override
    public String toString() {
        return "CommentXProfile{" +
            "commentID=" + commentID +
            ", receiver=" + receiver +
            ", poster=" + poster +
            ", comment=" + comment +
            '}';
    }
}
