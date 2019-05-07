import org.apache.commons.cli.*;

import java.io.IOException;
import java.util.Arrays;

public class CommandLineBuilder {

    /**
     * statyczne opcje commandline'a
     */
    private static final Options options = new Options();
    private final Option optionf = Option.builder("f").required().hasArg().desc("Path to the file to be processed").build();
    private final Option optionn = Option.builder("n").required(false).hasArgs().desc("Names of the authors to be found").build();
    private final Option optiont = Option.builder("t").required(false).hasArgs().desc("Types to be found").build();
    private final Option optionh = Option.builder("h").required(false).desc("Show help").build();

    public CommandLineBuilder(){
        options.addOption(optionf);
        options.addOption(optionn);
        options.addOption(optiont);
        options.addOption(optionh);
    }

    private static CommandLine generateCommandLine(String[] args){
        final CommandLineParser lineParser = new DefaultParser();
        CommandLine commandLine = null;
        try
        {
            commandLine = lineParser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(
                    "Unable to parse command-line arguments "
                            + Arrays.toString(args) + " due to: "
                            + e);
        }
        return commandLine;
    }

    private static void generateHelp(){
        String header = "Parse your BibTeX file";
        String footer = "BibTeX parser by Ania Banaszak";
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("BibTeX parser", header, options, footer,true);
    }

    /**
     * @param args to argumenty z linii komend przekazane do programu
     *  w zależności od arguementów wywołuje odpowiednie metody generateHelp, Search.*, Parse.parse
     */
    public static void runParser(String[] args){
        if(args.length == 0){
            generateHelp();
            return;
        }
        CommandLine commandLine = generateCommandLine(args);
        if(commandLine.hasOption("f")){
            try {
                Parser.parse(commandLine.getOptionValue("f"));
            }catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        if(commandLine.hasOption("t")){
            try{
                Search.categorySearch(commandLine.getOptionValues("t"));
            }catch (NoMatchingBibTeXItemFound e){
                System.out.println(e.getMessage());
            }
        }
        if(commandLine.hasOption("n")){
            try {
                Search.namesSearch(commandLine.getOptionValues("n"));
            }catch (NoMatchingBibTeXItemFound e){
                System.out.println(e.getMessage());
            }catch (WrongNameFormat e){
                System.out.println(e.getMessage());
            }
        }
        if(commandLine.hasOption("h")){
            generateHelp();
        }
    }
}
