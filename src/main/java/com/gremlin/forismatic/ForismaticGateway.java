package com.gremlin.forismatic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gremlin.forismatic.model.QuoteDTO;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.io.UncheckedIOException;

/**
 * Gateway to interact with Forismatic APIs
 * <p>
 * Uses OkHttpClient and Jackson for REST APIs and JSON deserialization respectively.
 */
@RequiredArgsConstructor
public class ForismaticGateway {
    private static final String GET_QUOTE_BY_LANGUAGE_URL_FORMAT =
            "https://api.forismatic.com/api/1.0/?method=getQuote&format=json&lang=%s";

    private final OkHttpClient client;
    private final ObjectMapper objectMapper;

    public QuoteDTO getQuoteByLanguage(String language) {
        Response response = getQuoteResponse(language);
        return deserializeResponse(response, QuoteDTO.class);
    }

    private Response getQuoteResponse(String language) {
        Request request = new Request.Builder()
                .url(String.format(GET_QUOTE_BY_LANGUAGE_URL_FORMAT, language))
                .build();
        try {
            return client.newCall(request).execute();
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to invoke Forismatic GetQuote API", e);
        }
    }

    private <T> T deserializeResponse(Response response, Class<T> clazz) {
        try {
            return objectMapper.readValue(response.body().byteStream(), clazz);
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to deserialize Forismatic API response", e);
        }
    }
}
