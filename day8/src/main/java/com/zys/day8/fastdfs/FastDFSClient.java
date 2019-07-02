package com.zys.day8.fastdfs;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import org.slf4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FastDFSClient {
    private static Logger logger = LoggerFactory.getLogger(FastDFSFile.class);

    static {
        try {
            String filePath = new ClassPathResource("fdsf_client.conf").getFile().getAbsolutePath();
            //读取配置文件，并初始化对应的属性
            ClientGlobal.init(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String[] upload(FastDFSFile file){
        logger.info("File Name: "+file.getName()+",File Length: "+file.getContent().length);
        /**
         * NameValuePair主要储存一些文件的基础信息，如作者、创建时间等；
         * 文件属性信息
         */
        NameValuePair[] meta_list = new NameValuePair[1];
        meta_list[0] = new NameValuePair("author",file.getAuthor());
        //开始时间
        Long startTime = System.currentTimeMillis();
        String[] uploadResults = null;
        StorageClient storageClient = null;
        try {
            //获取storage客户端
            storageClient = getStorageClient();
            //上传文件
            uploadResults = storageClient.upload_file(file.getContent(),file.getExt(),meta_list);
        } catch (IOException e) {
            logger.error("IO Exception When Uploading The File:"+file.getName(),e);
        } catch (MyException e) {
            logger.error("Non Exception When Uploading The File:"+file.getName(),e);
        }
        logger.info("upload file time used:"+(System.currentTimeMillis() - startTime)+"ms");
        //验证上传结果
        if(uploadResults == null && storageClient != null){
            logger.error("upload file fail,error code:"+storageClient.getErrorCode());
        }
        //上传文件成功会返回groupName
        logger.info("upload file successfully! group_name:"+uploadResults[0]+"remoteFileName:"+uploadResults[1]);
        return uploadResults;
    }

    /**
     * 获取文件信息
     * @param groupName 卷名
     * @param remoteFileName 文件名
     * @return
     */
    public static FileInfo getFile(String groupName,String remoteFileName){
        try {
            StorageClient storageClient = getStorageClient();
            return storageClient.get_file_info(groupName,remoteFileName);
        } catch (IOException e) {
            logger.error("IO Exception: Get File from Fast DFS failed",e);
        } catch (MyException e) {
            logger.error("Non IO Exception: Get File from Fast DFS failed",e);
        }
        return null;
    }

    /**
     * 获取文件的字节流并返回
     * @param groupName
     * @param remoteFile
     * @return
     */
    public static InputStream downFile(String groupName,String remoteFile){
        try {
            StorageClient storageClient = getStorageClient();
            byte[] fileByte = storageClient.download_file(groupName,remoteFile);
            InputStream is = new ByteArrayInputStream(fileByte);
            return is;
        } catch (IOException e) {
            logger.error("IO Exception: Get File from Fast DFS failed", e);
        } catch (MyException e) {
            logger.error("Non IO Exception: Get File from Fast DFS failed", e);
        }
        return null;
    }

    public static void deleteFile(String groupName,String remoteFile) throws Exception {
        StorageClient storageClient = getStorageClient();
        int i = storageClient.delete_file(groupName,remoteFile);
        logger.info("delete file successfully!!!"+i);
    }

    public static StorageClient getStorageClient() throws IOException {
        TrackerServer trackerServer = getTrackerServer();
        StorageClient storageClient = new StorageClient(trackerServer,null);
        return storageClient;
    }

    public static TrackerServer getTrackerServer() throws IOException {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        return trackerServer;
    }

    public static String getTrackerURL() throws IOException {
        return "http://"+getTrackerServer().getInetSocketAddress().getHostString()+":"+ClientGlobal.getG_tracker_http_port()+"/";
    }

    public static StorageServer[] getStoreStorages(String groupName)
            throws IOException {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        return trackerClient.getStoreStorages(trackerServer, groupName);
    }
}
