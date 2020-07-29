package com.examle.gitAnalyzer.web;

import com.examle.gitAnalyzer.model.Commit;
import com.examle.gitAnalyzer.model.UserCommitData;
import com.examle.gitAnalyzer.service.CommitAnalyzer;
import com.examle.gitAnalyzer.service.StatCollector;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/stat")
@AllArgsConstructor
public class StatController {

    private final StatCollector collector;
    private final CommitAnalyzer analyzer;

    @GetMapping("/{repoName}/count")
    public ResponseEntity<?> getTotalNumberOfCommits(@PathVariable("repoName") String repoName,
                                                     @RequestParam(name = "branch", defaultValue = "master") String branch)
            throws IOException {
        List<Commit> commitData = analyzer.getCommitData(repoName, branch);
        List<UserCommitData> userCommitHistogram = collector.collectTotalNumberOfCommits(commitData);
        return new ResponseEntity<>(userCommitHistogram, HttpStatus.OK);
    }

}
