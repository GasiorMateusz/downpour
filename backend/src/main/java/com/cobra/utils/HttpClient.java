package com.cobra.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

@Slf4j
public class HttpClient {

    static CloseableHttpClient client = HttpClients.createDefault();

    public static JsonObject sendGetRequest(String url) {
        try {
            HttpGet request = new HttpGet(url);
            log.info(String.valueOf(request));
            try (CloseableHttpResponse response = client.execute(request)) {
                log.info(response.toString());
                String jsonResponse = EntityUtils.toString(response.getEntity());
                Gson gson = new Gson();
                return gson.fromJson(jsonResponse, JsonObject.class);
            }
        } catch (Exception e) {
            log.error("Error while sending request on url : " + url + "\n" + e);
            return null;
        }
    }

}
