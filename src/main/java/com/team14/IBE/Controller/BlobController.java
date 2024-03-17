package com.team14.IBE.Controller;

import com.team14.IBE.Service.BlobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/blobs")
public class BlobController {
    @Autowired
    private BlobService blobService;

    @GetMapping("/{containerName}")
    public List<String> listBlobs(@PathVariable String containerName) {
        return blobService.listBlobs(containerName);
    }

    @PostMapping("/{containerName}")
    public String uploadBlob(@PathVariable String containerName, @RequestParam MultipartFile file) {
        return blobService.uploadBlob(containerName, file);
    }

    @GetMapping("/{containerName}/{blobName}")
    public ResponseEntity<byte[]> downloadBlob(@PathVariable String containerName, @PathVariable String blobName) throws IOException {
        byte[] data = blobService.downloadBlob(containerName, blobName).readAllBytes();
        if (data != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentLength(data.length);
            headers.set("Content-Disposition", "attachment; filename=" + blobName);
            return new ResponseEntity<>(data, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{containerName}/{blobName}")
    public ResponseEntity<String> deleteBlob(@PathVariable String containerName, @PathVariable String blobName) {
        boolean deleted = blobService.deleteBlob(containerName, blobName);
        if (deleted) {
            return new ResponseEntity<>("Deleted blob " + blobName, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Blob " + blobName + " not found", HttpStatus.NOT_FOUND);
        }
    }
}
