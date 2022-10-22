package com.github.lucenehw;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

public class TestLuceneHw {

    @BeforeAll
    public static void initIndex() throws IOException {
        Path indexPath = Paths.get("lucene-index");
        Files.walk(indexPath)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);

        Indexer indexer = new Indexer("lucene-index", "documents");
        indexer.createIndex();
        indexer.close();
    }

    @DisplayName("Test simple query 1")
    @Test
    public void testSimpleQuery() {
        String query = "titolo intelligenza artificiale";
        System.out.println("\n1) TEST SIMPLE QUERY");
        System.out.println("Query: " + query);
        Searcher searcher = new Searcher("lucene-index");
        searcher.search(query);
        searcher.close();
    }

    @DisplayName("Test simple query 2")
    @Test
    public void testSimpleQuery2() {
        String query = "contenuto intelligenza artificiale";
        System.out.println("\n3) TEST SIMPLE QUERY");
        System.out.println("Query: " + query);
        Searcher searcher = new Searcher("lucene-index");
        searcher.search(query);
        searcher.close();
    }

    @DisplayName("Test phrase query 1")
    @Test
    public void testPhraseQuery() {
        String query = "titolo \"intelligenza artificiale\"";
        System.out.println("\n3) TEST PHRASE QUERY");
        System.out.println("Query: " + query);
        Searcher searcher = new Searcher("lucene-index");
        searcher.search(query);
        searcher.close();
    }

    @DisplayName("Test phrase query 2")
    @Test
    public void testPhraseQuery2() {
        String query = "contenuto \"intelligenza artificiale\"";
        System.out.println("\n4)TEST PHRASE QUERY");
        System.out.println("Query: " + query);
        Searcher searcher = new Searcher("lucene-index");
        searcher.search(query);
        searcher.close();
    }

    @DisplayName("Test query 5")
    @Test
    public void testQuery5() {
        String query = "titolo +ingegneria dei +dati";
        System.out.println("\n5)TEST QUERY");
        System.out.println("Query: " + query);
        Searcher searcher = new Searcher("lucene-index");
        searcher.search(query);
        searcher.close();
    }

    @DisplayName("Test query 6")
    @Test
    public void testQuery6() {
        String query = "contenuto +ingegneria dei +dati";
        System.out.println("\n6)TEST QUERY");
        System.out.println("Query: " + query);
        Searcher searcher = new Searcher("lucene-index");
        searcher.search(query);
        searcher.close();
    }

    @DisplayName("Test query 7")
    @Test
    public void testQuery7() {
        String query = "titolo -ingegneria dei dati";
        System.out.println("\n7)TEST QUERY");
        System.out.println("Query: " + query);
        Searcher searcher = new Searcher("lucene-index");
        searcher.search(query);
        searcher.close();
    }

    @DisplayName("Test query 8")
    @Test
    public void testQuery8() {
        String query = "contenuto -ingegneria dei dati";
        System.out.println("\n8)TEST QUERY");
        System.out.println("Query: " + query);
        Searcher searcher = new Searcher("lucene-index");
        searcher.search(query);
        searcher.close();
    }

    @DisplayName("Test query 9")
    @Test
    public void testQuery9() {
        String query = "titolo cucina giapponese";
        System.out.println("\n9)TEST QUERY");
        System.out.println("Query: " + query);
        Searcher searcher = new Searcher("lucene-index");
        searcher.search(query);
        searcher.close();
    }

    @DisplayName("Test query 10")
    @Test
    public void testQuery10() {
        String query = "contenuto cucina giapponese";
        System.out.println("\n10)TEST QUERY");
        System.out.println("Query: " + query);
        Searcher searcher = new Searcher("lucene-index");
        searcher.search(query);
        searcher.close();
    }
}
