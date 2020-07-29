package com.examle.gitAnalyzer.service;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@Slf4j
public class RepoCloner {

    @Value("${repo.path}")
    private String path;

    public void cloneRepository(String url) {
        String repoPath = path + url.substring(url.lastIndexOf("/"), url.lastIndexOf("."));
        log.info("Cloning repo from {} to the {}", url, repoPath);
        try {
            Git.cloneRepository()
                    .setURI(url)
                    .setDirectory(new File(repoPath))
                    .call();
        } catch (GitAPIException e) {
            log.error("Failed to clone repository {} - {}", url, e.getMessage());
        }
    }
}

