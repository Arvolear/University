package Algorithm;

import java.util.ArrayList;
import java.util.LinkedList;

class GraphHelper
{
	ArrayList < Integer > bfs(ArrayList < ArrayList < Double > > matrix, int vertex)
	{
		ArrayList < Integer > used = new ArrayList<>();

		for (int i = 0; i < matrix.size(); i++)
		{
			used.add(-1);
		}

		LinkedList < Integer > queue = new LinkedList<>();

		queue.addLast(vertex);
		used.set(vertex, 0);

		while (!queue.isEmpty())
		{
			int v = queue.pollFirst();

			for (int i = 0; i < matrix.get(v).size(); i++)
			{
				if (!matrix.get(v).get(i).equals(0.0) && used.get(i) == -1)
				{
					queue.addLast(i);
					used.set(i, used.get(v) + 1);
				}
			}
		}

		return used;
	}

	private boolean dfs(ArrayList < ArrayList < Double > > matrix, ArrayList < Integer > used, int from, int to)
	{
		used.set(from, 1);

		for (int i = 0; i < matrix.get(from).size(); i++)
		{
			if (matrix.get(from).get(i) > 0.0)
			{
				if (from != to && i == to)
				{
					return true;
				}

				if (used.get(i) > 0)
				{
					continue;
				}

				if (dfs(matrix, used, i, to))
				{
					return true;
				}
			}
		}

		return false;
	}

	boolean dfs(ArrayList < ArrayList < Double > > matrix, int from, int to)
	{
		ArrayList < Integer > used = new ArrayList<>();

		for (int i = 0; i < matrix.size(); i++)
		{
			used.add(-1);
		}

		return dfs(matrix, used, from, to);
	}

	private ArrayList < ArrayList < Double > > transpose(ArrayList < ArrayList < Double > > matrix)
	{
		ArrayList < ArrayList < Double > > ans = new ArrayList<>();

		for (int i = 0; i < matrix.get(0).size(); i++)
		{
			ArrayList < Double > tmp = new ArrayList<>();			

			for (int j = 0; j < matrix.size(); j++)
			{
				tmp.add(0.0);
			}

			ans.add(tmp);
		}
		
		for (int i = 0; i < matrix.size(); i++)
		{
			for (int j = 0; j < matrix.get(0).size(); j++)
			{
				ans.get(j).set(i, matrix.get(i).get(j));
			}
		}

		return ans;
	}

	private void strongDfs1(ArrayList < ArrayList < Double > > matrix, ArrayList < Integer > used, ArrayList < Integer > order, int v)
	{
		used.set(v, 1);

		for (int i = 0; i < matrix.get(v).size(); i++)
		{
			if (matrix.get(v).get(i) > 0.0 && used.get(i) < 1)
			{
				strongDfs1(matrix, used, order, i);
			}
		}

		order.add(v);
	}
	
	private void strongDfs2(ArrayList < ArrayList < Double > > xirtam, ArrayList < Integer > used, ArrayList < Integer > component, int v)
	{
		used.set(v, 1);
		component.add(v);

		for (int i = 0; i < xirtam.get(v).size(); i++)
		{
			if (xirtam.get(v).get(i) > 0.0 && used.get(i) < 1)
			{
				strongDfs2(xirtam, used, component, i);
			}
		}
	}

	ArrayList < ArrayList < Integer > > strongComponents(ArrayList < ArrayList < Double > > matrix)
	{
		ArrayList < ArrayList < Double > > xirtam = transpose(matrix);	
		ArrayList < ArrayList < Integer > > ans = new ArrayList<>();	
		ArrayList < Integer > used = new ArrayList<>();
		ArrayList < Integer > order = new ArrayList<>();

		for (int i = 0; i < matrix.size(); i++)
		{
			used.add(0);
		}
		
		for (int i = 0; i < matrix.size(); i++)
		{
			if (used.get(i) < 1)
			{
				strongDfs1(matrix, used, order, i);
			}
		}
		
		used.clear();

		for (int i = 0; i < matrix.size(); i++)
		{
			used.add(0);
		}

		for (int i = 0; i < matrix.size(); i++)
		{
			int v = order.get(matrix.size() - i - 1);

			if (used.get(v) < 1)
			{
				ArrayList < Integer > component = new ArrayList<>();

				strongDfs2(xirtam, used, component, v);

				ans.add(component);
			}
		}

		return ans;
	}
}
