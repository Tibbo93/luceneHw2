package com.github.lucenehw;

import org.apache.lucene.queryparser.classic.ParseException;
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
        long start = System.nanoTime();
        indexer.createIndex();
        long end = System.nanoTime();
        System.out.println("Indexing time: " + ((end - start)/1000000) + "ms");
        indexer.close();
    }

    @DisplayName("Test simple query 1")
    @Test
    public void testSimpleQuery() throws IOException, ParseException {
        String query = "titolo intelligenza artificiale";
        System.out.println("\nTEST SIMPLE QUERY");
        System.out.println("Query: " + query);
        Searcher searcher = new Searcher("lucene-index");
        long start = System.nanoTime();
        searcher.search(query);
        long end = System.nanoTime();
        System.out.println("Searching time: " + ((end - start)/1000000) + "ms");
        searcher.close();
    }

    @DisplayName("Test simple query 2")
    @Test
    public void testSimpleQuery2() throws IOException, ParseException {
        String query = "contenuto intelligenza artificiale";
        System.out.println("\nTEST SIMPLE QUERY");
        System.out.println("Query: " + query);
        Searcher searcher = new Searcher("lucene-index");
        long start = System.nanoTime();
        searcher.search(query);
        long end = System.nanoTime();
        System.out.println("Searching time: " + ((end - start)/1000000) + "ms");
        searcher.close();
    }

    @DisplayName("Test simple query 3")
    @Test
    public void testSimpleQuery3() throws IOException, ParseException {
        String query = "titolo re del mare";
        System.out.println("\nTEST SIMPLE QUERY");
        System.out.println("Query: " + query);
        Searcher searcher = new Searcher("lucene-index");
        long start = System.nanoTime();
        searcher.search(query);
        long end = System.nanoTime();
        System.out.println("Searching time: " + ((end - start)/1000000) + "ms");
        searcher.close();
    }

    @DisplayName("Test simple query 3")
    @Test
    public void testSimpleQuery4() throws IOException, ParseException {
        String query = "contenuto re del mare";
        System.out.println("\nTEST SIMPLE QUERY");
        System.out.println("Query: " + query);
        Searcher searcher = new Searcher("lucene-index");
        long start = System.nanoTime();
        searcher.search(query);
        long end = System.nanoTime();
        System.out.println("Searching time: " + ((end - start)/1000000) + "ms");
        searcher.close();
    }

    @DisplayName("Test phrase query 1")
    @Test
    public void testPhraseQuery() throws IOException, ParseException {
        String query = "titolo \"intelligenza artificiale\"";
        System.out.println("\nTEST PHRASE QUERY");
        System.out.println("Query: " + query);
        Searcher searcher = new Searcher("lucene-index");
        long start = System.nanoTime();
        searcher.search(query);
        long end = System.nanoTime();
        System.out.println("Searching time: " + ((end - start)/1000000) + "ms");
        searcher.close();
    }

    @DisplayName("Test phrase query 2")
    @Test
    public void testPhraseQuery2() throws IOException, ParseException {
        String query = "contenuto \"intelligenza artificiale\"";
        System.out.println("\nTEST PHRASE QUERY");
        System.out.println("Query: " + query);
        Searcher searcher = new Searcher("lucene-index");
        long start = System.nanoTime();
        searcher.search(query);
        long end = System.nanoTime();
        System.out.println("Searching time: " + ((end - start)/1000000) + "ms");
        searcher.close();
    }

    @DisplayName("Test phrase query 3")
    @Test
    public void testPhraseQuery3() throws IOException, ParseException {
        String query = "contenuto \"re del mare\"";
        System.out.println("\nTEST PHRASE QUERY");
        System.out.println("Query: " + query);
        Searcher searcher = new Searcher("lucene-index");
        long start = System.nanoTime();
        searcher.search(query);
        long end = System.nanoTime();
        System.out.println("Searching time: " + ((end - start)/1000000) + "ms");
        searcher.close();
    }

    @DisplayName("Test query 1")
    @Test
    public void testQuery5() throws IOException, ParseException {
        String query = "titolo +ingegneria dei +dati";
        System.out.println("\nTEST QUERY");
        System.out.println("Query: " + query);
        Searcher searcher = new Searcher("lucene-index");
        long start = System.nanoTime();
        searcher.search(query);
        long end = System.nanoTime();
        System.out.println("Searching time: " + ((end - start)/1000000) + "ms");
        searcher.close();
    }

    @DisplayName("Test query 2")
    @Test
    public void testQuery6() throws IOException, ParseException {
        String query = "contenuto +ingegneria dei +dati";
        System.out.println("\nTEST QUERY");
        System.out.println("Query: " + query);
        Searcher searcher = new Searcher("lucene-index");
        long start = System.nanoTime();
        searcher.search(query);
        long end = System.nanoTime();
        System.out.println("Searching time: " + ((end - start)/1000000) + "ms");
        searcher.close();
    }

    @DisplayName("Test query 3")
    @Test
    public void testQuery7() throws IOException, ParseException {
        String query = "titolo -ingegneria dei dati";
        System.out.println("\nTEST QUERY");
        System.out.println("Query: " + query);
        Searcher searcher = new Searcher("lucene-index");
        long start = System.nanoTime();
        searcher.search(query);
        long end = System.nanoTime();
        System.out.println("Searching time: " + ((end - start)/1000000) + "ms");
        searcher.close();
    }

    @DisplayName("Test query 3")
    @Test
    public void testQuery8() throws IOException, ParseException {
        String query = "contenuto -ingegneria dei dati";
        System.out.println("\nTEST QUERY");
        System.out.println("Query: " + query);
        Searcher searcher = new Searcher("lucene-index");
        long start = System.nanoTime();
        searcher.search(query);
        long end = System.nanoTime();
        System.out.println("Searching time: " + ((end - start)/1000000) + "ms");
        searcher.close();
    }

    @DisplayName("Test query 5")
    @Test
    public void testQuery9() throws IOException, ParseException {
        String query = "titolo cucina giapponese";
        System.out.println("\nTEST QUERY");
        System.out.println("Query: " + query);
        Searcher searcher = new Searcher("lucene-index");
        long start = System.nanoTime();
        searcher.search(query);
        long end = System.nanoTime();
        System.out.println("Searching time: " + ((end - start)/1000000) + "ms");
        searcher.close();
    }

    @DisplayName("Test query 6")
    @Test
    public void testQuery10() throws IOException, ParseException {
        String query = "contenuto cucina giapponese";
        System.out.println("\nTEST QUERY");
        System.out.println("Query: " + query);
        Searcher searcher = new Searcher("lucene-index");
        long start = System.nanoTime();
        searcher.search(query);
        long end = System.nanoTime();
        System.out.println("Searching time: " + ((end - start)/1000000) + "ms");
        searcher.close();
    }

    @DisplayName("Test query 7")
    @Test
    public void testQuery11() throws IOException, ParseException {
        String query = "contenuto coscienza";
        System.out.println("\nTEST QUERY");
        System.out.println("Query: " + query);
        Searcher searcher = new Searcher("lucene-index");
        long start = System.nanoTime();
        searcher.search(query);
        long end = System.nanoTime();
        System.out.println("Searching time: " + ((end - start)/1000000) + "ms");
        searcher.close();
    }

    @DisplayName("Test query 8")
    @Test
    public void testQuery12() throws IOException, ParseException {
        String query = "contenuto \"aurora boreale\"";
        System.out.println("\nTEST QUERY");
        System.out.println("Query: " + query);
        Searcher searcher = new Searcher("lucene-index");
        long start = System.nanoTime();
        searcher.search(query);
        long end = System.nanoTime();
        System.out.println("Searching time: " + ((end - start)/1000000) + "ms");
        searcher.close();
    }
}
