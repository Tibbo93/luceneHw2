package com.github.lucenehw;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.*;
import org.apache.lucene.analysis.custom.CustomAnalyzer;
import org.apache.lucene.analysis.it.ItalianAnalyzer;
import org.apache.lucene.analysis.miscellaneous.PerFieldAnalyzerWrapper;
import org.apache.lucene.analysis.miscellaneous.WordDelimiterGraphFilterFactory;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.codecs.simpletext.SimpleTextCodec;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
public class Indexer {

    private static String WHITESPACE = " ";

    private String indexPath;
    private String documentsPath;
    private Directory indexDirectory;

    public Indexer(String indexPath, String documentsPath) {
        try {
            this.indexPath = indexPath;
            this.documentsPath = documentsPath;
            this.indexDirectory = FSDirectory.open(Paths.get(indexPath));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createIndex() {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(this.documentsPath))) {
            Analyzer analyzer = CustomAnalyzer.builder()
                    .withTokenizer(WhitespaceTokenizerFactory.class)
                    .addTokenFilter(LowerCaseFilterFactory.class)
                    .addTokenFilter(WordDelimiterGraphFilterFactory.class)
                    .build();

            Map<String, Analyzer> perFieldAnalyzers = new HashMap<>();
            perFieldAnalyzers.put("titolo", analyzer);
            perFieldAnalyzers.put("contenuto", new ItalianAnalyzer());
            analyzer = new PerFieldAnalyzerWrapper(new StandardAnalyzer(), perFieldAnalyzers);

            IndexWriterConfig config = new IndexWriterConfig(analyzer);
            config.setCodec(new SimpleTextCodec());
            IndexWriter writer = new IndexWriter(indexDirectory, config);

            for (Path file : stream) {
                BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8);
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line);
                    content.append(WHITESPACE);
                }

                Document doc = new Document();
                doc.add(new TextField("titolo", file.getFileName().toString().replace(".txt", ""), Field.Store.YES));
                doc.add(new TextField("contenuto", content.toString(), Field.Store.YES));
                writer.addDocument(doc);

                reader.close();
            }

            writer.commit();
            writer.close();
            System.out.println("Index created successfully");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void close() {
        try {
            indexDirectory.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
