package de.uni.bremen.ds.io;

import de.uni.bremen.ds.model.Edge;
import de.uni.bremen.ds.model.Graph;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Exporter {
    public void writeGraph(final Graph g, final String path) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        writer.write("p ds " + g.size() + " " + g.getEdges().size() + "\n");
        for (Edge edge : g.getEdges()) {
            writer.write(edge.getStartId() + " " + edge.getEndId() + "\n");
        }
        writer.close();
    }
}
