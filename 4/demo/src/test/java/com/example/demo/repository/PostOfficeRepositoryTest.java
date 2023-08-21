package com.example.demo.repository;

import com.example.demo.entity.PostOffice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class PostOfficeRepositoryTest {

    @Autowired
    private PostOfficeRepository postOfficeRepository;

    @Test
    void testSaveAndFindAll() {
        
        PostOffice postOffice1 = new PostOffice();
        postOffice1.setIndex("12345");
        postOffice1.setName("Post Office 1");
        postOffice1.setAddress("Address 1");

        PostOffice postOffice2 = new PostOffice();
        postOffice2.setIndex("67890");
        postOffice2.setName("Post Office 2");
        postOffice2.setAddress("Address 2");

       
        postOfficeRepository.save(postOffice1);
        postOfficeRepository.save(postOffice2);

        List<PostOffice> postOffices = postOfficeRepository.findAll();

        
        assertEquals(2, postOffices.size());
        assertEquals(postOffice1, postOffices.get(0));
        assertEquals(postOffice2, postOffices.get(1));
    }

    // Write similar tests for other repository methods if needed

}