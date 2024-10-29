package de.uni.bremen.ds.model;

public class Vertex {
    private int id = -1;

    public int getId() {
        return id;
    }

    public Vertex(final int id) {
        if (id >= 0) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("id must be positive");
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vertex vertex)) {
            return false;
        }
        if (this.id == -1 || vertex.id == -1) {
            return false;
        }
        return id == vertex.id;
    }

    @Override
    public String toString() {
        return "[" + getId() + "]";
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public Vertex clone() {
        return new Vertex(this.getId());
    }
}
