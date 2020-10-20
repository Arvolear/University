package main.driver;

import main.decoder.Decoder;
import main.encoder.Encoder;

public class Driver
{
    private void showHelpMessage()
    {
        System.out.println("Welcome to the archiver, based on arithmetical encoding\n" +
                "java -jar arithmetical.jar [-h -e -d] [size] <input file> <output file>\n" +
                "\t -h or --help - shows this message\n" +
                "\t -e or --encode size <input file> <output file> - archive the file\n" +
                "\t -d or --decode <input file> <output file> - unarchive the file\n" +
                "\nExamples:\n" +
                "\t java -jar arithmetical.jar -e 3 ./test.txt\n" +
                "\t java -jar arithmetical.jar -d ./test.arv\n");
    }

    private void encode(String[] args)
    {
        if (args.length == 2)
        {
            System.out.println("No input file provided");
            return;
        }

        int length = Integer.parseInt(args[1]);
        String inputFileName = args[2];
        String outputFileName = "";

        if (args.length == 3)
        {
            outputFileName = inputFileName.substring(0, inputFileName.lastIndexOf(".")) + ".arv";
        }
        else
        {
            outputFileName = args[3];
        }

        Encoder encoder = new Encoder();

        encoder.read(length, inputFileName);
        encoder.write(outputFileName);
    }

    private void decode(String[] args)
    {
        if (args.length == 1)
        {
            System.out.println("No input file provided");
            return;
        }

        String inputFileName = args[1];
        String outputFileName = "";

        if (args.length == 2)
        {
            outputFileName = inputFileName.substring(0, inputFileName.lastIndexOf(".")) + ".arv";
        }
        else
        {
            outputFileName = args[2];
        }

        Decoder decoder = new Decoder();

        decoder.read(inputFileName);
        decoder.write(outputFileName);
    }

    public void perform(String[] args)
    {
        if (args.length == 0)
        {
            System.out.println("No arguments provided");
            return;
        }

        switch (args[0])
        {
            case "--help":
            case "-h":
            {
                showHelpMessage();
                break;
            }

            case "--encode":
            case "-e":
            {
                encode(args);
                break;
            }

            case "--decode":
            case "-d":
            {
                decode(args);
                break;
            }

            default:
            {
                System.out.println("Wrong argument provided");
                break;
            }
        }
    }
}
