package com.jxnd.yuhaojun.blog.model;

public class VideoComment {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column videocomment.id
     *
     * @mbg.generated Mon Mar 09 09:02:16 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column videocomment.type
     *
     * @mbg.generated Mon Mar 09 09:02:16 CST 2020
     */
    private Integer type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column videocomment.commentator
     *
     * @mbg.generated Mon Mar 09 09:02:16 CST 2020
     */
    private String commentator;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column videocomment.gmt_create
     *
     * @mbg.generated Mon Mar 09 09:02:16 CST 2020
     */
    private Long gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column videocomment.gmt_modified
     *
     * @mbg.generated Mon Mar 09 09:02:16 CST 2020
     */
    private Long gmtModified;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column videocomment.like_count
     *
     * @mbg.generated Mon Mar 09 09:02:16 CST 2020
     */
    private Integer likeCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column videocomment.content
     *
     * @mbg.generated Mon Mar 09 09:02:16 CST 2020
     */
    private String content;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column videocomment.parent_aid
     *
     * @mbg.generated Mon Mar 09 09:02:16 CST 2020
     */
    private Long parentAid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column videocomment.id
     *
     * @return the value of videocomment.id
     *
     * @mbg.generated Mon Mar 09 09:02:16 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column videocomment.id
     *
     * @param id the value for videocomment.id
     *
     * @mbg.generated Mon Mar 09 09:02:16 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column videocomment.type
     *
     * @return the value of videocomment.type
     *
     * @mbg.generated Mon Mar 09 09:02:16 CST 2020
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column videocomment.type
     *
     * @param type the value for videocomment.type
     *
     * @mbg.generated Mon Mar 09 09:02:16 CST 2020
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column videocomment.commentator
     *
     * @return the value of videocomment.commentator
     *
     * @mbg.generated Mon Mar 09 09:02:16 CST 2020
     */
    public String getCommentator() {
        return commentator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column videocomment.commentator
     *
     * @param commentator the value for videocomment.commentator
     *
     * @mbg.generated Mon Mar 09 09:02:16 CST 2020
     */
    public void setCommentator(String commentator) {
        this.commentator = commentator == null ? null : commentator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column videocomment.gmt_create
     *
     * @return the value of videocomment.gmt_create
     *
     * @mbg.generated Mon Mar 09 09:02:16 CST 2020
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column videocomment.gmt_create
     *
     * @param gmtCreate the value for videocomment.gmt_create
     *
     * @mbg.generated Mon Mar 09 09:02:16 CST 2020
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column videocomment.gmt_modified
     *
     * @return the value of videocomment.gmt_modified
     *
     * @mbg.generated Mon Mar 09 09:02:16 CST 2020
     */
    public Long getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column videocomment.gmt_modified
     *
     * @param gmtModified the value for videocomment.gmt_modified
     *
     * @mbg.generated Mon Mar 09 09:02:16 CST 2020
     */
    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column videocomment.like_count
     *
     * @return the value of videocomment.like_count
     *
     * @mbg.generated Mon Mar 09 09:02:16 CST 2020
     */
    public Integer getLikeCount() {
        return likeCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column videocomment.like_count
     *
     * @param likeCount the value for videocomment.like_count
     *
     * @mbg.generated Mon Mar 09 09:02:16 CST 2020
     */
    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column videocomment.content
     *
     * @return the value of videocomment.content
     *
     * @mbg.generated Mon Mar 09 09:02:16 CST 2020
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column videocomment.content
     *
     * @param content the value for videocomment.content
     *
     * @mbg.generated Mon Mar 09 09:02:16 CST 2020
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column videocomment.parent_aid
     *
     * @return the value of videocomment.parent_aid
     *
     * @mbg.generated Mon Mar 09 09:02:16 CST 2020
     */
    public Long getParentAid() {
        return parentAid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column videocomment.parent_aid
     *
     * @param parentAid the value for videocomment.parent_aid
     *
     * @mbg.generated Mon Mar 09 09:02:16 CST 2020
     */
    public void setParentAid(Long parentAid) {
        this.parentAid = parentAid;
    }
}