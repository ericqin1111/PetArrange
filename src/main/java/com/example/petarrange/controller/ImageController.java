package com.example.petarrange.controller;

import com.example.petarrange.entity.Image;
import com.example.petarrange.persistence.ImagesMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;

@Controller
@RequestMapping("/image")
public class ImageController {

    @Autowired
    ImagesMapper imagesMapper;

    @RequestMapping("/gif/{id}")
    public void getGif(@PathVariable String id, HttpServletResponse response) throws IOException {


        byte[] img = imagesMapper.getDataByItemid(id)[0];





        if (img!= null) {
            response.setContentType("image/png");
            try(InputStream in = new ByteArrayInputStream(img);
                OutputStream out = response.getOutputStream()) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
