package de.uni.bremen.ds;

import de.uni.bremen.ds.io.Importer;
import de.uni.bremen.ds.model.Graph;
import de.uni.bremen.ds.model.Solution;
import de.uni.bremen.ds.verifier.DSVerifier;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TestVerifier {

    final static String TEST_DIR = "testset";

    @Test
    void testAll() {
        Stream.of(Objects.requireNonNull(new File(TEST_DIR).listFiles()))
                .filter(file -> !file.isDirectory())
                .forEach(file -> {
                    if(file.getName().endsWith(".gr")) {
                        try {
                            Importer importer = new Importer();
                            Graph g = importer.readGraph(file.getPath());
                            Solution sol = importer.readSolution(file.getPath().replace(".gr", ".sol"));

                            DSVerifier verifier = new DSVerifier();
                            assertTrue(verifier.verify(g, sol));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
    }
}