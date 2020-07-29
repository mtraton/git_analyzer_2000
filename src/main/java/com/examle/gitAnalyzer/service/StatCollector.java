package com.examle.gitAnalyzer.service;

import com.examle.gitAnalyzer.model.Commit;
import com.examle.gitAnalyzer.model.UserCommitData;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatCollector {

    public List<UserCommitData> collectTotalNumberOfCommits(List<Commit> commits,
                                                            LocalDate startTime,
                                                            LocalDate endTime) {
        List<Commit> dateFilteredCommits = commits.stream()
                .filter(commit -> commit.getDateTime().toLocalDate().isAfter(startTime))
                .filter(commit -> commit.getDateTime().toLocalDate().isBefore(endTime))
                .collect(Collectors.toList());
        Map<String, Integer> commitHistogram = new HashMap<>();
        dateFilteredCommits.forEach(commit -> commitHistogram.merge(commit.getAuthor(), 1, Integer::sum));

        return commitHistogram.entrySet()
                .stream()
                .map((entity) -> new UserCommitData(entity.getKey(), entity.getValue()))
                .sorted(Comparator.comparingInt(UserCommitData::getNumberOfCommits).reversed())
                .collect(Collectors.toList());
    }

}
