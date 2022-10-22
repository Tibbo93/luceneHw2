package com.github.lucenehw;

import org.apache.lucene.queryparser.classic.ParseException;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Indexer indexer = new Indexer("lucene-index", "documents");
            long start = System.nanoTime();
            indexer.createIndex();
            long end = System.nanoTime();
            System.out.println("Indexing time: " + ((end - start)/1000000) + "ms");

            Scanner scanner = new Scanner(System.in);
            System.out.println("\nFields allowed: 'titolo', 'contenuto");
            System.out.println("Example 1: field term1 term2");
            System.out.println("Example 2: field \"term1 term2\"");
            System.out.println("Enter query:");
            String query = scanner.nextLine();

            Searcher searcher = new Searcher("lucene-index");
            start = System.nanoTime();
            searcher.search(query);
            end = System.nanoTime();
            System.out.println("Searching time: " + ((end - start)/1000000) + "ms");
            searcher.close();
        } catch (IOException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
