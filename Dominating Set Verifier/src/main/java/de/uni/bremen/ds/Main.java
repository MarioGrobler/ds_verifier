package de.uni.bremen.ds;

import de.uni.bremen.ds.io.Importer;
import de.uni.bremen.ds.model.Graph;
import de.uni.bremen.ds.model.Solution;
import de.uni.bremen.ds.verifier.DSVerifier;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        if(args.length != 2) {
            System.err.println("Two arguments expected. Usage: ds_verifier [Path to graph] [Path to solution]");
        }

        Importer importer = new Importer();
        Graph graph = importer.readGraph(args[0]);
        Solution solution = importer.readSolution(args[1]);

        DSVerifier verifier = new DSVerifier();
        if(verifier.verify(graph, solution)) {
            System.out.println("Solution of size " + solution.getSolutionSize() + " is valid!");
        } else {
            System.err.println("At least one vertex is not dominated!");
        }
    }
}