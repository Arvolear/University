#include <iostream>
#include <vector>

using namespace std;

vector < vector < int > > graph; // adjacency matrix
vector < int > vertices; // all vertices the graph has

void find(vector < int > &indices)
{
    bool complete = true;
    for (size_t i = 0; i < indices.size(); i++)
    {
        for (size_t j = 0; j < indices.size(); j++)
        {
            if (i != j)
            {
                if (!graph[i][j])
                {
                    complete = false;
                }
            }
        }

        if (!complete)
        {
            break;
        }
    }
    
    if (complete)
    {
        for (size_t i = 0; i < indices.size(); i++)
        {
            cout << indices[i] << ' ';
        }

        cout << endl;
    }

    int pos = -1;
    for (int i = (int)indices.size() - 1; i >= 0; i--)
    {
        if (indices[i] < (int)vertices.size() - (i + 1))
        {
            pos = i;
        }
    }

    if (pos == -1)
    {
        return;
    }

    indices[pos]++;

    for (int i = pos - 1; i >= 0; i--)
    {
        indices[i] = indices[i + 1] + 1;
    }

    find(indices);
}

int main()
{
    int n, k; // n = vertices, k = edges

    cin >> n >> k;

    graph.resize(n);

    for (int i = 0; i < n; i++)
    {
        graph[i].resize(n);
    }

    vertices.resize(n);

    for (int i = 0; i < k; i++)
    {
        int from, to;

        cin >> from >> to;

        graph[from][to] = 1;
        graph[to][from] = 1;

        if (!vertices[from])
        {
            vertices[from] = from;
        }

        if (!vertices[to])
        {
            vertices[to] = to;
        }
    }

    int m; // m = m-vertex complete graph

    cin >> m;

    vector < int > indices;

    for (int i = 0; i < m; i++)
    {
        indices.push_back(m - (i + 1));
    }

    find(indices);

    return 0;
}
