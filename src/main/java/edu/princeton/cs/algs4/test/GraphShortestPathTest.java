package edu.princeton.cs.algs4.test;

import java.util.*;

public class GraphShortestPathTest {
    public static void main(String[] args) {
        GraphTest.Graph a= new GraphTest.DenseGraphIterater(6, true);
        //Graph a= new SparseGraphIterater(6, true);
        a.addEdge(1, 3);
        a.addEdge(3, 5);
        a.addEdge(5, 1);
        ShortestPath b= new ShortestPath(a, 1);
        b.showPath(1);
        //System.out.println("ans: "+ String.join(",", StreamSupport.stream(Spliterators.spliteratorUnknownSize(a.adj(1),0),false).map(n->n.toString()).collect(Collectors.toList())));
    }

    static class ShortestPath {
        GraphTest.Graph G;
        private boolean[] visited;
        private int s;
        private int[] from;
        private int[] ord;

        public ShortestPath(GraphTest.Graph graph, int s) {
            G=graph;
            assert s>=0 && s<G.V();
            visited=new boolean[G.V()];
            from=new int[G.V()];
            ord=new int[G.V()];
            for (int i=0;i<G.V();i++) {
                visited[i]=false;
                from[i]=-1;
                ord[i]=-1;
            }

            this.s=s;
            LinkedList<Integer> q=new LinkedList<>();
            q.push(s);
            visited[s]=true;
            ord[s]=0;
            while (!q.isEmpty()) {
                int v=q.pop();
                //for (int i: G.adj(v)) {
                for (Iterator<Integer> it = G.adj(v); it.hasNext(); ) {
                    int i = it.next();
                    if(!visited[i]) {
                        q.push(i);
                        visited[i]=true;
                        from[i]=v;
                        ord[i]=ord[v]+1;
                    }
                }
            }
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

            List<Integer> res=new LinkedList<>();
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

        public int length(int w) {
            assert w>=0 && w<G.V();
            return ord[w];
        }
    }
}
