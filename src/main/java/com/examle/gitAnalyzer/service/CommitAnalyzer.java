package com.examle.gitAnalyzer.service;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommitAnalyzer {

    @Value("${repo.path}")
    private String path;

    private Repository getRepository() throws IOException {
        FileRepositoryBuilder builder = new FileRepositoryBuilder();
        return builder
                .setGitDir(new File(path))
                .build();
    }

    public List<String> getCommitData() throws IOException {
        String treeName = "refs/heads/master"; // tag or branch
        Repository repository = getRepository();
        Git git = new Git(repository);
        List<String> commitData = new ArrayList<>();
        try {
            for (RevCommit commit : git.log().add(repository.resolve(treeName)).call()) {
                String address = commit.getAuthorIdent().getEmailAddress();
                String time = getTimeStampFromCommit(commit);
                commitData.add(String.format("%s %s%n", address, time));
            }
        } catch (IOException | GitAPIException e) {
            e.printStackTrace();
        }
        return commitData;
    }

    private String getTimeStampFromCommit(RevCommit commit) {
        int commitTime = commit.getCommitTime();
        return Instant                         // Represent a moment in UTC, an offset of zero hours-minutes-seconds.
                .ofEpochMilli(commitTime)
                .atOffset(                        // Convert from `Instant` (always in UTC, an offset of zero) to `OffsetDateTime` which can have any offset.
                        ZoneOffset.UTC                // A constant representing an offset of zero hours-minutes-seconds, that is, UTC itself.
                )            // Parse a count of milliseconds since 1970-01-01T00:00Z. Returns a `Instant` object.
                .toString();
    }
}
