package dream.store;

import dream.model.Candidate;
import dream.model.Post;
import dream.model.User;

import java.util.Collection;

public interface Store {
    Collection<Post> findAllPosts();
    Collection<Candidate> findAllCandidates();
    Collection<User> findAllUsers();

    void save(Post post);
    void save(Candidate post);
    void save(User user);

    Post findPostById(int id);
    Candidate findCandidateById(int id);
    User findUserById(int id);

    int registerPhotoID(int canId);

    void deleteCandidateByID(int canID);
}
