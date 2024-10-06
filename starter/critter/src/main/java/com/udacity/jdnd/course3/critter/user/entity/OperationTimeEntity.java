package com.udacity.jdnd.course3.critter.user.entity;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.List;

@Entity(name = "operation_time")
public class OperationTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "day_of_week")
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @OneToMany(mappedBy = "operationTime")
    private List<UserOperationTimeEntity> operationTimeUsers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public List<UserOperationTimeEntity> getOperationTimeUsers() {
        return operationTimeUsers;
    }

    public void setOperationTimeUsers(List<UserOperationTimeEntity> operationTimeUsers) {
        this.operationTimeUsers = operationTimeUsers;
    }
}
