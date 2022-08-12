package vttp2022.ssf.Assessment.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import vttp2022.ssf.Assessment.models.News;
import vttp2022.ssf.Assessment.services.NewsService;

@RestController
@RequestMapping(path = "/news", produces = MediaType.APPLICATION_JSON_VALUE)
public class NewsRestController {

    @Autowired
    private NewsService newsSvc;

    @GetMapping(value = "{gid}")
    public ResponseEntity<String> getArticles(@PathVariable String gid) {
        Optional<News> opt = newsSvc.getArticlesById(gid);

        if (opt.isEmpty()) {
            JsonObject err = Json.createObjectBuilder()
                .add("error", "Cannot find news article %s".formatted(gid))
                .build();
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(err.toString());
        
        }
        return null;
    }
}
