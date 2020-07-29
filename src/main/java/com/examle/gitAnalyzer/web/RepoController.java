package com.examle.gitAnalyzer.web;

import com.examle.gitAnalyzer.service.RepoCloner;
import com.examle.gitAnalyzer.service.RepoLister;
import com.examle.gitAnalyzer.service.RepoRefresher;
import com.examle.gitAnalyzer.web.dto.CloneRepoDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/repo")
@AllArgsConstructor
public class RepoController {
    private RepoCloner cloner;
    private RepoLister lister;
    private RepoRefresher refresher;

    @PostMapping
    public ResponseEntity<?> cloneRepo(@RequestBody CloneRepoDto request) {
        cloner.cloneRepository(request.getRepoUrl());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<String>> listAll() {
        List<String> repositories = lister.listRepositories();
        return new ResponseEntity<>(repositories, HttpStatus.OK);
    }

    @GetMapping("/{repoName}")
    public ResponseEntity<?> pull(String repoName) throws IOException {
        refresher.refresh(repoName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
