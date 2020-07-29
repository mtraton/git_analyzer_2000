package com.examle.gitAnalyzer.service;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RepoRefresher {
    @Autowired
    CommitAnalyzer commitAnalyzer;

    public void refresh(String repoName) throws IOException {
        Repository repository = commitAnalyzer.getRepository(repoName);
        Git git = new Git(repository);
        git.pull();
    }

}
