package com.zys.day7.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class uploadController {
    //上传文件地址,注意路径后的\\
    private static String UPLOADED_FOLDER = "C:\\Users\\hasee\\Desktop\\tmp\\";

    @GetMapping("/")
    public String index(){
        return "upload";
    }

    //上传业务
    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file")MultipartFile file, RedirectAttributes redirectAttributes){
        if(file.isEmpty()){
            redirectAttributes.addFlashAttribute("message","Please select a file to upload");
            return "redirect:upload_status";
        }
        try {
            byte[] bytes = file.getBytes();
            //UPLOADED_FOLDER 文件本地存储地址
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path,bytes);
            redirectAttributes.addFlashAttribute("message","You successfully uploaded '"+file.getOriginalFilename()+ "'");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/upload_status";
    }

    @GetMapping("/more")
    public String uploadMore(){
        return "upload_more";
    }

    @PostMapping("/uploadMore")
    public String moreFileUpload(@RequestParam("file") MultipartFile[] files,
                                 RedirectAttributes redirectAttributes) {
        if (files.length==0) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:upload_status";
        }
        for(MultipartFile file:files){
            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
                Files.write(path, bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        redirectAttributes.addFlashAttribute("message", "You successfully uploaded all");
        return "redirect:/upload_status";
    }

    @GetMapping("/upload_status")
    public String uploadStatus() {
        return "upload_status";
    }
}
