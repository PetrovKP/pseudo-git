package com.nopointer.server.database;

import com.nopointer.server.entity.Commit;

import java.sql.Connection;
import java.util.List;

public interface DatabaseAPI {
    void setConnection(Connection connection);

    // Users
    boolean registerUser(String login, String password);
    boolean login(String login, String password);
    int getIdUser(String login);

    @Deprecated
    boolean deleteUser(String login);

    // Files
    boolean createFile(String login, String title, List<String> text); // Я на уровне клиента запрещу создать пустой по содержанию файл
    Integer getCommitsCount(int idUser, int idFile);
    String getTitle(String login, int idFile);
    List<Integer> getAllFilesId(int idUser);
    List<String> getActualText(String login, int idFile);
    List<Integer> getAllCommitsId(String login, int idFile);

    //Access
    boolean isAccessUserToFile(int idUser, int idFile);
    List<String> getAllUsersByFile(String login, int idFile);
    boolean addUserToFile(int idUser, int newIdUser, int idFile);

    // Commits
    boolean addCommit (int idUser, int idFile, List<String> text);
    String getCommitDateById(String login, int idFile, int idCommit);
    Commit getCommitById(String login, int idFile, int idCommit);
    // Удалить коммиты у которые Id выше
    boolean revertFileToCommit (String login, int idFile, int idCommit);
}
