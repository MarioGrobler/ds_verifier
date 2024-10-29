package de.uni.bremen.ds.model;

import java.util.Objects;

public class Edge {
    private final int startId;
    private final int endId;

    protected Edge(final Vertex startVertex,
                   final Vertex endVertex) {

        if (startVertex.getId() == endVertex.getId()) {
            throw new IllegalArgumentException("Vertexes cant be the same");
        }
        if (startVertex.getId() < endVertex.getId()) {
            this.startId = startVertex.getId();
            this.endId = endVertex.getId();
        } else {
            this.startId = endVertex.getId();
            this.endId = startVertex.getId();
        }
    }

    protected Edge(final int startId,
                   final int endId) {
        if (startId == endId) {
            throw new IllegalArgumentException("Vertexes cant be the same");
        }

        if (startId < endId) {
            this.startId = startId;
            this.endId = endId;
        } else {
            this.startId = endId;
            this.endId = startId;
        }
    }


    public int getStartId() {
        return startId;
    }


    public int getEndId() {
        return endId;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (!(o instanceof Edge)) {
            return false;
        }
        Edge that = (Edge) o;
        return (that.getStartId() == startId && that.getEndId() == endId);
    }

    @Override
    public int hashCode() {
        if (startId <= endId) {
            return Objects.hash(startId, endId);
        }
        return Objects.hash(endId, startId);
    }

    @Override
    public String toString() {
        return "(" + getStartId() + ")---(" + getEndId() + ")";
    }


}
