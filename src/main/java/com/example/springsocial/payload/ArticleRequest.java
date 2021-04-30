package com.example.springsocial.payload;

public class ArticleRequest {
    private int offsets;
    private int limit;

    public int getOffsets() {
        return offsets;
    }

    public void setOffsets(int offsets) {
        this.offsets = offsets;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
