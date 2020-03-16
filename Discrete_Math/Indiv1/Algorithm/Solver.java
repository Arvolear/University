package Algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;

public class Solver
{
	private class Pair <T, K>
	{
		T first;
		K second;

		Pair(T first, K second)
		{
			this.first = first;
			this.second = second;
		}
	}

	private ArrayList < ArrayList < Double > > weights;
	private ArrayList < Double > producers;
	private ArrayList < Double > consumers;

	private ArrayList < ArrayList < Double > > solution;

	private ArrayList < ArrayList < Pair < Double, Boolean > > > potentialsTable;

	private ArrayList < Double > producersPotentials; 
	private ArrayList < Double > consumersPotentials;

	private int dummyProducer; 
	private int dummyConsumer;

	public Solver() 
	{
		dummyProducer = 0;
		dummyConsumer = 0;

		solution = new ArrayList<>();

		potentialsTable = new ArrayList<>();

		producersPotentials = new ArrayList<>();
		consumersPotentials = new ArrayList<>();

		producers = new ArrayList<>();
		consumers = new ArrayList<>();
	}

	public void load(String path) throws Exception
	{
		Loader loader = new Loader(path);

		loader.load();

		weights = loader.getWeights();
		producers = loader.getProducers();
		consumers = loader.getConsumers();
	}

	private void fixOpened()
	{
		double consumersTotal = 0;
		double producersTotal = 0;

		for (int i = 0; i < consumers.size(); i++)
		{
			consumersTotal += consumers.get(i);
		}

		for (int i = 0; i < producers.size(); i++)
		{
			producersTotal += producers.get(i);
		}

		if (producersTotal < consumersTotal)
		{
			dummyProducer = 1;

			ArrayList < Double > tmp = new ArrayList<>();

			for (int i = 0; i < weights.get(0).size(); i++)
			{
				tmp.add(0.0);
			}

			weights.add(tmp);
			producers.add(consumersTotal - producersTotal);
		}
		else if (producersTotal > consumersTotal)
		{
			dummyConsumer = 1;

			for (int i = 0; i < weights.size(); i++)
			{
				weights.get(i).add(0.0);
			}

			consumers.add(producersTotal - consumersTotal);
		}
	}

	private void findBaseSolution()
	{
		solution.clear();

		ArrayList < Double > localProducers = new ArrayList<>();
		ArrayList < Double > localConsumers = new ArrayList<>();

		for (int i = 0; i < weights.size(); i++)
		{
			localProducers.add(0.0);
		}

		for (int j = 0; j < weights.get(0).size(); j++)
		{
			localConsumers.add(0.0);
		}

		for (int i = 0; i < weights.size(); i++)
		{
			ArrayList < Double > tmp = new ArrayList<>();

			for (int j = 0; j < weights.get(i).size(); j++)
			{
				double toAdd = Math.max(0, Math.min(consumers.get(j) - localConsumers.get(j), producers.get(i) - localProducers.get(i)));

				tmp.add(toAdd);

				localProducers.set(i, localProducers.get(i) + toAdd);
				localConsumers.set(j, localConsumers.get(j) + toAdd);
			}

			solution.add(tmp);
		}
	}

	private enum Direction
	{
		NONE,
		UP, 
		LEFT,
		HORIZONTAL,
		VERTICAL;
	}

	private class BFSNode
	{
		int row;
		int col;

		Direction dir;

		BFSNode(int row, int col)
		{
			this.row = row;
			this.col = col;
			this.dir = Direction.NONE;
		}

		BFSNode(int row, int col, Direction dir)
		{
			this.row = row;
			this.col = col;
			this.dir = dir;
		}
	}

