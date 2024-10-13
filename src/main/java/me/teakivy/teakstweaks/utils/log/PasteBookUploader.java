package me.teakivy.teakstweaks.utils.log;

import okhttp3.*;

import java.io.IOException;

public class PasteBookUploader {

    private static final String API_URL = "https://pastebook.dev/api/upload";
    private static final MediaType MEDIA_TYPE_TEXT = MediaType.parse("text/plain");

    private static final OkHttpClient client = new OkHttpClient();

    /**
     * Uploads the provided text to Pastebook and returns the result URL.
     *
     * @param text  The content to be uploaded
     * @param title The title of the post
     * @return The URL of the uploaded content
     * @throws IOException If an error occurs during the request
     */
    public static String uploadText(String text, String title) throws IOException {
        // Create request body with the provided text
        RequestBody body = RequestBody.create(text, MEDIA_TYPE_TEXT);

        // Build the request
        Request request = new Request.Builder()
                .url(API_URL)
                .post(body)
                .addHeader("Content-Type", "text/plain")
                .addHeader("title", title)
                .addHeader("unlisted", "true")
//                .addHeader("expires", "2592000000")
                .addHeader("expires", "3600000")
                .build();

        // Execute the request and get the response
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                // Return the URL if the response is successful
                return response.body().string();
            } else {
                throw new IOException("Request failed with status code: " + response.code());
            }
        }
    }
}
