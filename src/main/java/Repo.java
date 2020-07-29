import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneOffset;

public class Repo {

    void clone(String repoURl, String repoPath) {
        try {
            Git.cloneRepository()
                    .setURI(repoURl)
                    .setDirectory(new File(repoPath))
                    .call();
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
    }


}
