package com.sundaempire.frontend.backend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;

public enum BackendService {
    INSTANCE;

    public interface ResponseHandler {
        void onSuccess(String response);
        void onError(String error);
    }

    private final String baseUrl;

    BackendService() {
        this.baseUrl = "http://localhost:8084";
    }

    private String buildUrl(String path) {
        if (path == null || path.isEmpty()) return baseUrl;
        if (path.startsWith("/")) return baseUrl + path;
        return baseUrl + "/" + path;
    }

    public void getInfo(ResponseHandler handler) {
        send("GET", "/api/info", null, handler);
    }

    public void getHealth(ResponseHandler handler) {
        send("GET", "/api/health", null, handler);
    }

    public void getPlayers(ResponseHandler handler) {
        send("GET", "/api/players", null, handler);
    }

    public void getSaves(ResponseHandler handler) {
        send("GET", "/api/saves", null, handler);
    }

    public void postSave(String jsonBody, ResponseHandler handler) {
        send("POST", "/api/saves", jsonBody, handler);
    }

    private void send(String method, String path, String body, ResponseHandler handler) {
        String url = buildUrl(path);

        Net.HttpRequest request = new Net.HttpRequest(method);
        request.setUrl(url);

        if (body != null) {
            request.setContent(body);
            request.setHeader("Content-Type", "application/json; charset=UTF-8");
        }

        request.setHeader("Accept", "application/json");

        Gdx.net.sendHttpRequest(request, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                int status = httpResponse.getStatus().getStatusCode();
                String result = httpResponse.getResultAsString();
                if (status >= 200 && status < 400) {
                    if (handler != null) handler.onSuccess("HTTP " + status + ": " + result);
                } else {
                    if (handler != null) handler.onError("HTTP " + status + ": " + result);
                }
            }

            @Override
            public void failed(Throwable t) {
                if (handler != null) handler.onError(t.getClass().getSimpleName() + ": " + t.getMessage());
            }

            @Override
            public void cancelled() {
                if (handler != null) handler.onError("cancelled");
            }
        });
    }
}
