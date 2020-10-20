package main.decoder;

import main.util.Node;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Decoder
{
    private HashMap<String, Node> nodes;
    private ArrayList<Node> helpNodes;

    private File inputFile;
    private File outputFile;

    private int wordLength;
    private int lastWordLength;
    private int textLength;

    private ArrayList<Double> mids;

    private int chunkSize;

    private void calculateProbability()
    {
        for (int i = 0; i < helpNodes.size(); i++)
        {
            if (i > 0)
            {
                helpNodes.get(i).leftBorder = helpNodes.get(i - 1).rightBorder;
            }
            else
            {
                helpNodes.get(i).leftBorder = 0.0;
            }

            if (i < helpNodes.size() - 1)
            {
                helpNodes.get(i).rightBorder = helpNodes.get(i).probability + helpNodes.get(i).leftBorder;
            }
            else
            {
                helpNodes.get(i).rightBorder = 1.0;
            }
        }
    }

    public void read(String inputFileName)
    {
        inputFile = new File(inputFileName);

        if (!inputFile.exists())
        {
            System.out.println("No such file exists");
            return;
        }

        nodes = new HashMap<>();
        helpNodes = new ArrayList<>();
        mids = new ArrayList<>();

        try
        {
            DataInputStream reader = new DataInputStream(new FileInputStream(inputFile));

            int numSymbols = reader.readInt();
            chunkSize = reader.readInt();
            textLength = reader.readInt();
            wordLength = reader.readInt();
            lastWordLength = reader.readInt();

            for (int i = 0; i < numSymbols; i++)
            {
                StringBuilder builder = new StringBuilder();

                int howMany;

                if (i == numSymbols - 1)
                {
                    howMany = lastWordLength;
                }
                else
                {
                    howMany = wordLength;
                }

                for (int j = 0; j < howMany; j++)
                {
                    builder.append((char)reader.readByte());
                }

                String symbol = builder.toString();
                double probability = reader.readDouble();

                Node node = new Node(symbol, probability);

                nodes.put(symbol, node);
                helpNodes.add(node);
            }

            try
            {
                while (true)
                {
                    double curMid = reader.readDouble();

                    mids.add(curMid);
                }
            }
            catch (Exception ex)
            {
            }

            reader.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        calculateProbability();
    }

    public void write(String outputFileName)
    {
        outputFile = new File(outputFileName);

        try
        {
            PrintWriter writer = new PrintWriter(new FileOutputStream(outputFile));

            for (int k = 0; k < mids.size(); k++)
            {
                StringBuilder builder = new StringBuilder();

                for (int i = 0; i < Math.min(textLength - k * chunkSize, chunkSize); i++)
                {
                    int left = 0;
                    int right = helpNodes.size() - 1;

                    while (true)
                    {
                        int mid = (right + left) / 2;

                        double newLeft = helpNodes.get(mid).leftBorder;
                        double newRight = helpNodes.get(mid).rightBorder;

                        if (mids.get(k).compareTo(newLeft) < 0)
                        {
                            right = mid - 1;
                        }
                        else if (mids.get(k).compareTo(newRight) >= 0)
                        {
                            left = mid + 1;
                        }
                        else
                        {
                            builder.append(helpNodes.get(mid).text);

                            double dif = newRight - newLeft;
                            mids.set(k, (mids.get(k) - newLeft) / dif);

                            break;
                        }
                    }
                }

                writer.write(builder.toString());
            }

            writer.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
