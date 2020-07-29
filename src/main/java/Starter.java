import org.eclipse.jgit.lib.Repository;

import java.io.IOException;

public class Starter {
    public static void main(String[] args) {
        try {
            Repo repo = new Repo();
            Repository repository = repo.getRepository();
            String branch = repository.getBranch();
            System.out.println(branch);
            repo.traverse(repository);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
