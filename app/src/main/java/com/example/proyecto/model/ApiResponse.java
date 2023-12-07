package com.example.proyecto.model;
import java.util.List;

public class ApiResponse {
    private String msg;
    private Data data;

    public ApiResponse(String msg, Data data) {
        this.msg = msg;
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        private List<Feed> feeds;

        public Data(List<Feed> feeds) {
            this.feeds = feeds;
        }

        public List<Feed> getFeeds() {
            return feeds;
        }

        public void setFeeds(List<Feed> feeds) {
            this.feeds = feeds;
        }
    }
}
