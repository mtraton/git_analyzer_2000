package com.examle.gitAnalyzer.web;

import com.examle.gitAnalyzer.model.Commit;
import com.examle.gitAnalyzer.service.CommitAnalyzer;
import com.examle.gitAnalyzer.service.RepoCloner;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/commit")
@AllArgsConstructor
public class CommitController {

    private CommitAnalyzer commitAnalyzer;

    @GetMapping("/{repoName}")
    public ResponseEntity<List<Commit>> getCommits(@PathVariable String repoName) {
        List<Commit> commitData = new ArrayList<>();
        try {
            commitData = commitAnalyzer.getCommitData(repoName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(commitData, HttpStatus.CREATED);
    }

    @GetMapping("/{repoName}/{user}")
    public ResponseEntity<List<Commit>> getCommitsForUser(@PathVariable String repoName, @PathVariable String user) {
        List<Commit> commitData = new ArrayList<>();
        try {
            commitData = commitAnalyzer.getCommitDataForUser(repoName, user);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(commitData, HttpStatus.CREATED);
    }
}
