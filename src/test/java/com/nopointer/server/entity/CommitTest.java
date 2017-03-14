package com.nopointer.server.entity;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CommitTest {
    private Commit commitStub;
    private List<TextString> textStringsStub;

    private List<String> oldText; //"String 1\n String 2\n String 3\n"

    private List<String> newText; //"String 1\n String 1.1\n String 2\n String 3.1\n"

    /* This is the explanation of the expected behavior of created commit
    * Old text:
    *  String 1
    *  String 2
    *  String 3
    *
    *  New text:
    *  String 1
    *  String 1.1
    *  String 2
    *  String 3.1
    *
    *  Expected 'commit' of this texts:
    *  2:+ String 1.1
    *  3:- String 3
    *  4:+ String 3.1
    */

    @Before
    public void setUp() throws Exception {
        // Init oldText
        oldText = new ArrayList<>();
        oldText.add("String 1");
        oldText.add("String 2");
        oldText.add("String 3");

        // Init newText
        newText = new ArrayList<>();
        newText.add("String 1");
        newText.add("String 1.1");
        newText.add("String 2");
        newText.add("String 3.1");

        // Init textStringStub
        textStringsStub = new ArrayList<>();
        textStringsStub.add(new TextString(2, "+", "String 1.1"));
        textStringsStub.add(new TextString(3, "-", "String 3"));
        textStringsStub.add(new TextString(4, "+", "String 3.1"));

        // Init commitStub
        commitStub = new Commit() {
            @Override
            public List<TextString> getTextStrings() {
                return textStringsStub;
            }
        };
    }

    @Test
    public void commitCreatesCorrectly() throws Exception {
        Commit commit = new Commit(oldText, newText);

        assertTrue(commit.equals(commitStub));
    }

    @Test
    public void canCompareEqualCommits() throws Exception {
        Commit commit1 = new Commit() {
            @Override
            public List<TextString> getTextStrings() {
                List<TextString> textStrings = new ArrayList<>();
                textStrings.add(new TextString(1, "DELETED", "This is old text"));
                textStrings.add(new TextString(2, "NEW", "This is new text"));
                return textStrings;
            }
        };
        Commit commit2 = new Commit() {
            @Override
            public List<TextString> getTextStrings() {
                List<TextString> textStrings = new ArrayList<>();
                textStrings.add(new TextString(1, "DELETED", "This is old text"));
                textStrings.add(new TextString(2, "NEW", "This is new text"));
                return textStrings;
            }
        };

        assertTrue(commit1.equals(commit2));
        assertTrue(commit2.equals(commit1));
        // Коммит сам с собой равен
        assertTrue(commit1.equals(commit1));
    }

    @Test
    public void canCompareNotEqualCommits() throws Exception {
        Commit commit1 = new Commit() {
            @Override
            public List<TextString> getTextStrings() {
                List<TextString> textStrings = new ArrayList<>();
                textStrings.add(new TextString(1, "DELETED", "This is old text1"));
                textStrings.add(new TextString(2, "NEW", "This is new text1"));
                return textStrings;
            }
        };
        Commit commit2 = new Commit() {
            @Override
            public List<TextString> getTextStrings() {
                List<TextString> textStrings = new ArrayList<>();
                textStrings.add(new TextString(1, "DELETED", "This is old text2"));
                textStrings.add(new TextString(2, "NEW", "This is new text2"));
                textStrings.add(new TextString(3, "NEW", "This is also new text2"));
                return textStrings;
            }
        };
        Commit commit3 = new Commit() {
            @Override
            public List<TextString> getTextStrings() {
                List<TextString> textStrings = new ArrayList<>();
                textStrings.add(new TextString(1, "DELETED", "This is old text3"));
                textStrings.add(new TextString(2, "NEW", "This is new text3"));
                return textStrings;
            }
        };
        Commit commit4 = new Commit() {
            @Override
            public List<TextString> getTextStrings() {
                return null;
            }
        };

        // Сравнение с разными по размеру ts
        assertFalse(commit1.equals(commit2));
        assertFalse(commit2.equals(commit1));

        // Сравнение с пустым объектом
        assertFalse(commit1.equals(null));
        // Сравнение с одинковым по размеру ts но разные по значению
        assertFalse(commit1.equals(commit3));
        // Сравнение с пустым списком
        assertFalse(commit1.equals(commit4));

    }
}