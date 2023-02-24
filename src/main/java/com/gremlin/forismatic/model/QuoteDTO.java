package com.gremlin.forismatic.model;

import lombok.Data;

/**
 * Model to represent Quote entity in Forismatic APIs
 * <p>
 * Will be used for deserializing from JSON, hence public setters and a public no-args constructor is provided.
 */
@Data
public class QuoteDTO {
    private String quoteText;
    private String quoteAuthor;
    private String senderName;
    private String senderLink;
    private String quoteLink;
}
