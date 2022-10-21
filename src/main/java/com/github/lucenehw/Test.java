package com.github.lucenehw;

public class Test {
    public static void main(String[] args) {
        Indexer indexer = new Indexer("lucene-index", "documents");
        indexer.createIndex();
    }
}
