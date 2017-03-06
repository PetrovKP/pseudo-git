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

    @Override
    public boolean equals(Object o)
    {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        Commit commit = (Commit)o;
        List<TextString> localTextStrings = getTextStrings();

        if (localTextStrings == null || commit.getTextStrings() == null) {return false;}

        if (localTextStrings.size() != commit.getTextStrings().size()) {return false;}
        for (int i = 0; i < localTextStrings.size(); i++){
            if (!localTextStrings.get(i).equals(commit.getTextStrings().get(i))){
                return false;
            }
        }
        return true;
    }
}
