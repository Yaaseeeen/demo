package com.example.demo.controller;

import com.example.demo.entity.PostOffice;
import com.example.demo.service.PostOfficeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class PostOfficeControllerTest {

    @Mock
    private PostOfficeService postOfficeService;

    private PostOfficeController postOfficeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        postOfficeController = new PostOfficeController(postOfficeService);
    }

    @Test
    void create_ShouldReturnCreatedPostOffice() {

        PostOffice postOffice = new PostOffice();
        PostOffice createdPostOffice = new PostOffice();
        when(postOfficeService.create(postOffice)).thenReturn(createdPostOffice);


        PostOffice response = postOfficeController.create(postOffice);


        assertEquals(createdPostOffice, response);
        verify(postOfficeService, times(1)).create(postOffice);
    }

    @Test
    void get_WithValidId_ShouldReturnPostOffice() {

        Long id = 1L;
        PostOffice postOffice = new PostOffice();
        when(postOfficeService.findById(id)).thenReturn(postOffice);


        PostOffice response = postOfficeController.get(id);


        assertEquals(postOffice, response);
        verify(postOfficeService, times(1)).findById(id);
    }

    @Test
    void get_WithInvalidId_ShouldThrowNoSuchElementException() {

        Long id = 1L;
        when(postOfficeService.findById(id)).thenThrow(new NoSuchElementException("Post office with id " + id + " not found"));

        assertThrows(NoSuchElementException.class, () -> postOfficeController.get(id));
        verify(postOfficeService, times(1)).findById(id);
    }

    @Test
    void getAll_ShouldReturnAllPostOffices() {

        List<PostOffice> postOffices = new ArrayList<>();
        postOffices.add(new PostOffice());
        postOffices.add(new PostOffice());
        when(postOfficeService.getAll()).thenReturn(postOffices);


        List<PostOffice> response = postOfficeController.getAll();


        assertEquals(postOffices, response);
        verify(postOfficeService, times(1)).getAll();
    }

    @Test
    void testCreateWhenPostOfficeObjectProvidedThenReturnSamePostOfficeObject() {

        PostOffice postOffice = new PostOffice();
        when(postOfficeService.create(postOffice)).thenReturn(postOffice);


        PostOffice response = postOfficeController.create(postOffice);


        assertEquals(postOffice, response);
        verify(postOfficeService, times(1)).create(postOffice);
    }

    @Test
    void testCreateWhenPostOfficeObjectProvidedThenPostOfficeServiceCreateMethodCalled() {

        PostOffice postOffice = new PostOffice();


        postOfficeController.create(postOffice);


        verify(postOfficeService, times(1)).create(postOffice);
    }
}