	private void findPotentials()
	{
		potentialsTable.clear();

		producersPotentials.clear();
		consumersPotentials.clear();

		ArrayList < Node > randomElems = new ArrayList<>();

		int toAdd = producers.size() + consumers.size() - 1;

		for (int i = 0; i < solution.size(); i++)
		{
			ArrayList < Pair < Double, Boolean > > tmp = new ArrayList<>();

			for (int j = 0; j < solution.get(i).size(); j++)
			{
				if (solution.get(i).get(j) > 0.0)
				{
					tmp.add(new Pair<>(weights.get(i).get(j), true));	
					toAdd--;
				}
				else
				{
					tmp.add(new Pair<>(0.0, false));
					randomElems.add(new Node(i, j));
				}
			}

			potentialsTable.add(tmp);
		}

		while (true)
		{
			Collections.shuffle(randomElems);

			ArrayList < Boolean > producersUsedPotentials = new ArrayList<>();
			ArrayList < Boolean > consumersUsedPotentials = new ArrayList<>();

			for (int i = 0; i < weights.size(); i++)
			{
				producersPotentials.add(0.0);
				producersUsedPotentials.add(false);
			}

			for (int j = 0; j < weights.get(0).size(); j++)
			{
				consumersPotentials.add(0.0);
				consumersUsedPotentials.add(false);
			}

			for (int i = 0; i < toAdd; i++)
			{
				potentialsTable.get(randomElems.get(i).row).set(randomElems.get(i).col, new Pair<>(0.0, true));
			}

			LinkedList < BFSNode > queue = new LinkedList<>();

			queue.add(new BFSNode(potentialsTable.size() - 1, potentialsTable.get(0).size() - 1, Direction.UP));

			while (!queue.isEmpty())
			{
				// first <----------------- last queue
				BFSNode node = queue.pollFirst();

				switch (node.dir)
				{
					case UP:
						{
							for (int i = potentialsTable.size() - 1; i >= 0; i--)
							{
								double cell = potentialsTable.get(i).get(node.col).first;
								boolean partOfWeight = potentialsTable.get(i).get(node.col).second;

								if (partOfWeight)
								{
									double knownPotential = consumersPotentials.get(node.col);

									if (!producersUsedPotentials.get(i))
									{
										producersPotentials.set(i, cell - knownPotential);

										producersUsedPotentials.set(i, true);
										queue.add(new BFSNode(i, node.col, Direction.LEFT));
									}
								}
							}

							break;
						}

					case LEFT:
						{
							for (int j = potentialsTable.get(0).size() - 1; j >= 0; j--)
							{
								double cell = potentialsTable.get(node.row).get(j).first;
								boolean partOfWeight = potentialsTable.get(node.row).get(j).second;

								if (partOfWeight)
								{
									double knownPotential = producersPotentials.get(node.row);

									if (!consumersUsedPotentials.get(j))
									{
										consumersPotentials.set(j, cell - knownPotential);

										consumersUsedPotentials.set(j, true);
										queue.add(new BFSNode(node.row, j, Direction.UP));
									}
								}
							}

							break;
						}
				}
			}

			boolean shallBreak = true;

			for (int i = 0; i < producersUsedPotentials.size(); i++)
			{
				if (!producersUsedPotentials.get(i))
				{
					shallBreak = false;
					break;
				}
			}

			if (shallBreak)
			{
				for (int i = 0; i < consumersUsedPotentials.size(); i++)
				{
					if (!consumersUsedPotentials.get(i))
					{
						shallBreak = false;
						break;
					}
				}
			}

			if (!shallBreak)
			{
				for (int i = 0; i < toAdd; i++)
				{
					potentialsTable.get(randomElems.get(i).row).set(randomElems.get(i).col, new Pair<>(0.0, false));
				}	
			}
			else
			{
				break;
			}
		}

		for (int i = 0; i < potentialsTable.size(); i++)
		{
			for (int j = 0; j < potentialsTable.get(i).size(); j++)
			{
				if (!potentialsTable.get(i).get(j).second)
				{
					potentialsTable.get(i).set(j, new Pair<>(consumersPotentials.get(j) + producersPotentials.get(i), false));
				}
			}
		}
	}

	private class Node
	{
		int row;
		int col;

		double val;

		Node(int row, int col)
		{
			this.row = row;
			this.col = col;
			this.val = 0.0;
		}

		Node(int row, int col, double val)
		{
			this.row = row;
			this.col = col;
			this.val = val;
		}
	}

	private boolean findPath(Direction dir, ArrayList < Node > path, ArrayList < Integer > rowsAmount, ArrayList < Integer > colsAmount)
	{
		Node start = path.get(0);
		Node cur = path.get(path.size() - 1);

		switch (dir)
		{
			case HORIZONTAL:
				{
					if (cur.col != start.col && cur.row == start.row && path.size() > 3)
					{
						// path is found
						return true;
					}

					for (int j = 0; j < colsAmount.size(); j++)
					{
						// also checks for used
						if (potentialsTable.get(cur.row).get(j).second)
						{
							if (rowsAmount.get(cur.row) < 2 && colsAmount.get(j) < 2)
							{
								potentialsTable.get(cur.row).set(j, new Pair<>(potentialsTable.get(cur.row).get(j).first, false)); 

								rowsAmount.set(cur.row, rowsAmount.get(cur.row) + 1);
								colsAmount.set(j, colsAmount.get(j) + 1);

								path.add(new Node(cur.row, j, -cur.val));

								// recursive call
								if (findPath(Direction.VERTICAL, path, rowsAmount, colsAmount))
								{
									return true;
								}

								potentialsTable.get(cur.row).set(j, new Pair<>(potentialsTable.get(cur.row).get(j).first, true)); 

								rowsAmount.set(cur.row, rowsAmount.get(cur.row) - 1);
								colsAmount.set(j, colsAmount.get(j) - 1);

								path.remove(path.size() - 1);
							}
						}
					}

					break;
				}

			case VERTICAL:
				{	
					if (cur.row != start.row && cur.col == start.col && path.size() > 3)
					{
						// path is found
						return true;
					}

					for (int i = 0; i < rowsAmount.size(); i++)
					{
						// also checks for used
						if (potentialsTable.get(i).get(cur.col).second)
						{
							if (rowsAmount.get(i) < 2 && colsAmount.get(cur.col) < 2)
							{
								potentialsTable.get(i).set(cur.col, new Pair<>(potentialsTable.get(i).get(cur.col).first, false)); 

								rowsAmount.set(i, rowsAmount.get(i) + 1);
								colsAmount.set(cur.col, colsAmount.get(cur.col) + 1);

								path.add(new Node(i, cur.col, -cur.val));

								// recursive call
								if (findPath(Direction.HORIZONTAL, path, rowsAmount, colsAmount))
								{
									return true;
								}

								potentialsTable.get(i).set(cur.col, new Pair<>(potentialsTable.get(i).get(cur.col).first, true)); 

								rowsAmount.set(i, rowsAmount.get(i) - 1);
								colsAmount.set(cur.col, colsAmount.get(cur.col) - 1);

								path.remove(path.size() - 1);
							}
						}
					}

					break;
				}
		}

		return false;
	}

