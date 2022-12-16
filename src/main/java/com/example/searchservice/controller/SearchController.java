package com.example.searchservice.controller;

import com.example.searchservice.dto.BlogDto;
import com.example.searchservice.service.client.BlogServiceProxy;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/search-service")
@CrossOrigin(origins = "http://localhost:4200")
public class SearchController {

    private final BlogServiceProxy blogServiceProxy;

    @GetMapping
    @CircuitBreaker(name = "searchBlogsByKey", fallbackMethod = "searchBlogsByKeyFallback")
    public ResponseEntity<List<BlogDto>> getBlogsBySearchKey(@RequestParam(defaultValue = "") String searchKey){
        List<BlogDto> blogDtoList = blogServiceProxy.getAllBlogs().getBody();

        if(searchKey.equals("")){
            return ResponseEntity.ok(blogDtoList);
        } else {
            List<BlogDto> searchedBlogs = blogDtoList.stream().filter(b -> b.getTitle().contains(searchKey))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(searchedBlogs);
        }
    }

    public ResponseEntity<List<BlogDto>> searchBlogsByKeyFallback(String searchKey, Throwable throwable) {
        List<BlogDto> blogDtoList = blogServiceProxy.getAllBlogs().getBody();
        log.warn("Could not load the list of blogs!");
        return ResponseEntity.ok().body(blogDtoList);
    }

}
