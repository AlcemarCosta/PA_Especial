/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adtgraph.adt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;



/**
 *
 * @author costa
 */
public class MyDigraph<V, E> implements Digraph<V, E> {
    
    //private int nEdges;
    private HashMap<V, Vertex<V>> listVertices;
    private HashMap<E, Edge<E,V>> edges;
    
    
    
    public MyDigraph(){
        //this.nEdges = 0;
        listVertices = new HashMap();
        edges = new HashMap<>();
        
    }
    
     private MyVertex checkVertex(Vertex<V> p) throws InvalidVertexException {
        if (p == null) {
            throw new InvalidVertexException("WRONG vertex");
        }
        try {
            return (MyVertex) p;
        } catch (ClassCastException e) {
            throw new InvalidVertexException("WRONG vertex");
        }
    }

    private MyEdge checkEdge(Edge<E, V> ed) throws InvalidEdgeException {
        if (ed == null) {
            throw new InvalidEdgeException("WRONG edge");
        }
        try {
            return (MyEdge) ed;
        } catch (ClassCastException e) {
            throw new InvalidEdgeException("WRONG edge");
        }
    }



    @Override
    public Vertex<V> finalVertex(Vertex<V> v, Edge<E, V> e) throws InvalidVertexException, InvalidEdgeException {
     MyEdge edge = checkEdge(e);
        
        if( !edge.contains(v) ) return null; /* this edge does not connect vertex v */
         if(edge.vertices()[0] == v) {
             return edge.vertices()[1];
         } else {
             return edge.vertices()[0];
         }
        
//        if(edge.vertexStart == v) return edge.vertexEnd;
//        else System.out.println("O vertex dado nao é o vertex Inicial, entao nao se pode ser mostrado o vertice final");
////throw new InvalidVertexException("O vertex dado nao é o vertex Inicial, entao nao se pode ser mostrado o vertice final");
//        return edge.vertexStart;

    }

