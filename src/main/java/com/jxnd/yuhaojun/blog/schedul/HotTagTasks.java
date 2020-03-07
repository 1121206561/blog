package com.jxnd.yuhaojun.blog.schedul;

import com.jxnd.yuhaojun.blog.coach.HotTagsTasksCoach;
import com.jxnd.yuhaojun.blog.dao.QuestionDAO;
import com.jxnd.yuhaojun.blog.model.Question;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
public class HotTagTasks {
    @Autowired
    private QuestionDAO questionDAO;
    @Autowired
    private HotTagsTasksCoach hotTagsTasksCoach;


    @Scheduled(fixedRate = 1000 * 60 * 60)
    public void reportCurrentTime() {
        int offset = 0;
        int limit = 5;

        List<Question> questionList = new ArrayList<>();
        Map<String, Integer> map = new LinkedHashMap<>();
        while (offset == 0 || questionList.size() == limit) {
            questionList = questionDAO.select(offset, limit);
            for (Question question : questionList) {
                String[] Tags = question.getTag().split(",");
                for (String Tag : Tags) {
                    Integer TagHotCount = map.get(Tag);
                    if (TagHotCount == null) {
                        map.put(Tag, 5 + question.getComment_count() + (question.getView_count() / 5));
                    } else {
                        map.put(Tag, TagHotCount + 5 + question.getComment_count() + (question.getView_count() / 10));
                    }
                }
            }
            offset += limit;
        }
        List list = hotTagsTasksCoach.sortByMap(map);
        if (list.size() > 6) {
            hotTagsTasksCoach.setTagList(list.subList(0, 5));
        } else {
            hotTagsTasksCoach.setTagList(list);
        }

    }
}
