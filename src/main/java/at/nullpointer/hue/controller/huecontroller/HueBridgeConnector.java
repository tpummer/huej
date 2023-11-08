package at.nullpointer.hue.controller.huecontroller;

import lombok.AllArgsConstructor;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Sends commands to the Hue bridge.
 */
@AllArgsConstructor
public class HueBridgeConnector {
    private String base;

    public void setLampState(int lamp, String json) throws IOException, HueException {
        URL url = new URL(base + "/lights/" + lamp + "/state");
        System.out.println(url);

        HttpURLConnection connection = null;

        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");

            OutputStreamWriter os = new OutputStreamWriter(connection.getOutputStream());
            os.write(json);
            os.close();

            int status = connection.getResponseCode();

            if (status != HttpURLConnection.HTTP_OK)
                throw new HueException("Bridge returned status " + status);

            JsonReader reader = Json.createReader(connection.getInputStream());
            JsonArray array = reader.readArray();
            reader.close();

            JsonObject object = array.getJsonObject(0);

            if (object.getJsonObject("success") == null)
                throw new HueException("Bridge returned an error: " + object);
        } finally {
            if (connection != null)
                connection.disconnect();
        }
    }
}
