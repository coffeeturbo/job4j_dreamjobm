package dream.store;

import dream.model.Candidate;
import dream.model.Post;

import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class PsqlMain {

    static Logger LOGGER;

    static {
        try (InputStream ins = PsqlMain.class.getClassLoader().getResourceAsStream("logger.properties")) {
            LogManager.getLogManager().readConfiguration(ins);
            LOGGER = Logger.getLogger(PsqlMain.class.getName());
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Store store = PsqlStore.instOf();
        try {
            store.save(new Post(0, "Java Job"));
            for (Post post : store.findAllPosts()) {
                System.out.println(post.getId() + " " + post.getName());
            }

            Post findByidPost = store.findPostById(1);
            System.out.printf("post find by id:  %d %s\n", findByidPost.getId(), findByidPost.getName());

            findByidPost.setName("Updated Post");
            store.save(findByidPost);
            System.out.printf("post update id:  %d %s\n", findByidPost.getId(), findByidPost.getName());


            store.save(new Candidate(0, "Java Candidate"));
            for (Candidate post : store.findAllCandidates()) {
                System.out.println(post.getId() + " " + post.getName());
            }

            Candidate findByidCandidate = store.findCandidateById(1);
            System.out.printf("candidate find by id:  %d %s\n", findByidCandidate.getId(), findByidCandidate.getName());

            findByidCandidate.setName("Updated Candidate");
            store.save(findByidCandidate);
            System.out.printf("candidate update id:  %d %s\n", findByidCandidate.getId(), findByidCandidate.getName());
        } catch (Exception e) {
            LOGGER.warning(e.getMessage());
        }
    }
}
