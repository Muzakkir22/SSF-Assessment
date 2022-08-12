package vttp2022.ssf.Assessment.services;

import java.io.Reader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;


import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp2022.ssf.Assessment.models.News;
import vttp2022.ssf.Assessment.repositories.NewsRepository;

@Service
public class NewsService {

    private static final String URL = "https://min-api.cryptocompare.com/data/v2/news/";

    @Autowired
    private NewsRepository newsRepo;


    public List<News> getArticles(String id) {

        Optional<String> opt = newsRepo.get(id);
        String payload;

        System.out.printf(" news: %s\n", id);

        if (opt.isEmpty()) {

            System.out.println("Extracting news from CryptoCompare");

            RequestEntity<Void> req = RequestEntity.get(URL).build();

            RestTemplate template = new RestTemplate();
            ResponseEntity<String> resp;

            resp = template.exchange(req, String.class);

            payload = resp.getBody();
            System.out.println("payload: " + payload);
        } else {
            payload = opt.get();
            System.out.printf("cache: %s\n", payload);
        }

        Reader strReader = new StringReader(payload);
        JsonReader jsonReader = Json.createReader(strReader);
        JsonObject newsResult = jsonReader.readObject();
        JsonArray newsHeadlines = newsResult.getJsonArray("news");
        List<News> list = new LinkedList<>();
        for (int i = 0; i < newsHeadlines.size(); i++) {
            JsonObject jo = newsHeadlines.getJsonObject(i);
            list.add(News.create(jo));
        }
        return list;
    }

    public Optional<News> getArticlesById(String gid) {
        return null;
    }
}
