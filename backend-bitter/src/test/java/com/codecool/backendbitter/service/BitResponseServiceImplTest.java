package com.codecool.backendbitter.service;

import com.codecool.backendbitter.controller.dto.BitResponseDTO;
import com.codecool.backendbitter.controller.dto.NewBitResponseDTO;
import com.codecool.backendbitter.model.BitResponse;
import com.codecool.backendbitter.repository.BitRepository;
import com.codecool.backendbitter.repository.BitResponseRepository;
import com.codecool.backendbitter.utility.BitResponseMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.sql.Timestamp;
import java.util.UUID;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ContextConfiguration
class BitResponseServiceImplTest {

    @Mock
    private BitResponseRepository bitResponseRepository;
    @Mock
    private BitResponseMapper bitResponseMapper;
    @InjectMocks
    private BitResponseServiceImpl bitResponseService;

    @Test
    public void test_addBitResponseWithValidData() {
    }

}