package com.github.lucenehw;

import org.apache.lucene.queryparser.classic.ParseException;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Indexer indexer = new Indexer("lucene-index", "documents");
            indexer.createIndex();

            Scanner scanner = new Scanner(System.in);
            System.out.println("\nFields allowed: 'titolo', 'contenuto");
            System.out.println("Example 1: field term1 term2");
            System.out.println("Example 2: field \"term1 term2\"");
            System.out.println("Enter query:");
            String query = scanner.nextLine();

            Searcher searcher = new Searcher("lucene-index");
            searcher.search(query);
            searcher.close();
        } catch (IOException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
