package com.nopointer.server.entity;

import org.junit.*;

import java.util.*;

import static org.junit.Assert.*;

public class CommitTest
{
    private Commit commitStub;
    private List<TextString> textStringsStub;

    private String oldText = "String 1\n String 2\n String 3\n";
    private String newText = "String 1\n String 1.1\n String 2\n String 3.1\n";

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
    *  OLD: String 1
    *  NEW: String 1.1
    *  OLD: String 2
    *  DELETED: String 3
    *  NEW: String 3.1
    */

    @Before
    public void setUp() throws Exception{
        // Init textStringStub
        textStringsStub = new ArrayList<>();
        textStringsStub.add(new TextString("OLD", "String 1"));
        textStringsStub.add(new TextString("NEW", "String 1.1"));
        textStringsStub.add(new TextString("OLD", "String 2"));
        textStringsStub.add(new TextString("DELETED", "String 3"));
        textStringsStub.add(new TextString("NEW", "String 3.1"));

        // Init commitStub
        commitStub = new Commit(){
            @Override
            public List<TextString> getTextStrings()
            {
                return textStringsStub;
            }
        };
    }


    @Test
    public void commitCreatesCorrectly() throws Exception
    {
        Commit commit = new Commit(oldText, newText);

        assertTrue(commit.equals(commitStub));
    }

    @Test
    public void canCompareEqualCommits() throws Exception
    {
        Commit commit1 = new Commit("This is old text", "This is new text");
        Commit commit2 = new Commit("This is old text", "This is new text");

        assertTrue(commit1.equals(commit2));
        assertTrue(commit2.equals(commit1));
    }

    @Test
    public void canCompareNotEqualCommits() throws Exception
    {
        Commit commit1 = new Commit("This is old text1", "This is new text1");
        Commit commit2 = new Commit("This is old text2", "This is new text2\n And also this is new text2");

        assertFalse(commit1.equals(commit2));
        assertFalse(commit2.equals(commit1));
    }
}