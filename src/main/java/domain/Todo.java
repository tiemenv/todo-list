package domain;

import java.util.Objects;

public class Todo {

    //TODO: id should autoincrement and be PK in the database
    private int id;
    private String description;
    private int importance;
    private boolean done;

    public Todo(String description, int importance, boolean done) {
        this.description = description;
        this.importance = importance;
        this.done = done;
    }

    public Todo(String description, int importance) {
        this.description = description;
        this.importance = importance;
    }

    public Todo(String description){
        this.description = description;
        this.importance = 0;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return id == todo.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return description;
    }
}
