package hindarto.johan.moduleapi.entity;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String name;

    @ManyToOne
    private GroupModule groupModule;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GroupModule getGroupModule() {
        return groupModule;
    }

    public void setGroupModule(GroupModule groupModule) {
        this.groupModule = groupModule;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", groupModule=" + groupModule +
                '}';
    }
}
