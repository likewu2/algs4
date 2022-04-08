package edu.princeton.cs.algs4.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class GraphDfsTest {
    public static void main(String[] args) {
        GraphTest.Graph a= new GraphTest.DenseGraphIterater(6, true);
        //Graph a= new SparseGraphIterater(6, true);
        a.addEdge(1, 3);
        a.addEdge(3, 5);
        a.addEdge(5, 1);
        Components b= new Components(a);
        System.out.println("ans: "+ String.join(",", StreamSupport.stream(Spliterators.spliteratorUnknownSize(a.adj(1),0),false).map(n->n.toString()).collect(Collectors.toList())));
    }

    static class Components {
        GraphTest.Graph G;
        private boolean[] visited;
        private int m_count;
        private int[] id;

        void dfs(int v) {
            visited[v] = true;
            id[v]=m_count;
            for (Iterator<Integer> it = G.adj(v); it.hasNext(); ) {
            //for (int i: G.adj(v))
                int i = it.next();
                if (!visited[i])
                    dfs(i);
            }
        }

        public Components(GraphTest.Graph graph) {
            G=graph;
            visited=new boolean[G.V()];
            id=new int[G.V()];
            m_count=0;
            for (int i=0;i<G.V();i++) {
                visited[i]=false;
                id[i]=-1;
            }

            for (int i=0;i<G.V();i++)
                if(!visited[i]) {
                    dfs(i);
                    m_count++;
                }
        }

        int count() {return m_count;}

        boolean isConnected(int v, int w) {
            assert v>=0 && v<G.V();
            assert w>=0 && w<G.V();
            return id[v]==id[w];
        }
    }
}
