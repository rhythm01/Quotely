package com.gremlin.quotely.dao;

import com.gremlin.quotely.model.Language;
import com.gremlin.quotely.model.Quote;

/**
 * Interface for a data access object used for retrieving a {@link Quote}
 */
public interface QuoteDao {
    Quote getQuote(Language language);
}
