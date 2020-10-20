package main.encoder;

import main.util.Node;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Encoder
{
    private HashMap<String, Node> nodes;
    private ArrayList<Node> helpNodes;

    private File inputFile;
    private File outputFile;

    private int wordLength;
    private int textLength;

    private double curLeft;
    private double curRight;

    private int chunkSize;

    private void calculateProbability()
    {
        for (var node : nodes.values())
        {
            helpNodes.add(node);
            node.probability = (double) node.quantity / (double) textLength;
        }

        Collections.sort(helpNodes);

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

    public void read(int wordLength, String inputFileName)
    {
        inputFile = new File(inputFileName);

        if (!inputFile.exists())
        {
            System.out.println("No such file exists");
            return;
        }

        this.wordLength = wordLength;
        nodes = new HashMap<>();
        helpNodes = new ArrayList<>();
        textLength = 0;

        switch (wordLength)
        {
            case 1:
            {
                chunkSize = 9;
                break;
            }

            case 2:
            {
                chunkSize = 5;
                break;
            }

            case 3:
            case 4:
            {
                chunkSize = 3;
                break;
            }

            default:
            {
                chunkSize = 2;
                break;
            }
        }

        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));

            while (true)
            {
                int symbolCode = 0;
                StringBuilder builder = new StringBuilder();

                int i = 0;
                while (i < wordLength && (symbolCode = reader.read()) != -1)
                {
                    builder.append((char) symbolCode);
                    i++;
                }

                if (symbolCode == -1 && i == 0)
                {
                    break;
                }

                String symbol = builder.toString();

                if (nodes.containsKey(symbol))
                {
                    Node node = nodes.get(symbol);
                    node.quantity++;
                }
                else
                {
                    nodes.put(symbol, new Node(symbol));
                }

                textLength++;
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
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));
            DataOutputStream writer = new DataOutputStream(new FileOutputStream(outputFile));

            writer.writeInt(helpNodes.size());
            writer.writeInt(chunkSize);
            writer.writeInt(textLength);
            writer.writeInt(wordLength);
            writer.writeInt(helpNodes.get(helpNodes.size() - 1).text.length());

            for (Node node : helpNodes)
            {
                writer.writeBytes(node.text);
                writer.writeDouble(node.probability);
            }

            boolean loop = true;

            while (loop)
            {
                curLeft = 0.0;
                curRight = 1.0;

                for (int i = 0; i < chunkSize; i++)
                {
                    int symbolCode = 0;
                    StringBuilder builder = new StringBuilder();

                    int j = 0;
                    while (j < wordLength && (symbolCode = reader.read()) != -1)
                    {
                        builder.append((char) symbolCode);
                        j++;
                    }

                    if (symbolCode == -1 && j == 0)
                    {
                        loop = false;
                        break;
                    }

                    String symbol = builder.toString();

                    Node node = nodes.get(symbol);

                    double prevLeft = curLeft;
                    double diff = curRight - prevLeft;

                    curLeft = prevLeft + diff * node.leftBorder;
                    curRight = prevLeft + diff * node.rightBorder;
                }

                double curMid = (curRight + curLeft) / 2.0;

                writer.writeDouble(curMid);
            }

            writer.close();
            reader.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
