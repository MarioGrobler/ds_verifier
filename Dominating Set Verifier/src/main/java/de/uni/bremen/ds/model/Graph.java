package de.uni.bremen.ds.model;

import java.util.*;

public class Graph {
    protected Map<Integer, Set<Vertex>> vertexConnections;
    protected Map<Integer, Vertex> vertexMap;
    protected Set<Edge> edgesSet;

    protected int nextVertexId;

    public int getNextVertexId() {
        return nextVertexId;
    }

    public Graph() {
        this.vertexConnections = new HashMap<>();
        edgesSet = new LinkedHashSet<>();
        vertexMap = new HashMap<>();
    }

    public Graph(final Graph graph) {
        this.edgesSet = new LinkedHashSet<>(graph.edgesSet);
        this.vertexConnections = new HashMap<>();
        graph.vertexConnections.forEach((integer, vertices)
                -> this.vertexConnections.put(integer, new HashSet<>(vertices)));
        this.vertexMap = new HashMap<>(graph.vertexMap);
        this.nextVertexId = graph.nextVertexId;
    }


    public Graph(final int n) {
        vertexConnections = new HashMap<>(n);
        vertexMap = new HashMap<>(n);
        edgesSet = new LinkedHashSet<>();
        for (int i = 1; i < n + 1; i++) {
            addVertex(new Vertex(i));
        }
    }

    public Graph(final Map<Integer, Vertex> vertexMap,
                 final Map<Integer, Set<Vertex>> vertexConnections,
                 final Set<Edge> edgesSet, final int nextVertex) {
        this.edgesSet = new LinkedHashSet<>(edgesSet);
        this.vertexConnections = new HashMap<>();
        vertexConnections.forEach((integer, vertices)
                -> this.vertexConnections.put(integer, new HashSet<>(vertices)));
        this.vertexMap = new HashMap<>(vertexMap);
        this.nextVertexId = nextVertex;
    }


    public ArrayList<Integer> getVertexIdList() {
        return new ArrayList<>(this.vertexMap.keySet());
    }

    public Vertex createNewVertex() {
        Vertex vertex = new Vertex(nextVertexId);
        nextVertexId++;
        vertexMap.put(vertex.getId(), vertex);
        vertexConnections.put(vertex.getId(), new HashSet<>());
        return vertex;
    }

    public Vertex addVertex(final Vertex vertex) {
        if (vertexMap.containsKey(vertex.getId())) {
            throw new IllegalArgumentException("Graph contains Vertex with same id");
        }
        nextVertexId = vertex.getId() > nextVertexId ? vertex.getId() + 1 : nextVertexId + 1;
        vertexMap.put(vertex.getId(), vertex);
        vertexConnections.put(vertex.getId(), new HashSet<>());
        return vertex;
    }


    public void updateVertex(final Vertex vertex) {
        Vertex current = vertexMap.get(vertex.getId());
        if (current != null) {
            vertexMap.put(vertex.getId(), vertex);
        } else {
            addVertex(vertex);
        }
    }

    public Edge addEdge(final Vertex start, final Vertex end) {
        if (!vertexMap.containsKey(start.getId())) {
            throw new IllegalArgumentException(start + " is not in the Graph");
        }
        if (!vertexMap.containsKey(end.getId())) {
            throw new IllegalArgumentException(end + " is not in the Graph");
        }
        Edge edge = new Edge(start, end);
        edgesSet.add(edge);
        vertexConnections.get(start.getId()).add(end);
        vertexConnections.get(end.getId()).add(start);
        return edge;
    }


    public Edge addEdge(final int startId, final int endId) {
        if (!vertexMap.containsKey(startId)) {
            throw new IllegalArgumentException(startId + " is not in the Graph");
        }
        if (!vertexMap.containsKey(endId)) {
            throw new IllegalArgumentException(endId + " is not in the Graph");
        }
        Edge edge = new Edge(startId, endId);
        edgesSet.add(edge);
        vertexConnections.get(startId).add(vertexMap.get(endId));
        vertexConnections.get(endId).add(vertexMap.get(startId));
        return edge;
    }


