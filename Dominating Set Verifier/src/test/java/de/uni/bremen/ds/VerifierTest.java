package de.uni.bremen.ds;

import static org.junit.jupiter.api.Assertions.assertTrue;

import de.uni.bremen.ds.io.Importer;
import de.uni.bremen.ds.model.Graph;
import de.uni.bremen.ds.model.Solution;
import de.uni.bremen.ds.verifier.DSVerifier;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.stream.Stream;

class TestVerifier {

  final static String TEST_DIR = "testset";

  @Test
  void testAll() throws URISyntaxException {
    //Dirty test to verify the verifier
    File testDir = new File(Objects.requireNonNull(getClass().getClassLoader().getResource(TEST_DIR)).toURI());

    Stream.of(Objects.requireNonNull(testDir.listFiles()))
        .filter(file -> !file.isDirectory())
        .forEach(file -> {
          if (file.getName().endsWith(".gr")) {
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