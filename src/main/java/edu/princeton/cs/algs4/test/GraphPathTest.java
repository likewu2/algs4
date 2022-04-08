package edu.princeton.cs.algs4.test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class GraphPathTest {
    public static void main(String[] args) {
        GraphTest.Graph a= new GraphTest.DenseGraphIterater(6, true);
        //Graph a= new SparseGraphIterater(6, true);
        a.addEdge(1, 3);
        a.addEdge(3, 5);
        a.addEdge(5, 1);
        Path b= new Path(a, 1);
        b.showPath(1);
        //System.out.println("ans: "+ String.join(",", StreamSupport.stream(Spliterators.spliteratorUnknownSize(a.adj(1),0),false).map(n->n.toString()).collect(Collectors.toList())));
    }

    static class Path {
        GraphTest.Graph G;
        private boolean[] visited;
        private int s;
        private int[] from;

        void dfs(int v) {
            visited[v] = true;
            for (Iterator<Integer> it = G.adj(v); it.hasNext(); ) {
            //for (int i: G.adj(v))
                int i = it.next();
                if (!visited[i]) {
                    from[i]=v;
                    dfs(i);
                }
            }
        }

        public Path(GraphTest.Graph graph, int s) {
            G=graph;
            assert s>=0 && s<G.V();
            visited=new boolean[G.V()];
            from=new int[G.V()];
            for (int i=0;i<G.V();i++) {
                visited[i]=false;
                from[i]=-1;
            }

            this.s=s;
            dfs(s);
        }

        boolean hasPath(int w) {
            assert w>=0 && w<G.V();
            return visited[w];
        }

        List<Integer> path(int w) {
            assert hasPath(w);
            //LinkedList<Integer> s=new LinkedList<>();
            Deque<Integer> s = new ArrayDeque<>();
            int p=w;
            while(p!=-1) {
                s.push(p);
                p=from[p];
            }

            List<Integer> res=new ArrayList<>();
            while(!s.isEmpty())
                res.add(s.pop());

            return res;
        }

        void showPath(int w) {
            assert hasPath(w);
            List<Integer> vec=path(w);
            for (int i=0;i<vec.size();i++) {
                System.out.print(vec.get(i));
                if (i==vec.size()-1)
                    System.out.println();
                else
                    System.out.print(" -> ");
            }
        }
    }
}