    public void updateEdge(final Edge edge) {
        if (edgesSet.contains(edge)) {
            edgesSet.remove(edge);
            edgesSet.add(edge);
        } else {
            int startId = edge.getStartId();
            int endId = edge.getEndId();
            if (!vertexMap.containsKey(startId)) {
                throw new IllegalArgumentException(startId + " is not in the Graph");
            }
            if (!vertexMap.containsKey(endId)) {
                throw new IllegalArgumentException(endId + " is not in the Graph");
            }
            vertexConnections.get(startId).add(vertexMap.get(endId));
            vertexConnections.get(endId).add(vertexMap.get(startId));
            edgesSet.add(edge);
        }
    }


    public int degreeOf(final Vertex vertex) {
        if (vertexExits(vertex)) {
            return vertexConnections.get(vertex.getId()).size();
        }
        return 0;
    }


    public int degreeOf(final int id) {
        if (vertexExits(id)) {
            return vertexConnections.get(id).size();
        }
        return 0;
    }

    public int getMaxDegree() {
        int degree = 0;
        for (Vertex v : this.getVertices()) {
            degree = Math.max(degree, degreeOf(v));
        }
        return degree;
    }


    public boolean vertexExits(final Vertex id) {
        return vertexMap.containsKey(id.getId());
    }


    public boolean vertexExits(final int id) {
        return vertexMap.containsKey(id);
    }


    public boolean removeVertex(final Vertex vertex) {
        return removeVertex(vertex.getId());
    }


    public boolean removeVertex(final int id) {
        if (vertexMap.containsKey(id)) {
            Set<Vertex> vertices = vertexConnections.get(id);
            vertices.forEach(vertex -> {
                vertexConnections.get(vertex.getId()).remove(getVertex(id));
                this.edgesSet.remove(new Edge(id, vertex.getId()));
            });
            vertexConnections.remove(id);
            return vertexMap.remove(id) != null;
        }
        return false;
    }


    public boolean removeEdge(final Edge edge) {
        vertexConnections.get(edge.getStartId()).remove(getVertex(edge.getEndId()));
        vertexConnections.get(edge.getEndId()).remove(getVertex(edge.getStartId()));
        return edgesSet.remove(edge);
    }

    public boolean removeEdge(final int start, final int end) {
        return removeEdge(new Edge(start, end));
    }


    public boolean removeEdge(final Vertex vertexStart, final Vertex vertexEnd) {
        return removeEdge(new Edge(vertexStart, vertexEnd));
    }


    public boolean edgeExists(final Vertex vertexStart, final Vertex vertexEnd) {
        if (!vertexExits(vertexStart)) {
            return false;
        }
        return vertexConnections.get(vertexStart.getId()).contains(vertexEnd);
    }


    public boolean edgeExists(final int start, final int end) {
        return edgeExists(vertexMap.get(start), vertexMap.get(end));
    }


    public boolean edgeExists(final Edge edge) {
        return edgesSet.contains(edge);
    }


    public Collection<Edge> getEdges() {
        return edgesSet;
    }


    public Collection<Vertex> getVertices() {
        return vertexMap.values();
    }


    public Set<Vertex> getNeighbors(final Vertex vertex) {
        if (vertexExits(vertex)) {
            return vertexConnections.get(vertex.getId());
        }
        return new HashSet<>();
    }


    public Set<Vertex> getNeighbors(final int vertex) {
        if (vertexExits(vertex)) {
            return vertexConnections.get(vertex);
        }
        return new HashSet<>();
    }


    public Vertex getVertex(final int id) {
        return vertexMap.get(id);
    }

    @Override
    public Graph clone() {
        return new Graph(vertexMap, vertexConnections, edgesSet, nextVertexId);
    }

    public void clean() {
        this.vertexConnections = new HashMap<>();
        this.vertexMap = new HashMap<>();
        this.edgesSet = new LinkedHashSet<>();
        this.nextVertexId = 0;
    }

    @Override
    public String toString() {
        return "Graph{"
                + "vertexConnections=" + vertexConnections
                + ", vertexMap=" + vertexMap
                + ", edgesSet=" + edgesSet
                + '}';
    }


    public Edge createEdge(final int start, final int end) {
        return new Edge(start, end);
    }

    public int size() {
        return vertexMap.size();
    }
}
