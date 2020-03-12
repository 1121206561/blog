package com.jxnd.yuhaojun.blog.controller;

import com.jxnd.yuhaojun.blog.exception.CustomizeErrorCode;
import com.jxnd.yuhaojun.blog.exception.CustomizeException;
import com.jxnd.yuhaojun.blog.model.Person;
import com.jxnd.yuhaojun.blog.model.User;
import com.jxnd.yuhaojun.blog.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("person")
    public String person(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            throw new CustomizeException(CustomizeErrorCode.NO_LOGIN);
        }
        List<Person> personList = personService.selectAll(user.getLogin());
        if (personList.size() > 0) {
            Person person = personList.get(0);
            model.addAttribute("person", person);
            return "person";
        } else {
            model.addAttribute("person", new Person());
            return "InsertPerson";
        }
    }

    @PostMapping("/insertPerson")
    public String insertPerson(Person person, HttpServletRequest request) {
        if (person.getId() == null) {
            personService.insert(person, request);
        } else {
            personService.update(person, request);
        }
        return "redirect:/person";
    }

    @GetMapping("/updatePerson")
    public String updatePerson(Long id, Model model) {
        Person person = personService.selectById(id);
        model.addAttribute("person", person);
        return "InsertPerson";
    }
}
