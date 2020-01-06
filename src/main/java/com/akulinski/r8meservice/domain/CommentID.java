package com.akulinski.r8meservice.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CommentID implements Serializable {

    @Column(name = "receiver_id")
    private Long receiver;

    @Column(name = "poster_id")
    private Long poster;

    @Column(name = "comment_id")
    private Long comment;

    public CommentID() {
    }

    public CommentID(Long receiver, Long poster, Long comment) {
        this.receiver = receiver;
        this.poster = poster;
        this.comment = comment;
    }

    public Long getReceiver() {
        return receiver;
    }

    public void setReceiver(Long receiver) {
        this.receiver = receiver;
    }

    public Long getPoster() {
        return poster;
    }

    public void setPoster(Long poster) {
        this.poster = poster;
    }

    public Long getComment() {
        return comment;
    }

    public void setComment(Long comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentID)) return false;
        CommentID commentID = (CommentID) o;
        return Objects.equals(getReceiver(), commentID.getReceiver()) &&
            Objects.equals(getPoster(), commentID.getPoster()) &&
            Objects.equals(getComment(), commentID.getComment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getReceiver(), getPoster(), getComment());
    }

    @Override
    public String toString() {
        return "CommentID{" +
            "receiver=" + receiver +
            ", poster=" + poster +
            ", comment=" + comment +
            '}';
    }
}
