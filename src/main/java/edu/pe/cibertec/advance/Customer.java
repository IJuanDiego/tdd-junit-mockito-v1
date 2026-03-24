package edu.pe.cibertec.advance;

public class Customer {

    private Long id;
    private String name;
    private String email;

    public Customer( Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public long getId(){ return  id;}
    public String getName(){ return name;}
    public String getEmail(){ return email;}
}
