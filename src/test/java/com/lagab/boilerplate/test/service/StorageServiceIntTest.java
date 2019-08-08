package com.lagab.boilerplate.test.service;

import com.lagab.boilerplate.Boot;
import com.lagab.boilerplate.config.StorageProperties;
import com.lagab.boilerplate.service.StorageService;
import com.lagab.boilerplate.service.impl.DefaultStorageService;
import com.lagab.boilerplate.store.Store;
import com.lagab.boilerplate.store.validator.FileValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Boot.class)
public class StorageServiceIntTest {

    private static String pathTest = "W:\\Dev\\spring-boilerplate/test-lagab";

    private static String fileTest = "storage/spring-boot-project-logo.png";
    private static  int fileSizeTest = 5897;

    @Autowired
    private StorageProperties storageProperties;

    @Autowired
    private Store store;
    @Autowired
    private FileValidator fileValidator;


    private StorageService storageService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        storageService = new DefaultStorageService(store,fileValidator);
    }

    @Test
    public void testUploadFile() throws Exception {
        final InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileTest);
        final MockMultipartFile avatar = new MockMultipartFile("file", "test.png", "image/png", inputStream);
        storageService.putFile(pathTest,avatar,"test.png");
    }

    @Test
    public void testDownloadFile() throws Exception {
        String path = Thread.currentThread().getContextClassLoader().getResource(fileTest).getFile().substring(1);
        File file = storageService.getFile(path);
        Assert.assertTrue(file != null);
        Assert.assertEquals(file.length(),fileSizeTest);
    }

}
