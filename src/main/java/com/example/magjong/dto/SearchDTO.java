package com.example.magjong.dto;

import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;

/**
 * Author 雷铭涛
 * Date:2020-03-04
 * vsersion 1.0
 */
@Data
public class SearchDTO {
    //唯一值  主键
    @Field
    private String id;
    //创建人id
    @Field("question_creator")
    private String questionCreator;
    //回复数
    @Field("question_comment_cont")
    private String questionCommentCont;
    //标题
    @Field
    private String questionTitle;
    @Field("question_gmt_create")
    private String questionGmtCreate;
    private String userSrc;
}
