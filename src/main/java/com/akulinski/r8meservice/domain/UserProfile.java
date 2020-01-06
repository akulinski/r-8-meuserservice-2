package com.akulinski.r8meservice.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Set;

/**
 * A UserProfile.
 */
@Entity
@Table(name = "user_profile")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "userprofile")
public class UserProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @Column(name = "current_rating")
    private Double currentRating;

    @OneToOne
    @JoinColumn(unique = true)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receiver")
    private Set<CommentXProfile> received;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "poster")
    private Set<CommentXProfile> posted;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCurrentRating() {
        return currentRating;
    }

    public UserProfile currentRating(Double currentRating) {
        this.currentRating = currentRating;
        return this;
    }

    public void setCurrentRating(Double currentRating) {
        this.currentRating = currentRating;
    }

    public User getUser() {
        return user;
    }

    public UserProfile user(User user) {
        this.user = user;
        return this;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Set<CommentXProfile> getReceived() {
        return received;
    }

    public void setReceived(Set<CommentXProfile> received) {
        this.received = received;
    }

    public Set<CommentXProfile> getPosted() {
        return posted;
    }

    public void setPosted(Set<CommentXProfile> posted) {
        this.posted = posted;
    }

    public void setUser(User user) {
        this.user = user;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserProfile)) {
            return false;
        }
        return id != null && id.equals(((UserProfile) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
            "id=" + getId() +
            ", currentRating=" + getCurrentRating() +
            "}";
    }
}
