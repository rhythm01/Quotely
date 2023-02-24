package com.gremlin.quotely.model;

import lombok.Value;

/**
 * Model for application's representation of quote
 */
@Value
public class Quote {
    String text;
    String author;
}
