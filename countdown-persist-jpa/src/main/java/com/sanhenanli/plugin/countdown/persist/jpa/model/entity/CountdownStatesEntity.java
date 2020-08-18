package com.sanhenanli.plugin.countdown.persist.jpa.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * datetime 2020/8/18 10:36
 *
 * @author zhouwenxiang
 */
@Entity
@Table(name = "countdown_states", schema = "hm", catalog = "")
public class CountdownStatesEntity {
    private int id;
    private String name;
    private Long millis;
    private Integer action;
    private String actionResult;
    private Integer state;
    private Long timestamp;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "millis")
    public Long getMillis() {
        return millis;
    }

    public void setMillis(Long millis) {
        this.millis = millis;
    }

    @Basic
    @Column(name = "action")
    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    @Basic
    @Column(name = "action_result")
    public String getActionResult() {
        return actionResult;
    }

    public void setActionResult(String actionResult) {
        this.actionResult = actionResult;
    }

    @Basic
    @Column(name = "state")
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Basic
    @Column(name = "timestamp")
    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Basic
    @Column(name = "create_time", insertable = false, updatable = false, columnDefinition = "CURRENT_TIMESTAMP")
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountdownStatesEntity that = (CountdownStatesEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(millis, that.millis) &&
                Objects.equals(action, that.action) &&
                Objects.equals(actionResult, that.actionResult) &&
                Objects.equals(state, that.state) &&
                Objects.equals(timestamp, that.timestamp) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, millis, action, actionResult, state, timestamp, createTime, updateTime);
    }
}
