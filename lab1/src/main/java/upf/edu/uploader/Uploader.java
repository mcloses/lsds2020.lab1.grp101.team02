package upf.edu.uploader;

import java.util.List;

import com.amazonaws.services.s3.AmazonS3;

public interface Uploader {

    /**
     * Uploads a list of files to the target specified through its implementation
     * @param files the files to upload
     */
    void upload(List<String> files);
}
