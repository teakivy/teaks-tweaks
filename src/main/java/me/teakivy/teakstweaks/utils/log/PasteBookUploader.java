package me.teakivy.teakstweaks.utils.log;

import me.teakivy.teakstweaks.utils.config.Config;
import okhttp3.*;

import java.io.IOException;

public class PasteBookUploader {

    private static final String API_URL = "https://api.paste.teakstweaks.com/upload";
    private static final MediaType MEDIA_TYPE_TEXT = MediaType.parse("text/plain");

    private static final OkHttpClient client = new OkHttpClient();
    private static final long EXPIRES = 30 * 24 * 60 * 60 * 1000L; // 30 days
    private static final long DEV_EXPIRES = 20 * 60 * 1000L; // 20 minutes

    /**
     * Uploads the provided text to PasteBook and returns the result URL.
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
                .addHeader("expires", Config.isDevMode() ? String.valueOf(DEV_EXPIRES) : String.valueOf(EXPIRES))
                .build();

        // Execute the request and get the response
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                // Return the URL if the response is successful
                return "https://paste.teakstweaks.com/p/" + response.body().string();
            } else {
                throw new IOException("Request failed with status code: " + response.code());
            }
        }
    }
}
