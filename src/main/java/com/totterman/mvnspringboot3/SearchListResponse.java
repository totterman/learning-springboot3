package com.totterman.mvnspringboot3;

public record SearchListResponse(String kind, String etag,
    String nextPageToken, String prevPageToken,
    PageInfo pageInfo, SearchResult[] items) {
        
    }
