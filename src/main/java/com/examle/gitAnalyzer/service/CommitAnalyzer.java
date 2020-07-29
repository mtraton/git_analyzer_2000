package com.examle.gitAnalyzer.service;

import com.examle.gitAnalyzer.exception.RefsDoesNotExistsException;
import com.examle.gitAnalyzer.model.Commit;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommitAnalyzer {

    @Value("${repo.path}")
    private String path;

    Repository getRepository(String repoName) throws IOException {
        FileRepositoryBuilder builder = new FileRepositoryBuilder();
        return builder
                .findGitDir(new File(String.format("%s/%s", path, repoName)))
                .build();
    }

    public List<Commit> getCommitData(String repoName, String ref) throws IOException {
        String treeName = "refs/heads/" + ref; // tag or branch
        Repository repository = getRepository(repoName);
        Git git = new Git(repository);
        List<Commit> commitData = new ArrayList<>();
        try {
            for (RevCommit commit : git.log().add(repository.resolve(treeName)).call()) {
                String address = commit.getAuthorIdent().getEmailAddress();
                commitData.add(new Commit(address, getTimeStampFromCommit(commit), commit.getShortMessage()));
            }
        } catch (NullPointerException e) {
            throw new RefsDoesNotExistsException("Refs does not exists: " + ref);
        }
        catch (IOException | GitAPIException e) {
            e.printStackTrace();
        }
        return commitData;
    }

    public List<Commit> getCommitDataForUser(String repoName, String branch, String user) throws IOException {
        return getCommitData(repoName, branch).stream()
                .filter(commit -> commit.getAuthor().equals(user))
                .collect(Collectors.toList());
    }

    private LocalDateTime getTimeStampFromCommit(RevCommit commit) {
        int commitTime = commit.getCommitTime();
        return Instant                         // Represent a moment in UTC, an offset of zero hours-minutes-seconds.
                .ofEpochSecond(commitTime)
                .atOffset(                        // Convert from `Instant` (always in UTC, an offset of zero) to `OffsetDateTime` which can have any offset.
                        ZoneOffset.UTC                // A constant representing an offset of zero hours-minutes-seconds, that is, UTC itself.
                )            // Parse a count of milliseconds since 1970-01-01T00:00Z. Returns a `Instant` object.
                .toLocalDateTime();
    }
}
