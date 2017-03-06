package com.nopointer.server.entity;

import java.io.Serializable;
import java.util.*;

public class Commit implements Serializable{
    private List<TextString> textStrings;

    public Commit()
    {
        textStrings = null;
    }

    public Commit(String oldText, String newText){
        this.textStrings = CommitUtils.buildCommit(oldText, newText);
    }

    public List<TextString> getTextStrings()
    {
        return textStrings;
    }
}
