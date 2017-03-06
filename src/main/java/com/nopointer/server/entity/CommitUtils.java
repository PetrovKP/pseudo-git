package com.nopointer.server.entity;

import difflib.Chunk;
import difflib.Delta;
import difflib.DiffUtils;
import difflib.Patch;

import java.util.ArrayList;
import java.util.List;

class CommitUtils {
    private CommitUtils() {
    }

    public static List<TextString> buildCommit(List<String> oldText, List<String> newText) {
        List<TextString> result = new ArrayList<>();

        Patch<String> diff = DiffUtils.diff(oldText, newText);

        for (Delta delta : diff.getDeltas()) {
            Chunk original = delta.getOriginal();
            Chunk revised = delta.getRevised();
            int position;

            switch (delta.getType()) {
                case INSERT:
                    position = revised.getPosition();
                    for (int i = 0; i < revised.getLines().size(); i++) {
                        result.add(new TextString(position + i + 1, "+",
                                (String) revised.getLines().get(i)));
                    }
                    break;

                case DELETE:
                    position = original.getPosition();
                    for (int i = 0; i < original.getLines().size(); i++) {
                        result.add(new TextString(position + i + 1, "-",
                                (String) original.getLines().get(i)));
                    }
                    break;

                case CHANGE:
                    position = original.getPosition();
                    for (int i = 0; i < original.getLines().size(); i++) {
                        result.add(new TextString(position + i + 1, "-",
                                (String) original.getLines().get(i)));
                    }
                    position = revised.getPosition();
                    for (int i = 0; i < revised.getLines().size(); i++) {
                        result.add(new TextString(position + i + 1, "+",
                                (String) revised.getLines().get(i)));
                    }
                    break;
            }
        }
        return result;
    }
}
