package com.jxnd.yuhaojun.blog.coach;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Data
public class HotTagsTasksCoach {
    private List<Map.Entry<String, Integer>> TagList;

    //将Map转换为List并排序
    public List<Map.Entry<String, Integer>> sortByMap(Map<String, Integer> map) {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        Comparator<Map.Entry<String, Integer>> comparator = Comparator.comparing(Map.Entry::getValue);
        list.sort(comparator.reversed());
        return list;
    }
}