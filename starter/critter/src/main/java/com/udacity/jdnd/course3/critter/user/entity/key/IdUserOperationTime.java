package com.udacity.jdnd.course3.critter.user.entity.key;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class IdUserOperationTime implements Serializable {
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "operation_time_id")
    private Integer operationTimeId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getOperationTimeId() {
        return operationTimeId;
    }

    public void setOperationTimeId(Integer operationTimeId) {
        this.operationTimeId = operationTimeId;
    }
}
