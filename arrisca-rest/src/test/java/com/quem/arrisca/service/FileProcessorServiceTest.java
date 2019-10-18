package com.quem.arrisca.service;

import com.quem.arrisca.repository.FileRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@Ignore
@RunWith(MockitoJUnitRunner.class)
public class FileProcessorServiceTest {

    @Mock
    FileRepository fileRepository;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void queryFile() {
        FileProcessorService service = new FileProcessorService(fileRepository);
        service.queryFile("");
    }
}