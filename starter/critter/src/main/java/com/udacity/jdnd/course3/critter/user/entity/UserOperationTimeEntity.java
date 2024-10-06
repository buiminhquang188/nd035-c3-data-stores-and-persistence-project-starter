package com.udacity.jdnd.course3.critter.user.entity;

import com.udacity.jdnd.course3.critter.user.entity.key.IdUserOperationTime;

import javax.persistence.*;

@Entity(name = "user_operation_time")
public class UserOperationTimeEntity {
    @Id
    @EmbeddedId
    private IdUserOperationTime idUserOperationTime;

    @ManyToOne
    @JoinColumn(name = "operation_time_id", insertable = false, updatable = false)
    private OperationTimeEntity operationTime;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserEntity user;

    public IdUserOperationTime getIdUserOperationTime() {
        return idUserOperationTime;
    }

    public void setIdUserOperationTime(IdUserOperationTime idUserOperationTime) {
        this.idUserOperationTime = idUserOperationTime;
    }

    public OperationTimeEntity getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(OperationTimeEntity operationTime) {
        this.operationTime = operationTime;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
