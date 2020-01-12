package com.akulinski.r8meservice.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.akulinski.r8meservice.domain.UserProfile} entity.
 */
public class UserProfileDTO implements Serializable {

    private Long id;

    private Double currentRating;

    private Long userId;

    private String link;

    private String username;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCurrentRating() {
        return currentRating;
    }

    public void setCurrentRating(Double currentRating) {
        this.currentRating = currentRating;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserProfileDTO userProfileDTO = (UserProfileDTO) o;
        if (userProfileDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userProfileDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserProfileDTO{" +
            "id=" + getId() +
            ", currentRating=" + getCurrentRating() +
            ", user=" + getUserId() +
            "}";
    }
}
