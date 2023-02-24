package com.gremlin.quotely.cli;

import com.gremlin.quotely.model.Language;
import com.gremlin.quotely.model.Quote;
import com.gremlin.quotely.dao.QuoteDao;
import lombok.RequiredArgsConstructor;

import java.io.PrintStream;
import java.util.Map;

/**
 * Since application uses a different representation of language than the command line arguments
 * e.g. ({@link Language#ENGLISH} for "English"), a map to convert a language argument to its application
 * representation needs to be provided in the constructor.
 */
@RequiredArgsConstructor
public class QuotelyApp {
    // Visible for testing
    static final String QUOTE_TEXT_PREFIX = "Text: ";
    // Visible for testing
    static final String QUOTE_AUTHOR_PREFIX = "Author: ";

    private final ArgumentParser argumentParser;
    private final Map<String, Language> languageArgumentToLanguage;
    private final QuoteDao quoteDao;

    public void run(String[] args, PrintStream printStream) {
        String languageName = argumentParser.getLanguageArgument(args);
        Language language = getLanguage(languageName);
        Quote quote = quoteDao.getQuote(language);
        printStream.println(QUOTE_TEXT_PREFIX + quote.getText());
        printStream.println(QUOTE_AUTHOR_PREFIX + quote.getAuthor());
    }

    private Language getLanguage(String languageName) {
        if (languageArgumentToLanguage.containsKey(languageName)) {
            return languageArgumentToLanguage.get(languageName);
        } else {
            throw new IllegalArgumentException("Unsupported language: " + languageName);
        }
    }
}
