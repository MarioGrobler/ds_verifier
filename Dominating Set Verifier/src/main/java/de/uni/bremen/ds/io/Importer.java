package de.uni.bremen.ds.io;

import de.uni.bremen.ds.model.Graph;
import de.uni.bremen.ds.model.Solution;
import de.uni.bremen.ds.model.Vertex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Importer {
    public Graph readGraph(final String path) throws IOException {
        Graph graph = new Graph();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        String line;
        int i = 0;
        while ((line = bufferedReader.readLine()) != null) {
            i++;
            if (line.isEmpty()) {
                continue;
            }
            String[] strings = line.split(" ");

            if (line.startsWith("c")) {
                continue;
            }

            if (line.startsWith("p")) {
                if (strings.length < 2 || !strings[1].equals("ds")) {
                    throw new IOException("Problem specifier is not ds in line " + i);
                }
                if (strings.length != 4) {
                    throw new IOException("Problem specifying line " + i + " does not contain exactly 4 values");
                }

                int n = Integer.parseInt(strings[2]);
                for(int j = 1; j <= n; j++) {
                    graph.addVertex(new Vertex(j));
                }
                continue;
            }
            if (strings.length == 2) {
                int start = Integer.parseInt(strings[0]);
                int end = Integer.parseInt(strings[1]);

                if (!graph.vertexExists(start) || !graph.vertexExists(end)) {
                    throw new IOException("Line " + i + " contains a vertex greater than n");
                }

                graph.addEdge(start, end);
                continue;
            }
            if (strings.length == 0 || line.isBlank() || line.startsWith("\n")) {
                continue;
            }

            throw new IOException("Line " + i + " does not contain two vertices!");
        }
        return graph;
    }

    public Solution readSolution(final String path) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        String line;
        int i = 0;
        int solutionSize = -1;

        //find first number
        while ((line = bufferedReader.readLine()) != null) {
            i++;
            if(line.isEmpty()) {
                continue;
            }

            String[] strings = line.split(" ");

            if (line.startsWith("c")) {
                continue;
            }
            if (strings.length == 0 || line.isBlank() || line.startsWith("\n")) {
                continue;
            }
            if(strings.length == 1) {
                solutionSize = Integer.parseInt(strings[0]);
                break;
            }
            throw new IOException("Line " + i + " does not contain a number");
        }

        Solution solution = new Solution(solutionSize);
        while ((line = bufferedReader.readLine()) != null) {
            i++;
            if(line.isEmpty()) {
                continue;
            }

            String[] strings = line.split(" ");

            if (line.startsWith("c")) {
                continue;
            }
            if (strings.length == 0 || line.isBlank() || line.startsWith("\n")) {
                continue;
            }
            if(strings.length == 1) {
                solution.addVertex(Integer.parseInt(strings[0]));
                continue;
            }
            throw new IOException("Line " + i + " does not contain a number");
        }

        return solution;
    }

}
