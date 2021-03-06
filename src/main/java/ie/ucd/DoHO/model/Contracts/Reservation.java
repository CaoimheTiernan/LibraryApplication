package ie.ucd.DoHO.model.Contracts;

import ie.ucd.DoHO.model.Artifact;
import ie.ucd.DoHO.model.User;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Reservation implements Serializable, Comparable<Reservation> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Artifact artifact;
    @CreationTimestamp
    private Date created;

    public Reservation(User user, Artifact artifact) {
        setUser(user);
        setArtifact(artifact);
    }

    public Reservation() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Artifact getArtifact() {
        return artifact;
    }

    public void setArtifact(Artifact artifact) {
        this.artifact = artifact;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public int compareTo(Reservation other) {
        return this.created.compareTo(other.created);
    }
}
