package com.quizPortal.quizPortal.model.Entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class UserSession{

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "VARCHAR(45)", nullable = false)
    private String token;

    @Temporal(TemporalType.TIMESTAMP)
    private Date signInTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date signOutTime;

    @ManyToOne
    @JoinColumn(name = "user_id" )
    private User user;

    public Date getSignInTime() {
        return signInTime;
    }

    public void setSignInTime(Date signInTime) {
        this.signInTime = signInTime;
    }

    public Date getSignOutTime() {
        return signOutTime;
    }

    public void setSignOutTime(Date signOutTime) {
        this.signOutTime = signOutTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
