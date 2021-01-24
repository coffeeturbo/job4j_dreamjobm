package dream.store;

import dream.model.Candidate;
import dream.model.Post;

import java.util.Collection;

public interface Store {
    Collection<Post> findAllPosts();

    Collection<Candidate> findAllCandidates();

    void save(Post post) throws Exception;
    void save(Candidate post) throws Exception;

    Post findPostById(int id);
    Candidate findCandidateById(int id);
}
