package com.sg.gate.musicserver.domain;

import java.io.Serializable;
import java.util.Date;

public class Song implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column song.id
     *
     * @mbg.generated Mon Apr 11 10:05:03 CST 2022
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column song.singer_id
     *
     * @mbg.generated Mon Apr 11 10:05:03 CST 2022
     */
    private Integer singerId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column song.name
     *
     * @mbg.generated Mon Apr 11 10:05:03 CST 2022
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column song.introduction
     *
     * @mbg.generated Mon Apr 11 10:05:03 CST 2022
     */
    private String introduction;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column song.create_time
     *
     * @mbg.generated Mon Apr 11 10:05:03 CST 2022
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column song.update_time
     *
     * @mbg.generated Mon Apr 11 10:05:03 CST 2022
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column song.pic
     *
     * @mbg.generated Mon Apr 11 10:05:03 CST 2022
     */
    private String pic;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column song.url
     *
     * @mbg.generated Mon Apr 11 10:05:03 CST 2022
     */
    private String url;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column song.lyric
     *
     * @mbg.generated Mon Apr 11 10:05:03 CST 2022
     */
    private String lyric;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column song.id
     *
     * @return the value of song.id
     *
     * @mbg.generated Mon Apr 11 10:05:03 CST 2022
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column song.id
     *
     * @param id the value for song.id
     *
     * @mbg.generated Mon Apr 11 10:05:03 CST 2022
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column song.singer_id
     *
     * @return the value of song.singer_id
     *
     * @mbg.generated Mon Apr 11 10:05:03 CST 2022
     */
    public Integer getSingerId() {
        return singerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column song.singer_id
     *
     * @param singerId the value for song.singer_id
     *
     * @mbg.generated Mon Apr 11 10:05:03 CST 2022
     */
    public void setSingerId(Integer singerId) {
        this.singerId = singerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column song.name
     *
     * @return the value of song.name
     *
     * @mbg.generated Mon Apr 11 10:05:03 CST 2022
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column song.name
     *
     * @param name the value for song.name
     *
     * @mbg.generated Mon Apr 11 10:05:03 CST 2022
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column song.introduction
     *
     * @return the value of song.introduction
     *
     * @mbg.generated Mon Apr 11 10:05:03 CST 2022
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column song.introduction
     *
     * @param introduction the value for song.introduction
     *
     * @mbg.generated Mon Apr 11 10:05:03 CST 2022
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column song.create_time
     *
     * @return the value of song.create_time
     *
     * @mbg.generated Mon Apr 11 10:05:03 CST 2022
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column song.create_time
     *
     * @param createTime the value for song.create_time
     *
     * @mbg.generated Mon Apr 11 10:05:03 CST 2022
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column song.update_time
     *
     * @return the value of song.update_time
     *
     * @mbg.generated Mon Apr 11 10:05:03 CST 2022
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column song.update_time
     *
     * @param updateTime the value for song.update_time
     *
     * @mbg.generated Mon Apr 11 10:05:03 CST 2022
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column song.pic
     *
     * @return the value of song.pic
     *
     * @mbg.generated Mon Apr 11 10:05:03 CST 2022
     */
    public String getPic() {
        return pic;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column song.pic
     *
     * @param pic the value for song.pic
     *
     * @mbg.generated Mon Apr 11 10:05:03 CST 2022
     */
    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column song.url
     *
     * @return the value of song.url
     *
     * @mbg.generated Mon Apr 11 10:05:03 CST 2022
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column song.url
     *
     * @param url the value for song.url
     *
     * @mbg.generated Mon Apr 11 10:05:03 CST 2022
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column song.lyric
     *
     * @return the value of song.lyric
     *
     * @mbg.generated Mon Apr 11 10:05:03 CST 2022
     */
    public String getLyric() {
        return lyric;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column song.lyric
     *
     * @param lyric the value for song.lyric
     *
     * @mbg.generated Mon Apr 11 10:05:03 CST 2022
     */
    public void setLyric(String lyric) {
        this.lyric = lyric == null ? null : lyric.trim();
    }
}