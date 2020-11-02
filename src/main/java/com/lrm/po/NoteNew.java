package com.lrm.po;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * 2020/11/02 csh  nanjing
 */
@Entity
@Table(name = "t_notenew")
public class NoteNew {

    @Id
    @GeneratedValue
    private Long id;
    private String notice;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    public Long getId() {
        return id;
    }

    public String getNotice() {
        return notice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoteNew noteNew = (NoteNew) o;
        return Objects.equals(id, noteNew.id) &&
                Objects.equals(notice, noteNew.notice) &&
                Objects.equals(createTime, noteNew.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, notice, createTime);
    }

    @Override
    public String toString() {
        return "NoteNew{" +
                "id=" + id +
                ", notice='" + notice + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public NoteNew(String notice, Date createTime) {
        this.notice = notice;
        this.createTime = createTime;
    }

    public NoteNew() {
    }
}
