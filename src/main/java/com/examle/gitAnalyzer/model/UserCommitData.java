package com.examle.gitAnalyzer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserCommitData {

    private final String email;
    private final int numberOfCommits;

}
