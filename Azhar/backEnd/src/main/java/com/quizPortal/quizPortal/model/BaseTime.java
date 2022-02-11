package com.quizPortal.quizPortal.model;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class BaseTime {
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_time",nullable = false)
    private Date createdTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_time",nullable = false)
    private Date updatedTime;

    @PrePersist
    private void onCreate()
    {
        this.createdTime = new Date();
        this.updatedTime = new Date();
    }

    @PreUpdate
    private void onUpdate(){
        this.updatedTime = new Date();
    }

}
