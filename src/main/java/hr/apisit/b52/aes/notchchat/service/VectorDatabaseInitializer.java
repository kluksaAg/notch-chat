package hr.apisit.b52.aes.notchchat.service;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.pdf.ParagraphPdfDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class VectorDatabaseInitializer {

    private final VectorStore vectorStore;

    VectorDatabaseInitializer(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @PostConstruct
    void init() {
        List<Document> documents = new ArrayList<>();

        documents.addAll(new ParagraphPdfDocumentReader("file:///home/kluksa/Downloads/mob.pdf").get());
        documents.addAll(new ParagraphPdfDocumentReader("file:///home/kluksa/Downloads/racun.pdf").get());
        documents.addAll(new ParagraphPdfDocumentReader("file:///home/kluksa/Downloads/onborad.pdf").get());
        var textSplitter = new TokenTextSplitter();
        var transformedDocuments = textSplitter.apply(documents);

        vectorStore.add(transformedDocuments);
    }
}
