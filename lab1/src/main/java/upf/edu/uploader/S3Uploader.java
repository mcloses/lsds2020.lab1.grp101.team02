/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upf.edu.uploader;

import java.io.File;
import java.util.List;

import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;


/**
 *
 * @author u150391
 */
public class S3Uploader implements Uploader {
    
    private String bucket;
    private String key;
    private String user;

    public S3Uploader(String bucket, String key, String user) {
        this.key = bucket + key;
        this.bucket = bucket;
        this.user = user;
        
    }

    @Override
    public void upload(List<String> files) {
        try {
            AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                    .withRegion(Regions.US_EAST_1)
                    .build();

            if (!s3.doesBucketExist(this.bucket)) { // if the bucket doesn't exist
                s3.createBucket(new CreateBucketRequest(this.bucket)); //create bucket if not exists
                System.out.format("Uploading %s to S3 bucket %s\n", files, this.bucket);
                s3.putObject(this.bucket, this.key, new File(files.get(0))); //upload the file to the bucket
                System.out.format("Uploaded %s to S3 bucket %s\n", files, this.bucket);
            }else{
                if(isMine(s3) ){ //check if the bucket is mine
                    System.out.println("This bucket is already created and you have access to this bucket\n");
                    System.out.format("Uploading %s to S3 bucket %s\n", files,this.bucket);
                    s3.putObject(this.bucket, this.key, new File(files.get(0)));//upload the file to the bucket if bucket exists and I have access to it
                    System.out.format("Uploaded %s to S3 bucket %s\n", files, this.bucket);
                }else{
                    System.out.println("You don't have acces to this bucket\n");
                    System.out.format("The bucket %s, already exists. Change the name of the bucket!\n",this.bucket);
                }
            }
        } catch (AmazonServiceException e) {
            e.printStackTrace();
        } catch (SdkClientException e) {
            e.printStackTrace();
        }
    }
    

    private boolean isMine(AmazonS3 bucket)
    {
        List<Bucket> buckets = bucket.listBuckets();
        for (Bucket b : buckets)
        {
            if (b.getName().equals(this.bucket)) return true;
        }
        return false;
    }

}
