package own_logics;
import java.io.IOException;
import java.util.List;

import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

public class GitHubChangesTrackerr {

    public static void main(String[] args) throws IOException {
        // Replace with your GitHub access token
        String accessToken = "Token_Comes_Here";

        // Initialize a GitHub client
        GitHub github = new GitHubBuilder().withOAuthToken(accessToken).build();

      //  https://github.com/Hemnathj7/FirstRepoIntelliJ.git
        	GHRepository repository = github.getRepository("Hemnathj7/Shashank");

        // Get the latest commit
        	   GHCommit latestCommit = repository.listCommits().iterator().next();

               // Get the parent commit (the commit before the latest one)
               GHCommit parentCommit = latestCommit.getParents().get(0);

               // Get the list of files changed between the parent commit and the latest commit
               List<GHCommit.File> files = latestCommit.getFiles();

               // Output the paths of added files
               for (GHCommit.File file : files) {
                   if (file.getFileName().endsWith(".java") && !isFileModifiedInParentCommit(file, parentCommit)) {
                       System.out.println("New Java file added: " + file.getFileName());
                   }
               }
           }

           private static boolean isFileModifiedInParentCommit(GHCommit.File file, GHCommit parentCommit) throws IOException {
               // Check if the file exists in the parent commit's list of files
               for (GHCommit.File parentFile : parentCommit.getFiles()) {
                   if (parentFile.getFileName().equals(file.getFileName())) {
                       // File exists in the parent commit, so it was not added in the latest commit
                       return true;
                   }
               }
               // File does not exist in the parent commit, so it was added in the latest commit
               return false;
           }
       }