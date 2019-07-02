package com.zys.day8.fastdfs;

import lombok.Data;

@Data
public class FastDFSFile {
    private String name;
    private byte[] content;
    private String ext;
    private String md5;
    private String author;

    public FastDFSFile(String name,byte[] content,String ext){
        this.name = name;
        this.content = content;
        this.ext = ext;
    }
}
