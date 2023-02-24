package com.gremlin.forismatic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gremlin.forismatic.model.QuoteDTO;
import okhttp3.OkHttpClient;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ForismaticGatewayIntegrationTest {
    private static final String ENGLISH_LANGUAGE = "en";
    private ForismaticGateway testSubject;

    @Before
    public void setUp() {
        testSubject = new ForismaticGateway(new OkHttpClient(), new ObjectMapper());
    }

    @Test
    public void getQuoteByLanguage() {
        QuoteDTO quote = testSubject.getQuoteByLanguage(ENGLISH_LANGUAGE);
        assertNotNull(quote.getQuoteText());
        assertNotNull(quote.getQuoteAuthor());
    }
}