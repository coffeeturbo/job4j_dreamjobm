package dream.store;

import dream.model.Candidate;
import dream.model.Post;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

public class PsqlStore implements Store {

    private final BasicDataSource pool = new BasicDataSource();

    private PsqlStore() {
        Properties cfg = new Properties();
        try (BufferedReader io = new BufferedReader(
                new FileReader("db.properties")
        )) {
            cfg.load(io);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        pool.setDriverClassName(cfg.getProperty("jdbc.driver"));
        pool.setUrl(cfg.getProperty("jdbc.url"));
        pool.setUsername(cfg.getProperty("jdbc.username"));
        pool.setPassword(cfg.getProperty("jdbc.password"));
        pool.setMinIdle(5);
        pool.setMaxIdle(10);
        pool.setMaxOpenPreparedStatements(100);
    }

    private static final class Lazy {
        private static final Store INST = new PsqlStore();
    }

    public static Store instOf() {
        return Lazy.INST;
    }

    @Override
    public Collection<Post> findAllPosts() {
        List<Post> posts = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("SELECT * FROM post")
        ) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    posts.add(new Post(it.getInt("id"), it.getString("name")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    public Collection<Candidate> findAllCandidates() {
        List<Candidate> candidates = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("SELECT * FROM candidate")
        ) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    candidates.add(new Candidate(it.getInt("id"), it.getString("name")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return candidates;
    }

    @Override
    public void save(Post post) {
        if (post.getId() == 0) {
            create(post);
        } else {
            update(post);
        }
    }

    @Override
    public void save(Candidate candidate) {
        if (candidate.getId() == 0) {
            create(candidate);
        } else {
            update(candidate);
        }
    }

    private Post create(Post post) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("INSERT INTO post(name) VALUES (?)", PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, post.getName());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    post.setId(id.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return post;
    }

    private Candidate create(Candidate candidate) {
        try (
                PreparedStatement ps = pool.getConnection().prepareStatement("INSERT INTO candidate(name) VALUES (?)", PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, candidate.getName());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    candidate.setId(id.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return candidate;
    }

    private void update(Post post) {
        try (PreparedStatement statement = pool.getConnection().prepareStatement("UPDATE post SET name = ? WHERE id = ?")) {
            statement.setString(1, post.getName());
            statement.setInt(2, post.getId());
            statement.executeUpdate();

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }

    private void update(Candidate candidate) {
        try (PreparedStatement statement = pool.getConnection().prepareStatement("UPDATE candidate SET name = ? WHERE id = ?")) {
            statement.setString(1, candidate.getName());
            statement.setInt(2, candidate.getId());
            statement.executeUpdate();

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Post findPostById(int id) {

        try (
                PreparedStatement ps = pool.getConnection().prepareStatement("SELECT id, name FROM post WHERE post.id=(?)")
        ) {
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            result.next();

            return new Post(result.getInt(1), result.getString(2));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Candidate findCandidateById(int id) {
        try (
                PreparedStatement ps = pool.getConnection().prepareStatement("SELECT id, name FROM candidate WHERE id=(?)")
        ) {
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            result.next();

            return new Candidate(result.getInt(1), result.getString(2));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
