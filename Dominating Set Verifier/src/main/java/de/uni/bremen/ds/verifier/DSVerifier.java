package de.uni.bremen.ds.verifier;

import de.uni.bremen.ds.model.Graph;
import de.uni.bremen.ds.model.Solution;
import de.uni.bremen.ds.model.Vertex;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class DSVerifier {
    public boolean verify(final Graph graph, final Solution solution) {
        if(!solution.isConsistent()) {
            throw new VerificationException("Solution is not consistent. Specified solution size: " +
                    solution.getSolutionSize() + ", actual solution size: " + solution.getSolution().size());
        }

        Set<Integer> dominated = new HashSet<>();

        for(int vertex : solution.getSolution()) {
            if(!graph.vertexExits(vertex)) {
                throw new VerificationException("Vertex " + vertex + " does not exist in the graph");
            }
            dominated.add(vertex);
            dominated.addAll(graph.getNeighbors(vertex).stream().map(Vertex::getId).collect(Collectors.toSet()));
        }
        return graph.size() == dominated.size();
    }
}