	private boolean tryOptimize()
	{	
		Node minNode = new Node(-1, -1, Double.MAX_VALUE);

		for (int i = 0; i < potentialsTable.size(); i++)
		{
			for (int j = 0; j < potentialsTable.get(i).size(); j++)
			{
				if (!potentialsTable.get(i).get(j).second)
				{
					double diff = weights.get(i).get(j) - potentialsTable.get(i).get(j).first;

					if (diff < minNode.val)
					{
						minNode.row = i;
						minNode.col = j;

						minNode.val = diff;
					}
				}
			}
		}

		if (minNode.val >= 0.0)
		{
			// can't optimize
			return false;
		}

		Node start = new Node(minNode.row, minNode.col, 1.0);

		ArrayList < Integer > rowsAmount = new ArrayList<>();
		ArrayList < Integer > colsAmount = new ArrayList<>();

		for (int i = 0; i < weights.size(); i++)
		{
			rowsAmount.add(0);
		}

		for (int j = 0; j < weights.get(0).size(); j++)
		{
			colsAmount.add(0);
		}

		rowsAmount.set(start.row, 1);
		colsAmount.set(start.col, 1);

		ArrayList < Node > path = new ArrayList<>();
		path.add(start);

		findPath(Direction.HORIZONTAL, path, rowsAmount, colsAmount);	

		double minValue = Double.MAX_VALUE;

		for (int i = 0; i < path.size(); i++)
		{
			// find MIN -
			if (path.get(i).val < 0.0)
			{
				minValue = Math.min(solution.get(path.get(i).row).get(path.get(i).col), minValue);
			}
		}

		for (int i = 0; i < path.size(); i++)
		{
			if (path.get(i).val < 0.0)
			{
				double solutionVal = solution.get(path.get(i).row).get(path.get(i).col);
				solution.get(path.get(i).row).set(path.get(i).col, solutionVal - minValue);
			}
			else if (path.get(i).val > 0.0)
			{
				double solutionVal = solution.get(path.get(i).row).get(path.get(i).col);
				solution.get(path.get(i).row).set(path.get(i).col, solutionVal + minValue);	
			}
		}

		return true;
	}

	public void solve()
	{
		fixOpened();
		findBaseSolution();

		while (true)
		{
			findPotentials();

			if (!tryOptimize())
			{
				break;
			}
		}
	}

	public void displaySolution()
	{
		for (int i = 0; i < solution.size() - dummyProducer; i++)
		{
			for (int j = 0; j < solution.get(i).size() - dummyConsumer; j++)
			{
				System.out.printf("%10.2f ", solution.get(i).get(j));
			}

			System.out.printf("%10.2f\n", producers.get(i));
		}

		for (int i = 0; i < consumers.size() - dummyConsumer; i++)
		{
			System.out.printf("%10.2f ", consumers.get(i));
		}

		System.out.println();
	}

	public Object[][] getSolution()
	{
		Object result[][] = new Object[solution.size() + 1][];

		for (int i = 0; i < solution.size(); i++)
		{
			Object objs[] = new Object[solution.get(i).size() + 1];

			for (int j = 0; j < solution.get(i).size(); j++)
			{
				objs[j] = solution.get(i).get(j);
			}

			objs[objs.length - 1] = producers.get(i);

			result[i] = objs;
		}

		result[result.length - 1] = new Object[consumers.size() + 1];

		for (int i = 0; i < consumers.size(); i++)
		{
			result[result.length - 1][i] = consumers.get(i);
		}

		return result;
	}

	public Object[][] getInput()
	{
		Object result[][] = new Object[weights.size() + 1][];

		for (int i = 0; i < weights.size(); i++)
		{
			Object objs[] = new Object[weights.get(i).size() + 1];

			for (int j = 0; j < weights.get(i).size(); j++)
			{
				objs[j] = weights.get(i).get(j);
			}

			objs[objs.length - 1] = producers.get(i);

			result[i] = objs;
		}

		result[result.length - 1] = new Object[consumers.size() + 1];

		for (int i = 0; i < consumers.size(); i++)
		{
			result[result.length - 1][i] = consumers.get(i);
		}

		return result;
	}

	public double getF()
	{
		double res = 0.0;

		for (int i = 0; i < solution.size(); i++)
		{
			for (int j = 0; j < solution.get(i).size(); j++)
			{
				res += solution.get(i).get(j) * weights.get(i).get(j);
			}
		}

		return res;
	}
}
