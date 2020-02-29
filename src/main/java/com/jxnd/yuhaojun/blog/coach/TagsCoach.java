package com.jxnd.yuhaojun.blog.coach;

import com.jxnd.yuhaojun.blog.dto.TagsDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TagsCoach {
    public static List<TagsDTO> showTags() {
        ArrayList<TagsDTO> tagsDTOArrayList = new ArrayList<>();

        TagsDTO language = new TagsDTO();
        language.setCategoryName("开发语言");
        language.setTags(Arrays.asList("c", "c++", "c#", "object c", "visual basic", "java", "javascript", "python", "luna", "dephi", "forth", "html"));
        tagsDTOArrayList.add(language);

        TagsDTO DB = new TagsDTO();
        DB.setCategoryName("数据库");
        DB.setTags(Arrays.asList("MySql", "H2", "Oracle", "redis", "Hive", "Solr", "IBM", "MongoDB", "ELK", "SAP"));
        tagsDTOArrayList.add(DB);

        TagsDTO frame = new TagsDTO();
        frame.setCategoryName("平台框架");
        frame.setTags(Arrays.asList("Spring", "Hibernate", "Struts", "Mybatis", "Element", "Swiper", "better-scroll", "Taro", "JF", "SpringBoot"));
        tagsDTOArrayList.add(frame);

        TagsDTO tool = new TagsDTO();
        tool.setCategoryName("开发工具");
        tool.setTags(Arrays.asList("Cross", "platform", "free", "open", "source", "Git", "Navicat", "eclipse", "IDEA"));
        tagsDTOArrayList.add(tool);

        TagsDTO middleware = new TagsDTO();
        middleware.setCategoryName("中间件");
        middleware.setTags(Arrays.asList("RabbitMQ", "RocketMQ", "ActiveMQ", "Kafka"));
        tagsDTOArrayList.add(middleware);

        return tagsDTOArrayList;
    }

    public static boolean isTags(String tags) {
        String[] str = tags.split(",");
        List<TagsDTO> tagsDTOList = showTags();
        ArrayList<String> tagList = new ArrayList<>();
        for (TagsDTO tagsDTO : tagsDTOList) {
            for (String tag : tagsDTO.getTags()) {
                tagList.add(tag);
            }
        }
        int tagTrue = 0;
        for (String tag : str) {
            for (String tagss : tagList) {
                if (tag.equals(tagss)) {
                    tagTrue = tagTrue + 1;
                }
            }
        }
        if (tagTrue == str.length) {
            return true;
        } else {
            return false;
        }
    }
}
