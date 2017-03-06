package com.nopointer.server.entity;

import java.util.*;

public class TestClass
{
    // TODO: delete this class
    public static void main(String[] args)
    {
        List<String> oldText = new ArrayList<>();
        oldText.add("String 1");
        oldText.add("String 2");
        oldText.add("String 3");
        oldText.add("String 4");
        oldText.add("String 1");

        List<String> newText = new ArrayList<>();
        newText.add("String 1");
        newText.add("String 1.1");
        newText.add("String 2");
        newText.add("String 3.1");
        newText.add("String 3.1");
        newText.add("String 3");
        newText.add("String 1.1");

        CommitUtils.buildCommit(oldText, newText);
    }
}
