package dream.store;

import dream.model.Candidate;
import dream.model.City;
import dream.model.Post;
import dream.model.User;

import java.util.Collection;

public interface Store {
    Collection<Post> findAllPosts();
    Collection<Candidate> findAllCandidates();
    Collection<City> findAllCities();
    Collection<User> findAllUsers();

    void save(Post post);
    void save(Candidate post);
    void save(User user);

    Post findPostById(int id);
    Candidate findCandidateById(int id);
    User findUserById(int id);
    User findUserByEmail(String email);

    int registerPhotoID(int canId);

    void deleteCandidateByID(int canID);
}
