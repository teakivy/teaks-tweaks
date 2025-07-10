package me.teakivy.teakstweaks.utils.log;


import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class PasteUploader {

    private static final String PASTEBIN_API_URL = "https://pastebin.com/api/api_post.php";

    // Your Pastebin developer API key here
    private static final String PASTEBIN_DEV_KEY = "-UgTukreTb_j3dMQQ0Pe26LOZjjH2PHI";

    /**
     * Uploads the provided text to Pastebin and returns the paste URL.
     *
     * @param text  The content to be uploaded
     * @param title The title of the paste
     * @return The URL of the uploaded paste
     * @throws IOException If an error occurs during the request
     */
    public static String uploadToPastebin(String text, String title) throws IOException {
        if (PASTEBIN_DEV_KEY == null) {
            throw new IllegalStateException("PASTEBIN_DEV_KEY is not set in environment variables!");
        }

        String data = "api_dev_key=" + URLEncoder.encode(PASTEBIN_DEV_KEY, "UTF-8") +
                "&api_option=paste" +
                "&api_paste_code=" + URLEncoder.encode(text, "UTF-8") +
                "&api_paste_name=" + URLEncoder.encode(title, "UTF-8") +
                "&api_paste_private=1" +
                "&api_paste_expire_date=N";

        URL url = new URL(PASTEBIN_API_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            os.write(data.getBytes());
        }

        try (Scanner scanner = new Scanner(conn.getInputStream())) {
            return scanner.useDelimiter("\\A").next();
        }
    }

    private static String encode(String value) {
        return value.replace("&", "%26")
                .replace("=", "%3D")
                .replace("+", "%2B");  // very basic; consider using URLEncoder in real code
    }
}
