package br.com.codenation.model;

public class BaseModel {
    private Long id;

    public BaseModel(Long id) {
        this.setId(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
