/* 
 * The MIT License
 *
 * Copyright 2019 brunomnsilva@gmail.com.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package adtgraph.adt;

import java.util.Collection;

/**
 * A directed graph (or digraph) is a graph that is made up of a set of vertices 
 * connected by edges, where the edges have a direction associated with them.
 * 
 * An directed-edge leaves the <i>outbound vertex</i>
 * towards the <i>inbound vertex</i> and this changes the reasoning behind some
 * methods of the {@link Graph} interface, which are overridden in this interface
 * so as to provide different documentation of expected behavior.
 * 
 * @param <E> Type of values stored in edges
 * @param <V> Type of values stored in vertices
 * 
 * @see Graph
 * @see Edge
 * @see Vertex
 */
public interface Digraph<V, E> extends Graph<V, E> {
    


    
    /**
     * Alcemar
     * Given a vertex and an edge, return the final vertex
     * @param v an vertex
     * @param e an edge
     * @return the opposite vertex
     * @throws InvalidVertexException if the vertex is invalid for the graph
     * @throws InvalidEdgeException if the edge is invalid for the graph
     */
    public Vertex<V> finalVertex(Vertex<V> v, Edge<E, V> e) 
            throws InvalidVertexException, InvalidEdgeException;
    
    /**
     * Tests whether two vertices are adjacent.
     * @param u a vertex (outbound, if digraph)
     * @param v another vertex(inbound, if digraph)
     * @return true if they are adjacent, false otherwise (if v is adjacent of u)
     * @throws InvalidVertexException if a vertex is invalid for the graph
     */
    public boolean areVertexParalel(Vertex<V> u, Vertex<V> v)
            throws InvalidVertexException;
    
    

    
    
}
