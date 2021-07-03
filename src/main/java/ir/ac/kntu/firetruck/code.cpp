#include<bits/stdc++.h>
using namespace std;
const int maxn=22;
int g[maxn][maxn];
int p[maxn];
int aim;
int vis[maxn];
int cnt;
int dfs(int u,int *a,int cur){
    if(u==aim)
    {
        for(int i=0;i<cur;i++)
        printf("%d ",a[i]);
        ++cnt;
        cout<<'\n';
    }
    else 
    for(int v=2;v<maxn;v++)
    {
        if(g[u][v]&&!vis[v]) 
        {
            a[cur]=v;
            vis[v]=1;
            dfs(v,a,cur+1);
            vis[v]=0;
        }
    }
}

int main(){
    int a,b;
    while(cin>>aim){
        memset(g,0,sizeof(g));
        cnt=0;
        while(scanf("%d%d",&a,&b),a,b){
            g[a][b]=1;
            g[b][a]=1;
            memset(vis,0,sizeof(vis));
        }
        p[0]=1;
        dfs(1,p,1);
        printf("There are %d routes from the firestation to streetcorner %d.\n",cnt,aim);
    }
}

