package com.gremlin.quotely.dao;

import com.gremlin.forismatic.ForismaticGateway;
import com.gremlin.forismatic.model.QuoteDTO;
import com.gremlin.quotely.model.Language;
import com.gremlin.quotely.model.Quote;
import lombok.RequiredArgsConstructor;

import java.util.Map;

/**
 * Implementation of {@link QuoteDao} that retrieves {@link Quote} from Forismatic API
 * <p>
 * Uses {@link ForismaticGateway} to interact with the Forismatic API. An implementation of this gateway needs
 * to be provided in the constructor.
 * <p>
 * Since Forismatic API use a different representation of language (e.g. "en" for English), a map to convert
 * {@link Language} to its Forismatic API representation needs to be provided in constructor.
 */
@RequiredArgsConstructor
public class ForismaticQuoteDao implements QuoteDao {
    private final ForismaticGateway forismaticGateway;
    private final Map<Language, String> languageToForismaticLanguageMapper;

    @Override
    public Quote getQuote(Language language) {
        String forismaticLanguage = getForismaticLanguage(language);
        QuoteDTO quoteDTO = forismaticGateway.getQuoteByLanguage(forismaticLanguage);
        return mapToQuote(quoteDTO);
    }

    private String getForismaticLanguage(Language language) {
        if (languageToForismaticLanguageMapper.containsKey(language)) {
            return languageToForismaticLanguageMapper.get(language);
        } else {
            throw new IllegalArgumentException("Cannot map " + language.name() + " to its Forismatic representation");
        }
    }

    private Quote mapToQuote(QuoteDTO quoteDTO) {
        return new Quote(quoteDTO.getQuoteText(), quoteDTO.getQuoteAuthor());
    }
}
