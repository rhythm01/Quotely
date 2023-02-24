package com.gremlin.quotely.cli;

import com.gremlin.quotely.dao.QuoteDao;
import com.gremlin.quotely.model.Language;
import com.gremlin.quotely.model.Quote;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.PrintStream;
import java.util.Map;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class QuotelyAppTest {
    private static final String LANGUAGE_ARGUMENT_ENGLISH = "English";
    private static final Map<String, Language> LANGUAGE_ARGUMENT_TO_LANGUAGE = Map.of(
            LANGUAGE_ARGUMENT_ENGLISH, Language.ENGLISH);
    private static final Quote QUOTE = new Quote("Test text", "Test author");

    @Mock
    private ArgumentParser mockArgumentParser;
    @Mock
    private QuoteDao mockQuoteDao;
    @Mock
    private PrintStream mockPrintStream;

    private QuotelyApp testSubject;

    @Before
    public void setUp() {
        testSubject = new QuotelyApp(
                mockArgumentParser,
                LANGUAGE_ARGUMENT_TO_LANGUAGE,
                mockQuoteDao
        );
    }

    @Test
    public void run() {
        String[] arg = new String[] {"-", LANGUAGE_ARGUMENT_ENGLISH};
        when(mockArgumentParser.getLanguageArgument(arg))
                .thenReturn(LANGUAGE_ARGUMENT_ENGLISH);
        when(mockQuoteDao.getQuote(Language.ENGLISH))
                .thenReturn(QUOTE);

        testSubject.run(arg, mockPrintStream);

        verify(mockArgumentParser).getLanguageArgument(arg);
        verify(mockQuoteDao).getQuote(Language.ENGLISH);
        verify(mockPrintStream).println(QuotelyApp.QUOTE_TEXT_PREFIX + QUOTE.getText());
        verify(mockPrintStream).println(QuotelyApp.QUOTE_AUTHOR_PREFIX + QUOTE.getAuthor());
    }
}