package com.gremlin.quotely.cli;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArgumentParserTest {
    private static final String DUMMY_LANGUAGE_ARGUMENT = "TestLanguage";

    private ArgumentParser testSubject;

    @Before
    public void setUp() {
        testSubject = new ArgumentParser();
    }

    @Test
    public void getLanguageArgument_noArgumentsProvided() {
        assertEquals(ArgumentParser.LANGUAGE_ARGUMENT_DEFAULT_VALUE, testSubject.getLanguageArgument(new String[] {}));
    }

    @Test
    public void getLanguageArgument_languageArgumentProvided() {
        assertEquals(DUMMY_LANGUAGE_ARGUMENT, testSubject.getLanguageArgument(new String[] {
                "-" + ArgumentParser.LANGUAGE_OPTION,
                DUMMY_LANGUAGE_ARGUMENT
        }));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getLanguageArgument_languageArgumentNotProvided() {
        testSubject.getLanguageArgument(new String[] {"-" + ArgumentParser.LANGUAGE_OPTION});
    }
}