package de.uni.bremen.ds.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    private int solutionSize;
    private final Set<Integer> solution;

    public Solution() {
        solution = new HashSet<>();
    }

    public Solution(final int solutionSize) {
        this.solutionSize = solutionSize;
        solution = new HashSet<>(solutionSize);
    }

    public void addVertex(final int vertexId) {
        solution.add(vertexId);
    }

    public void setSolutionSize(int solutionSize) {
        this.solutionSize = solutionSize;
    }

    public int getSolutionSize() {
        return solutionSize;
    }

    public Set<Integer> getSolution() {
        return solution;
    }

    public boolean isConsistent() {
        return solutionSize == solution.size();
    }
}
