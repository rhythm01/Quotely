package com.gremlin.quotely.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Utility class that parses the command line arguments and retrieves the required argument values
 */
public class ArgumentParser {
    // Visible for testing
    static final String LANGUAGE_OPTION = "l";
    // Visible for testing
    static final String LANGUAGE_ARGUMENT_DEFAULT_VALUE = "English";


    public String getLanguageArgument(String[] args) {
        Option languageOption = Option.builder()
                .option(LANGUAGE_OPTION)
                .hasArg(true)
                .required(false)
                .build();
        Options options = new Options()
                .addOption(languageOption);
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine commandLine = parser.parse(options, args);
            return commandLine.getOptionValue(languageOption, LANGUAGE_ARGUMENT_DEFAULT_VALUE);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Failed to parse the input. Please check input and try again.", e);
        }
    }
}
