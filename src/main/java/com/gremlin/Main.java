package com.gremlin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gremlin.forismatic.ForismaticGateway;
import com.gremlin.quotely.cli.ArgumentParser;
import com.gremlin.quotely.dao.ForismaticQuoteDao;
import com.gremlin.quotely.model.Language;
import com.gremlin.quotely.cli.QuotelyApp;
import okhttp3.OkHttpClient;

import java.util.Map;

/**
 * A command line application that given a language, will retrieve a random quote in that language and output
 * its text and author
 * <p>
 * To run the application provide the language in command line argument as {@code -l <language>}.
 * Supported languages: "English", "Russian". Defaults to "English" if not provided.
 * <p>
 * Example output:
 * <pre>{@code
 * Text: There are things so deep and complex that only intuition can reach it in our stage of development as human beings.
 * Author: John Astin
 * }</pre>
 */
public class Main {
    public static void main(String[] args) {
        try {
            QuotelyApp app = createNewApp();
            app.run(args, System.out);
        } catch (Exception e) {
            System.out.println("Encountered an error: " + e.getMessage());
        }
    }

    private static QuotelyApp createNewApp() {
        // Wire the object graph manually
        // This can be replaced with an objects injected via some Dependency Injection framework
        return new QuotelyApp(
                new ArgumentParser(),
                // Map of command line argument representation of language to application-internal representation
                Map.of(
                        "English", Language.ENGLISH,
                        "Russian", Language.RUSSIAN
                ),
                new ForismaticQuoteDao(
                        new ForismaticGateway(
                                new OkHttpClient(),
                                new ObjectMapper()
                        ),
                        // Map of application-internal representation of language to Forismatic API representation
                        Map.of(
                                Language.ENGLISH, "en",
                                Language.RUSSIAN, "ru"
                        )
                )
        );
    }
}