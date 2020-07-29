package com.examle.gitAnalyzer.service;

import com.examle.gitAnalyzer.model.Commit;
import com.examle.gitAnalyzer.model.UserCommitData;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatCollector {

    public List<UserCommitData> collectTotalNumberOfCommits(List<Commit> commits) {
        Map<String, Integer> commitHistogram = new HashMap<>();
        commits.forEach(commit -> commitHistogram.merge(commit.getAuthor(), 1, Integer::sum));

        List<UserCommitData> data = commitHistogram.entrySet()
                .stream()
                .map((entity) -> new UserCommitData(entity.getKey(), entity.getValue()))
                .sorted(Comparator.comparingInt(UserCommitData::getNumberOfCommits).reversed())
                .collect(Collectors.toList());

        return data;
    }

}
