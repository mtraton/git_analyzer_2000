package com.examle.gitAnalyzer.web;

import com.examle.gitAnalyzer.service.CommitAnalyzer;
import com.examle.gitAnalyzer.web.dto.CloneRepoDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/commit")
public class CommitController {


/*
@GetMapping("/employees/{id}")
  Employee one(@PathVariable Long id) {

    return repository.findById(id)
      .orElseThrow(() -> new EmployeeNotFoundException(id));
  }
 */
    @GetMapping("/commit/{repoName}")
    public ResponseEntity<?> getCommits(@PathVariable String repoName) {
        CommitAnalyzer commitAnalyzer = new CommitAnalyzer();
        try {
            commitAnalyzer.traverse(commitAnalyzer.getRepository());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
