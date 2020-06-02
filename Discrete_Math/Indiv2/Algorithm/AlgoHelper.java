package Algorithm;

import java.util.ArrayList;

class AlgoHelper
{
	int gcd(int a, int b)
	{
		while (b > 0)
		{
			a %= b;

			int tmp = b;
			b = a;
			a = tmp;
		}

		return a;
	}

	private void topMaxRow(ArrayList < ArrayList < Double > > system, int row)
	{
		double max = -1000000.0;
		int maxInd = -1;

		for (int i = row; i < system.size(); i++)
		{
			double val = system.get(i).get(row);

			if (max < val)
			{
				max = val;
				maxInd = i;
			}
		}
	
		ArrayList < Double > tmp = system.get(row);
		system.set(row, system.get(maxInd));
		system.set(maxInd, tmp);
	}
	
	private void divide(ArrayList < ArrayList < Double > > system, int row)
	{
		for (int i = row; i < system.size(); i++)
		{
			double val = system.get(i).get(row);
			
			for (int j = 0; j < system.get(i).size(); j++)
			{
				system.get(i).set(j, system.get(i).get(j) / val);
			}
		}
	}
	
	private void subtract(ArrayList < ArrayList < Double > > system, int row)
	{
		for (int i = row + 1; i < system.size(); i++)
		{
			for (int j = 0; j < system.get(i).size(); j++)
			{
				system.get(i).set(j, system.get(i).get(j) - system.get(row).get(j));
			}
		}
	}
	
	private void substitute(ArrayList < ArrayList < Double > > system, int row)
	{
		for (int j = system.get(row).size() - 2; j > row; j--)
		{
			double x = system.get(row).get(j);
			double val = system.get(row).get(system.get(0).size() - 1);
			double nextVal = system.get(j).get(system.get(0).size() - 1);

			system.get(row).set(j, 0.0);
			system.get(row).set(system.get(0).size() - 1, val - x * nextVal);
		}
	}

	private ArrayList < Double > solve(ArrayList < ArrayList < Double > > system)
	{
		for (int i = 0; i < system.size(); i++)
		{
			topMaxRow(system, i);
			divide(system, i);
			subtract(system, i);
		}

		for (int i = system.size() - 2; i >= 0; i--)
		{
			substitute(system, i);
		}

		ArrayList < Double > res = new ArrayList<>();

		for (int i = 0; i < system.size(); i++)
		{
			res.add(system.get(i).get(system.get(i).size() - 1));
		}

		return res;
	}

	ArrayList < Double > solveGauss(ArrayList < ArrayList < Double > > matrix)
	{
		ArrayList < ArrayList < Double > > system = new ArrayList<>();

		for (int i = 0; i < matrix.size(); i++)
		{
			ArrayList < Double > tmp = new ArrayList<>();

			if (i == 0)
			{
				for (int j = 0; j < matrix.get(0).size(); j++)
				{
					tmp.add(1.0);
				}

				tmp.add(1.0);
			}
			else
			{
				for (int j = 0; j < matrix.get(0).size(); j++)
				{
					double val = matrix.get(j).get(i);

					if (i == j)
					{
						val -= 1;
					}

					tmp.add(val);
				}

				tmp.add(0.0);
			}

			system.add(tmp);
		}
			
		return solve(system);
	}
}
