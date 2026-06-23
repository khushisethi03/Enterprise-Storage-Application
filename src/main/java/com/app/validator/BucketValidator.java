package com.app.validator;

import com.app.exception.InvalidFileException;

public final class BucketValidator {

    private BucketValidator() {
    }

    public static void validate(String bucketName) {

        if (bucketName == null || bucketName.trim().isEmpty()) {
            throw new InvalidFileException("Bucket name cannot be empty.");
        }

        if (bucketName.length() < 3 || bucketName.length() > 63) {
            throw new InvalidFileException(
                    "Bucket name must be between 3 and 63 characters.");
        }

        if (!bucketName.matches("^[a-z0-9][a-z0-9.-]*[a-z0-9]$")) {
            throw new InvalidFileException(
                    "Bucket name contains invalid characters.");
        }

        if (bucketName.contains("..")) {
            throw new InvalidFileException(
                    "Bucket name cannot contain consecutive dots.");
        }

        if (bucketName.contains(".-") || bucketName.contains("-.")) {
            throw new InvalidFileException(
                    "Bucket name cannot contain '.-' or '-.'");
        }

        if (bucketName.matches("^\\d+\\.\\d+\\.\\d+\\.\\d+$")) {
            throw new InvalidFileException(
                    "Bucket name cannot be an IP address.");
        }
    }
}