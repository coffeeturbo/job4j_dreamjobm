package dream.store;

import dream.model.Candidate;
import dream.model.Post;

import java.util.Collection;

public interface Store {
    Collection<Post> findAllPosts();

    Collection<Candidate> findAllCandidates();

    void save(Post post);
    void save(Candidate post);

    Post findPostById(int id);
    Candidate findCandidateById(int id);

    int registerPhotoID(int canId);

    void deleteCandidateByID(int canID);
}
