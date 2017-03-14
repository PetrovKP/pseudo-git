package com.nopointer.server.entity;

import java.io.Serializable;
import java.util.List;

public class Commit implements Serializable {
    private List<TextString> textStrings;

    public Commit() {
        textStrings = null;
    }

    public Commit(List<String> oldText, List<String> newText) {
        this.textStrings = CommitUtils.buildCommit(oldText, newText);
    }

    public List<TextString> getTextStrings() {
        return textStrings;
    }

    public boolean equals(Commit commit) {
        if (this == commit) {
            return true;
        }

        if (commit == null)
            return false;

        List<TextString> localTextStrings = getTextStrings();

        if (localTextStrings == null || commit.getTextStrings() == null) {
            return false;
        }

        if (localTextStrings.size() != commit.getTextStrings().size()) {
            return false;
        }
        for (int i = 0; i < localTextStrings.size(); i++) {
            if (!localTextStrings.get(i).equals(commit.getTextStrings().get(i))) {
                return false;
            }
        }
        return true;
    }
}
