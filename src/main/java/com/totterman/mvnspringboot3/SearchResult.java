package com.totterman.mvnspringboot3;

public record SearchResult(String kind, String etag,
    SearchId id, SearchSnippet snippet) {
    
}
