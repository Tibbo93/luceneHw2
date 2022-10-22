package com.github.lucenehw;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.core.WhitespaceTokenizerFactory;
import org.apache.lucene.analysis.custom.CustomAnalyzer;
import org.apache.lucene.analysis.it.ItalianAnalyzer;
import org.apache.lucene.analysis.miscellaneous.WordDelimiterGraphFilterFactory;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Setter
@Getter
@NoArgsConstructor
public class Searcher {

    private final static String QUERY_PATTERN = "^(titolo|contenuto) (\"[a-zA-Z ]+\"|([+-]?[a-zA-Z ])+)$";

    private Directory indexDirectory;
    private IndexReader reader;
    private IndexSearcher searcher;

    public Searcher(String indexPath) throws IOException {
        this.indexDirectory = FSDirectory.open(Paths.get(indexPath));
        this.reader = DirectoryReader.open(this.indexDirectory);
        this.searcher = new IndexSearcher(this.reader);

    }

    public void search(String query) throws ParseException, IOException {
        Pattern pattern = Pattern.compile(QUERY_PATTERN);
        Matcher matcher = pattern.matcher(query);

        if (matcher.find()) {
            String[] split = query.split("\\s", 2);
            runQuery(split[0], split[1]);
        } else {
            System.out.println("Query malformed!");
        }
    }

    private void runQuery(String field, String sequence) throws ParseException, IOException {
        System.out.printf("Field: %s, query: %s%n%n", field, sequence);
        QueryParser parser;
        if (field.equals("contenuto")) {
            parser = new QueryParser(field, new ItalianAnalyzer());
        } else {
            Analyzer analyzer = CustomAnalyzer.builder()
                    .withTokenizer(WhitespaceTokenizerFactory.class)
                    .addTokenFilter(LowerCaseFilterFactory.class)
                    .addTokenFilter(WordDelimiterGraphFilterFactory.class)
                    .build();
            parser = new QueryParser(field, analyzer);
        }

        Query query = parser.parse(sequence);

        TopDocs hits = searcher.search(query, 10);
        if (hits.scoreDocs.length != 0) {
            System.out.println("Result:");
            for (int i = 0; i < hits.scoreDocs.length; i++) {
                ScoreDoc scoreDoc = hits.scoreDocs[i];
                Document doc = searcher.doc(scoreDoc.doc);
                System.out.printf("%d) %s%n", i + 1, doc.get("titolo"));
            }
        } else {
            System.out.println("No results found");
        }
    }

    public void close() throws IOException {
        this.indexDirectory.close();
        this.reader.close();
    }
}
