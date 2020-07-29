package com.examle.gitAnalyzer.web;

import com.examle.gitAnalyzer.service.CommitAnalyzer;
import com.examle.gitAnalyzer.service.RepoCloner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/commit")
public class CommitController {
    private CommitAnalyzer commitAnalyzer;

    @GetMapping("/{repoName}")
    public ResponseEntity<List<String>> getCommits(@PathVariable String repoName) {
        List<String> commitData = new ArrayList<>();
        try {
            commitData = commitAnalyzer.getCommitData(repoName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(commitData, HttpStatus.CREATED);
    }
}
