package org.jabref.gui.exporter;

import java.io.IOException;
import java.nio.file.Path;

import org.jabref.logic.git.GitHandler;

import org.eclipse.jgit.api.errors.GitAPIException;

public class SaveGitDatabaseAction {
    final Path repositoryPath;
    final String automaticCommitMsg = "Automatic update via JabRef";

    public SaveGitDatabaseAction(Path repositoryPath) {
        this.repositoryPath = repositoryPath;
    }

    public boolean automaticUpdate() {
        try {
            GitHandler git = new GitHandler(repositoryPath);
            git.createCommitOnCurrentBranch(automaticCommitMsg, false);
            git.pushCommitsToRemoteRepository();
        } catch (
                GitAPIException |
                IOException e) {
            throw new RuntimeException(e);
        }

        return true;
    }
}
