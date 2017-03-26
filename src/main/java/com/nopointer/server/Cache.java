package com.nopointer.server;

import java.util.HashSet;
import java.util.Set;

public final class Cache {
    private static Cache instance = new Cache();
    private volatile Set<Integer> editedFiles;

    private Cache(){
        editedFiles = new HashSet<>();
    }

    public static synchronized Cache open(){
        return instance;
    }

    public boolean isFileEdited(int idFile){
        return editedFiles.contains(idFile);
    }

    public void startEditFile(int idFile){
        editedFiles.add(idFile);
    }

    public void finishEditFile(int idFile){
        editedFiles.remove(idFile);
    }
}
