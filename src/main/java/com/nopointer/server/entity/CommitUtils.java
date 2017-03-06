package com.nopointer.server.entity;

import difflib.*;

import java.util.*;

public class CommitUtils
{
    private CommitUtils(){}

    public static List<TextString> buildCommit(List<String> oldText, List<String> newText)
    {
        List<TextString> result = new ArrayList<>();
        List<String> res = new ArrayList<>();

        int shift = 0;

        for (int i = 0; i < oldText.size(); i++)
        {
            result.add(new TextString("OLD", oldText.get(i)));
        }

        Patch<String> diff = DiffUtils.diff(oldText, newText);

        for (Delta delta : diff.getDeltas()){
            switch (delta.getType()){
                case INSERT:
                    //int position = ;
                    break;
                case CHANGE:
                break;
                case DELETE:
                break;
            }
            System.out.println(delta.getType());
            System.out.println(delta.getOriginal());
            System.out.println(delta.getRevised());
        }
        return null;
    }
}
