package com.backend.ecommerce.User.entities.entity;

import jakarta.persistence.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

@Entity
/*@Table(name = "roles")*/
@Document(collection="roles")
public class Roles implements GrantedAuthority {

    @Id
    /*@GeneratedValue(strategy= GenerationType.AUTO)*/
    /*@Column(name="role_id")*/
    private Integer roleId;

    private String authority;

    public Roles(){
        super();
    }

    public Roles(String authority){
        this.authority = authority;
    }

    public Roles(Integer roleId, String authority){
        this.roleId = roleId;
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        // TODO Auto-generated method stub
        return this.authority;
    }

    public void setAuthority(String authority){
        this.authority = authority;
    }

    public Integer getRoleId(){
        return this.roleId;
    }

    public void setRoleId(Integer roleId){
        this.roleId = roleId;
    }

}
