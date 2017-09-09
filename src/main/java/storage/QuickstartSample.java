package storage;

// Imports the Google Cloud client library
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.google.api.services.storage.model.StorageObject;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

public class QuickstartSample {
    public static void main(String... args) throws Exception {

        StorageOptions options = StorageOptions.newBuilder()
            .setProjectId("spec-api")
            .build();

        Storage storage = options.getDefaultInstance().getService();

        // The name for the new bucket
        String bucketName = "jonsnow";

        // Get the  bucket
        Bucket bucket = storage.get(bucketName);


        File initialFile = new File("/Users/vthiagarajan/Documents/WorkSpaces/HomeawayMavenProjects/cloud-storage/src/main/resources/Imagine-Dragons.mp3");
        InputStream targetStream = new FileInputStream(initialFile);
        Blob blob = bucket.create("dragon.mp3", targetStream, "audio/mp3");

        System.out.printf("File %s created.%n", "https://storage.googleapis.com/jonsnow/"+blob.getName());

        BlobId blobId = BlobId.of(bucketName, blob.getName(), null);
        Acl acl = storage.createAcl(blobId, Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));

        System.out.println(acl.getEntity().toString());
    }
}