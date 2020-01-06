package com.akulinski.r8meservice.service.dto;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.akulinski.r8meservice.domain.Comment} entity.
 */
public class CommentDTO implements Serializable, Comparable {

    private Long id;

    private String comment;

    private Instant timeStamp;

    private String receiver;

    private String commenter;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Instant getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Instant timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getCommenter() {
        return commenter;
    }

    public void setCommenter(String commenter) {
        this.commenter = commenter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentDTO)) return false;
        CommentDTO that = (CommentDTO) o;
        return Objects.equals(getId(), that.getId()) &&
            Objects.equals(getComment(), that.getComment()) &&
            Objects.equals(getTimeStamp(), that.getTimeStamp()) &&
            Objects.equals(getReceiver(), that.getReceiver()) &&
            Objects.equals(getCommenter(), that.getCommenter());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getComment(), getTimeStamp(), getReceiver(), getCommenter());
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
            "id=" + id +
            ", comment='" + comment + '\'' +
            ", timeStamp=" + timeStamp +
            ", receiver='" + receiver + '\'' +
            ", commenter='" + commenter + '\'' +
            '}';
    }

    @Override
    public int compareTo(@NotNull Object o) {
        return this.getTimeStamp().compareTo(((CommentDTO) o).getTimeStamp());
    }
}
