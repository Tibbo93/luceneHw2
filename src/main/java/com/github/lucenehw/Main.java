package com.github.lucenehw;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Fields allowed: 'titolo', 'contenuto");
        System.out.println("Example 1: field term1 term2");
        System.out.println("Example 2: field \"term1 term2\"");
        System.out.println("Enter query:");
        String query = scanner.nextLine();

        Searcher searcher = new Searcher("lucene-index");
        searcher.search(query);
        searcher.close();
    }
}