    @Override
    public boolean areVertexParalel(Vertex<V> u, Vertex<V> v) throws InvalidVertexException {
    //we allow loops, so we do not check if u == v
        checkVertex(v);
        checkVertex(u);
        
        /* find and edge that contains both u and v */
        for (Edge<E, V> edge : edges.values()) {
            if( ((MyEdge)edge).contains(v) && ((MyEdge)edge).contains(v)) 
                if(edge.getVertexStart() == u){
                    return true;
                }
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
     return (numVertices() == 0 && numEdges() == 0);
    }

    @Override
    public int size() {
    return numVertices() + numEdges();
    }

    @Override
    public int numVertices() {
    return listVertices.size();
    }

    @Override
    public int numEdges() {
    return edges.size();
    }

    @Override
    public Iterable<Vertex<V>> vertices() {
    return listVertices.values();
    }

    @Override
    public Iterable<Edge<E, V>> edges() {
    return getEdges();
    }
    
    private HashSet<Edge<E, V>> getEdges(){
        HashSet<Edge<E, V>> edges = new HashSet();
        for(Vertex<V> vertex : vertices()){
            edges.addAll(checkVertex(vertex).listaEdges);
        }
        return edges;
    }

    @Override
    public Iterable<Edge<E, V>> incidentEdges(Vertex<V> v) throws InvalidVertexException {
    if (!this.listVertices.containsValue(v)) {
            throw new InvalidVertexException("vertex does not exist");
        }
        return checkVertex(v).listaEdges;
    }

    @Override
    public Iterable<Edge<E, V>> ascendingEdges(Vertex<V> v) throws InvalidEdgeException {
    checkVertex(v);
        
        List<Edge<E,V>> ascendingEdges = new ArrayList<>();
        for (Edge<E, V> edge : edges.values()) {
            
            if( ((MyEdge)edge).contains(v) ) { /* edge.vertices()[0] == v || edge.vertices()[1] == v */
                if(edge.getVertexStart() == v){
                    ascendingEdges.add(edge);
                }
            }
            
        }
    
        return ascendingEdges;
    
    }

    @Override
    public Vertex<V> opposite(Vertex<V> v, Edge<E, V> e) throws InvalidVertexException, InvalidEdgeException {
    Vertex<V>[] vertices = checkEdge(e).vertices();
        if (vertices[0] == v) {
            return vertices[1];
        }
        if (vertices[1] == v) {
            return vertices[0];
        }
        throw new InvalidVertexException("Invalid vertex");
    }

    @Override
    public boolean areAdjacent(Vertex<V> u, Vertex<V> v) throws InvalidVertexException {
    for (Edge<E, V> e : edges()) {
            MyEdge myedge = checkEdge(e);
            if (myedge.vertexIn == u && myedge.vertexOut == v
                    || myedge.vertexOut == u && myedge.vertexIn == v) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Vertex<V> insertVertex(V vElement) throws InvalidVertexException {
    if (listVertices.containsKey(vElement)) {
            throw new InvalidVertexException(vElement + "already exists ");
        }
        MyVertex vertex = new MyVertex(vElement);
        listVertices.put(vElement, vertex);
        return vertex;
    }

    @Override
    public Edge<E, V> insertEdge(Vertex<V> u, Vertex<V> v, E edgeElement) throws InvalidVertexException, InvalidEdgeException {
    if (!listVertices.containsKey(u.element())) {
            throw new InvalidVertexException(u.element() + "not exists ");
        }
        if (!listVertices.containsKey(v.element())) {
            throw new InvalidVertexException(v.element() + "not exists ");
        }

        //existe aresta?
        if (areAdjacent(u, v)) {
            throw new InvalidVertexException("already exist an edge ");
        }

        MyEdge edge = new MyEdge(edgeElement, u, v);
        // coloca-lo nos vertices.
        checkVertex(u).listaEdges.add(edge);
        checkVertex(v).listaEdges.add(edge);
        return edge;
    }
    
      private boolean existsVertexWith(V vElement) {
        return listVertices.containsKey(vElement);
    }

    private boolean existsEdgeWith(E edgeElement) {
        return edges.containsKey(edgeElement);
    }


    @Override
    public Edge<E, V> insertEdge(V vElement1, V vElement2, E edgeElement) throws InvalidVertexException, InvalidEdgeException {
    if (existsEdgeWith(edgeElement)) {
            throw new InvalidEdgeException("There's already an edge with this element.");
        }

        if (!existsVertexWith(vElement1)) {
            throw new InvalidVertexException("No vertex contains " + vElement1);
        }
        if (!existsVertexWith(vElement2)) {
            throw new InvalidVertexException("No vertex contains " + vElement2);
        }

        MyVertex startVertex = vertexOf(vElement1);
        MyVertex finalVertex = vertexOf(vElement2);

        MyEdge newEdge = new MyEdge(edgeElement, startVertex, finalVertex);

        edges.put(edgeElement, newEdge);

        return newEdge;
    }
    
    private MyVertex vertexOf(V vElement) {
        for (Vertex<V> v : listVertices.values()) {
            if (v.element().equals(vElement)) {
                return (MyVertex) v;
            }
        }
        return null;
    }

    @Override
    public V removeVertex(Vertex<V> v) throws InvalidVertexException {
    if (!listVertices.containsValue(v)) {
            throw new InvalidVertexException("not exists ");
        }
        MyVertex vertex = checkVertex(v);
        if (!vertex.listaEdges.isEmpty()) {
            throw new InvalidVertexException(" vertex has incident edges");
        }
        listVertices.remove(v);
        return v.element();
    }

    @Override
    public E removeEdge(Edge<E, V> e) throws InvalidEdgeException {
     checkEdge(e);

        E element = e.element();
        edges.remove(e.element());

        return element;
    }

    @Override
    public V replace(Vertex<V> v, V newElement) throws InvalidVertexException {
     if (!this.listVertices.containsValue(v)) {
            throw new InvalidVertexException("vertex does not exist");
        }

        MyVertex vertex = checkVertex(v);
        V elem1 = vertex.element();
        vertex.elem = newElement;
        return elem1;
    }

    @Override
    public E replace(Edge<E, V> e, E newElement) throws InvalidEdgeException {
     if (!existsEdge(e)) {
            throw new InvalidEdgeException("Invalid Edge");
        }
        MyEdge edge = checkEdge(e);
        E elem1 = edge.element();
        edge.elem = newElement;
        return elem1;
    }
    
    private boolean existsEdge(Edge e) {
        return getEdges().contains(e);
    }
    
     private class MyVertex implements Vertex<V> {

        private V elem;
        private List<Edge<E, V>> listaEdges;

        public MyVertex(V elem) {
            this.elem = elem;
            this.listaEdges = new ArrayList<>();

        }

        @Override
        public V element() throws InvalidVertexException {
            if (elem == null) {
                throw new InvalidVertexException("vertex null");
            }
            return elem;
        }

    }

    private class MyEdge implements Edge<E, V> {

        private E elem;
        private Vertex<V> vertexIn, vertexOut;

        public MyEdge(E elem, Vertex<V> vertexIn, Vertex<V> vertexOut) {
            this.elem = elem;
            this.vertexIn = vertexIn;
            this.vertexOut = vertexOut;
        }

        @Override
        public E element() throws InvalidEdgeException {
            if (elem == null) {
                throw new InvalidEdgeException("edge null");
            }
            return elem;
        }
        
        public boolean contains(Vertex<V> v) {
            return (vertexIn == v || vertexOut == v);
        }

        @Override
        public Vertex<V>[] vertices() {
            Vertex[] vertices= new Vertex[2];
            vertices[0]=vertexIn;
            vertices[1]=vertexOut;
            return vertices;      
        }
        
        public String toString(){
            return "Edge {{" + elem + "}, vertexStart=" + vertexIn.toString()
                    + ", vertexEnd=" + vertexOut.toString() + '}';
                    
        }

        @Override
        public Vertex<V> getVertexStart() {
        return vertexIn;
        }

        @Override
        public Vertex<V> getVertexEnd() {
        return vertexOut;
        }

    }

}

    



