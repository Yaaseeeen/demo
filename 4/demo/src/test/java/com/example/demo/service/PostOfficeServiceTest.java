package com.example.demo.service;

import com.example.demo.entity.PostOffice;
import com.example.demo.repository.PostOfficeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PostOfficeServiceTest {

    @Mock
    private PostOfficeRepository postOfficeRepository;

    private PostOfficeService postOfficeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        postOfficeService = new PostOfficeService(postOfficeRepository);
    }

    @Test
    void create_ShouldReturnCreatedPostOffice() {
        
        PostOffice postOffice = new PostOffice();
        when(postOfficeRepository.save(postOffice)).thenReturn(postOffice);

       
        PostOffice createdPostOffice = postOfficeService.create(postOffice);

        
        assertEquals(postOffice, createdPostOffice);
        verify(postOfficeRepository, times(1)).save(postOffice);
    }

    @Test
    void findById_WithValidId_ShouldReturnPostOffice() {
        
        Long id = 1L;
        PostOffice postOffice = new PostOffice();
        when(postOfficeRepository.findById(id)).thenReturn(Optional.of(postOffice));

       
        PostOffice foundPostOffice = postOfficeService.findById(id);

        
        assertEquals(postOffice, foundPostOffice);
        verify(postOfficeRepository, times(1)).findById(id);
    }

    @Test
    void findById_WithInvalidId_ShouldThrowNoSuchElementException() {
        
        Long id = 1L;
        when(postOfficeRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> postOfficeService.findById(id));
        verify(postOfficeRepository, times(1)).findById(id);
    }

    @Test
    void getAll_ShouldReturnAllPostOffices() {
        
        List<PostOffice> postOffices = new ArrayList<>();
        postOffices.add(new PostOffice());
        postOffices.add(new PostOffice());
        when(postOfficeRepository.findAll()).thenReturn(postOffices);

       
        List<PostOffice> allPostOffices = postOfficeService.getAll();

        
        assertEquals(postOffices, allPostOffices);
        verify(postOfficeRepository, times(1)).findAll();
    }

}