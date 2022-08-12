package vttp2022.ssf.Assessment.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vttp2022.ssf.Assessment.models.News;
import vttp2022.ssf.Assessment.services.NewsService;

@Controller
@RequestMapping("/news")
public class NewsController {

    @PostMapping
    public String postNews(@RequestBody MultiValueMap<String, String> form
            , Model model) {

            String name = form.saveArticles("news");
            if (( null == news)) || (news.trim().length() <= 0 ))
            }

    @Autowired
    private NewsService newsSvc;

    @GetMapping
    public String getArticles(Model model, @RequestParam String id) {
        List<News> news = newsSvc.getArticles(id);
        model.addAttribute("id", id);
        model.addAttribute("news", news);
        return "news";
    }
}
