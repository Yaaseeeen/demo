package com.example.demo.service;

import com.example.demo.entity.PostOffice;
import com.example.demo.repository.PostOfficeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PostOfficeService {
    private final PostOfficeRepository postOfficeRepository;

    public PostOffice create(PostOffice postOffice) {
        return postOfficeRepository.save(postOffice);
    }

    public PostOffice findById(long id) {
        return postOfficeRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Post office with id " + id + " not found"));
    }

    public List<PostOffice> getAll() {
        return postOfficeRepository.findAll();
    }
}
