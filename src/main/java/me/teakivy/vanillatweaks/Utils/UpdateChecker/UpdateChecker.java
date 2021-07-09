package me.teakivy.vanillatweaks.Utils.UpdateChecker;

import me.teakivy.vanillatweaks.Main;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;

public class UpdateChecker {

    private Main main;
    private int resourceId;

    public UpdateChecker(Main plugin, int resourceId) {
        this.main = plugin;
        this.resourceId = resourceId;
    }



    public String getLatestVersion() throws IOException, ParseException {
        String url = "https://api.spiget.org/v2/resources/" + resourceId + "/versions";
        try {
            @SuppressWarnings("deprecation")
            String nameJson = IOUtils.toString(new URL(url));
            JSONArray list = (JSONArray) JSONValue.parseWithException(nameJson);
            JSONObject latest = (JSONObject) list.get(list.size()-1);
            return latest.get("name").toString();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}


