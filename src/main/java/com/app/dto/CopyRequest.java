package com.app.dto;


public class CopyRequest {

    private String sourceBucket;
    private String targetBucket;
    private String objectKey;

    public CopyRequest() {
    }

    public String getSourceBucket() {
        return sourceBucket;
    }

    public void setSourceBucket(String sourceBucket) {
        this.sourceBucket = sourceBucket;
    }

    public String getTargetBucket() {
        return targetBucket;
    }

    public void setTargetBucket(String targetBucket) {
        this.targetBucket = targetBucket;
    }

    public String getObjectKey() {
        return objectKey;
    }

    public void setObjectKey(String objectKey) {
        this.objectKey = objectKey;
    }
}