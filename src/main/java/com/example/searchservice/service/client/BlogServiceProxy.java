package com.example.searchservice.service.client;

import com.example.searchservice.dto.BlogDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "blog-service")
public interface BlogServiceProxy {

    @GetMapping(value = "blog-service/blogs", consumes = "application/json")
    ResponseEntity<List<BlogDto>> getAllBlogs();
}
