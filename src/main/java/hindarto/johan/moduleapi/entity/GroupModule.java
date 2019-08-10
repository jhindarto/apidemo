package hindarto.johan.moduleapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class GroupModule {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @JsonIgnore
    private long id;

    @JsonIgnore
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @OrderBy("moduleOrder asc")
    private List<Module> modules;

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

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public void addModule(Module module) {
        if(this.modules == null || this.modules.isEmpty()) {
            this.modules = new ArrayList<Module>();
        }
        this.modules.add(module);
    }

    @Override
    public String toString() {
        return "GroupModule{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", modules=" + modules +
                '}';
    }
}
