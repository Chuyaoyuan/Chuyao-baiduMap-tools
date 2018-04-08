package com.cn.yaomvc.pojo;

import java.util.Date;

public class User {
    private Long seq;

    private String stats;

    private String username;

    private String password;

    private String realname;

    private String ssgaj;

    private String jingxian;

    private Date stopTime;

    private Date createTime;

    private Date updateTime;

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public String getStats() {
        return stats;
    }

    public void setStats(String stats) {
        this.stats = stats == null ? null : stats.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getSsgaj() {
        return ssgaj;
    }

    public void setSsgaj(String ssgaj) {
        this.ssgaj = ssgaj == null ? null : ssgaj.trim();
    }

    public String getJingxian() {
        return jingxian;
    }

    public void setJingxian(String jingxian) {
        this.jingxian = jingxian == null ? null : jingxian.trim();
    }

    public Date getStopTime() {
        return stopTime;
    }

    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}