package Algorithm;

import java.util.ArrayList;

class MatrixHelper
{
	ArrayList < ArrayList < Double > > copyMatrix(ArrayList < ArrayList < Double > > first)
	{
		ArrayList < ArrayList < Double > > ans = new ArrayList<>();

		for (int i = 0; i < first.size(); i++)
		{
			ArrayList < Double > tmp = new ArrayList<>();

			for (int j = 0; j < first.get(i).size(); j++)
			{
				tmp.add(first.get(i).get(j));
			}

			ans.add(tmp);
		}

		return ans;
	}

	ArrayList < ArrayList < Double > > mulMatrix(ArrayList < ArrayList < Double > > first, ArrayList < ArrayList < Double > > second)
	{
		if (first.isEmpty() || second.isEmpty() || first.get(0).size() != second.size())
		{
			return null;
		}

		ArrayList < ArrayList < Double > > res = new ArrayList<>();
		
		for (int i = 0; i < first.size(); i++)
		{
			ArrayList < Double > tmp = new ArrayList<>();

			for (int j = 0; j < second.get(0).size(); j++)
			{
				tmp.add(0.0);
			}

			res.add(tmp);
		}

		for (int i = 0; i < first.size(); i++)
		{
			for (int j = 0; j < second.get(0).size(); j++)
			{
				for (int k = 0; k < first.get(0).size(); k++)
				{
					res.get(i).set(j, res.get(i).get(j) + first.get(i).get(k) * second.get(k).get(j));
				}
			}
		}

		return res;
	}

	ArrayList < ArrayList < Double > > binPowMatrix(ArrayList < ArrayList < Double > > matrix, int power)
	{
		if (matrix.isEmpty() || matrix.size() != matrix.get(0).size())
		{
			return null;
		}

		ArrayList < ArrayList < Double > > first = copyMatrix(matrix);
		ArrayList < ArrayList < Double > > res = new ArrayList<>();
		
		for (int i = 0; i < first.size(); i++)
		{
			ArrayList < Double > tmp = new ArrayList<>();

			for (int j = 0; j < first.size(); j++)
			{
				tmp.add(0.0);
			}

			tmp.set(i, 1.0);
	
			res.add(tmp);
		}

		while (power > 0)
		{
			if (power % 2 == 0)
			{
				first = mulMatrix(first, first);
				power /= 2;
			}
			else
			{
				res = mulMatrix(res, first);
				power--;
			}
		}

		return res;
	}
}
