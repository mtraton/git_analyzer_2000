import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.File;

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
