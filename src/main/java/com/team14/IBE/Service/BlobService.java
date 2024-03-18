//package com.team14.IBE.Service;
//import com.azure.storage.blob.BlobClient;
//import com.azure.storage.blob.BlobContainerClient;
//import com.azure.storage.blob.BlobServiceClient;
//import com.azure.storage.blob.models.BlobItem;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class BlobService {
//
//    @Autowired
//    private BlobServiceClient blobServiceClient;
//
//    @Value("${azure.storage.blob.container-name}")
//    private String containerName;
//
//    public List<String> listBlobs(String containerName) {
//        List<String> blobNames = new ArrayList<>();
//        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(this.containerName);
//        for (BlobItem blobItem : containerClient.listBlobs()) {
//            blobNames.add(blobItem.getName());
//        }
//        return blobNames;
//    }
//
//    public String uploadBlob(String blobName, MultipartFile file) {
//        try {
//            BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
//            BlobClient blobClient = containerClient.getBlobClient(blobName);
//            blobClient.upload(file.getInputStream(), file.getSize(), true);
//        } catch (Exception e) {
//            // Handle upload error
//            throw new RuntimeException("Failed to upload blob: " + e.getMessage());
//        }
//        return blobName;
//    }
//
//    public InputStream downloadBlob(String blobName, String name) {
//        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
//        BlobClient blobClient = containerClient.getBlobClient(blobName);
//        return blobClient.openInputStream();
//    }
//
//    public boolean deleteBlob(String blobName, String name) {
//        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
//        containerClient.getBlobClient(blobName).delete();
//        return false;
//    }
//}
