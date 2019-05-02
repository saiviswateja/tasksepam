import java.io.BufferedReader;
import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;
public class Prims {

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String s;
        StringTokenizer st;
        s=br.readLine().trim();                
            st=new StringTokenizer(s," ");
        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());
        
        LinkedList<Node> adj[]=new LinkedList[n+1];       
        while(m-->0)
        {
                  s=br.readLine().trim();                         
                  st=new StringTokenizer(s);
                  int v1=Integer.parseInt(st.nextToken());
                  int v2=Integer.parseInt(st.nextToken());
                  int w=Integer.parseInt(st.nextToken());
                  if(adj[v1]==null)
                     {
                       adj[v1]=new LinkedList<Node>();
                     }
                     adj[v1].add(new Node(v2,w));
                     if(adj[v2]==null)
                     {
                        adj[v2]=new LinkedList<Node>();
                     }
                     adj[v2].add(new Node(v1, w));                  
              
        }
        int source=Integer.parseInt(br.readLine());
        long ans=prims(adj,n,source);
        System.out.println(ans);

    }
    private static long prims(LinkedList<Node>[] adj, int n,int source) {    
        long minw=0l;
        
        long key[]=new long[n+1];
        boolean visited[]=new boolean[n+1];
        for(int i=1;i<=n;i++)
        {
            key[i]=Long.MAX_VALUE;
        }
        
        key[source]=0l;
        for(int i=1;i<=n;i++)
        {
            int u=min_Key(key,visited);
            visited[u]=true;
            if(adj[u]!=null)
            {
                Iterator<Node> itr=adj[u].iterator();
                while(itr.hasNext())
                {
                    Node ob=itr.next();
                    int v=ob.n;
                    long w=ob.W;
                    if(!visited[v]&&key[v]>w)
                    {
                        key[v]=w;
                        
                    }
                }
            }
        }
        for(int i=1;i<=n;i++)
        {
            minw+=key[i];
        }
        return minw;
    }

private static int min_Key(long[] key, boolean[] visited) {
    int minind=0;
    long min=Long.MAX_VALUE;
    for(int i=1;i<key.length;i++)
    {
        if(!visited[i]&&key[i]<min)
        {
            min=key[i];
            minind=i;
            
        }
    }
    return minind;
}
private static class Node
{
    int n;
    int W;
    public Node(int n1,int w1) {
        n=n1;
        W=w1;
    }
}
}
