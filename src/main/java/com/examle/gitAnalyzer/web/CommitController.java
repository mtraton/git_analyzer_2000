package com.examle.gitAnalyzer.web;

import com.examle.gitAnalyzer.model.Commit;
import com.examle.gitAnalyzer.service.CommitAnalyzer;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/commit")
@AllArgsConstructor
public class CommitController {

    private CommitAnalyzer commitAnalyzer;

    @GetMapping("/{repoName}")
    public ResponseEntity<List<Commit>> getCommits(@PathVariable String repoName,
                                                   @RequestParam(name = "branch", defaultValue = "master") String branch) {
        List<Commit> commitData = new ArrayList<>();
        try {
            commitData = commitAnalyzer.getCommitData(repoName, branch);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(commitData, HttpStatus.CREATED);
    }

    @GetMapping("/{repoName}/{user}")
    public ResponseEntity<List<Commit>> getCommitsForUser(@PathVariable String repoName,
                                                          @PathVariable String user,
                                                          @RequestParam(name = "branch", defaultValue = "master") String branch) {
        List<Commit> commitData = new ArrayList<>();
        try {
            commitData = commitAnalyzer.getCommitDataForUser(repoName, branch, user);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(commitData, HttpStatus.CREATED);
    }
}
