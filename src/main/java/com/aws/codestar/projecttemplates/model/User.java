package com.aws.codestar.projecttemplates.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	@Column(name = "username", length = 36, nullable = false)
    private String username;
	
	@Column(name = "firstname")
    private String firstname;
    
	@Column(name = "lastname")
	private String lastname;
	
	
	@Column(name = "password", length = 128, nullable = false)
    private String password;
    
	@Column(name = "active") 
    private int active;

    @Transient
    private String passwordConfirm;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    
    public String getFirstname() {
    	return firstname;
    }
    
    public String getLastname() {
    	return lastname;
    }
    
    public int getActive() {
    	return active;
    }
    
    
    public void setActive(int active) {
    	this.active = active;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
    
    public void setFirstname(String firstname) {
    	this.firstname = firstname;
    }
    
    public void setLastname(String lastname) {
    	this.lastname = lastname;
    }
    
    
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    
}