package edu.princeton.cs.algs4.test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class GraphTest {
    public static void main(String[] args) {
        Graph a= new DenseGraphIterater(6, true);
        //Graph a= new SparseGraphIterater(6, true);
        a.addEdge(1, 3);
        a.addEdge(3, 5);
        a.addEdge(5, 1);
        //StreamSupport.stream(Spliterators.spliteratorUnknownSize(a.adj(1),0),false).map(n->n.toString());
        System.out.println("ans: "+ String.join(",", StreamSupport.stream(Spliterators.spliteratorUnknownSize(a.adj(1),0),false).map(n->n.toString()).collect(Collectors.toList())));
    }

    public static interface Graph {
        int V();
        int E();
        void addEdge(int v, int w);
        Iterator<Integer> adj(int v);
    }

    public static class DenseGraphIterater implements Graph {
        private int n;
        private int m;
        private boolean directed;
        private boolean[][] g;

        public DenseGraphIterater(int n, boolean directed) {
            assert n>=0;
            this.n=n;
            this.m=0;
            this.directed=directed;
            this.g=new boolean[n][n];
        }

        public int V() {return n;}
        public int E() {return m;}

        public void addEdge(int v, int w) {
            assert v>=0 && v<n;
            assert w>=0 && w<n;
            if(hasEdge(v, w))
                return;
            g[v][w]=true;
            if(!directed)
                g[w][v]=true;
            m++;
        }

        boolean hasEdge(int v, int w) {
            assert v>=0 && v<n;
            assert w>=0 && w<n;
            return g[v][w];
        }

        public Iterator<Integer> adj(int v) {
            assert v>=0 && v<n;
            List<Integer> adjV = new ArrayList<>();
            for(int i=0;i<n;i++)
                if(g[v][i])
                    adjV.add(i);
            return adjV.iterator();
        }
    }

    public static class SparseGraphIterater implements Graph {
        private int n;
        private int m;
        private boolean directed;
        private List<Integer>[] g;

        public SparseGraphIterater(int n, boolean directed) {
            assert n>=0;
            this.n=n;
            this.m=0;
            this.directed=directed;
            this.g=new ArrayList[n];
            for(int i=0;i<n;i++)
                this.g[i]=new ArrayList<>();
        }

        public int V() {return n;}
        public int E() {return m;}

        public void addEdge(int v, int w) {
            assert v>=0 && v<n;
            assert w>=0 && w<n;
            if(hasEdge(v, w))
                return;
            g[v].add(w);
            if(v!=w && !directed)
                g[w].add(v);
            m++;
        }

        boolean hasEdge(int v, int w) {
            assert v>=0 && v<n;
            assert w>=0 && w<n;
            for(int i=0;i<g[v].size();i++)
                if(g[v].get(i)==w)
                    return true;
            return false;
        }

        public Iterator<Integer> adj(int v) {
            assert v>=0 && v<n;
            return g[v].iterator();
        }
    }
}
