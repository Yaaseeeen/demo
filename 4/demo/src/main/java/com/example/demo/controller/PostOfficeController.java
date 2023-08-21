package com.example.demo.controller;

import com.example.demo.entity.PostOffice;
import com.example.demo.service.PostOfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/postoffice")
public class PostOfficeController {

    private final PostOfficeService postOfficeService;

    @PostMapping
    public PostOffice create(@RequestBody PostOffice postOffice) {
        return postOfficeService.create(postOffice);
    }

    @GetMapping("/{id}")
    public PostOffice get(@PathVariable Long id) {
        return postOfficeService.findById(id);
    }

    @GetMapping
    public List<PostOffice> getAll() {
        return postOfficeService.getAll();
    }

}
