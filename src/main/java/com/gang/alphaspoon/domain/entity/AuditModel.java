package com.gang.alphaspoon.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass //define como super class
@EntityListeners(AuditingEntityListener.class)  // estos atributos usan los entity listeners
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)  //Al ingresar data(POST) no se envia estros atributos
//En caso que  se use GET se puede mostrar la informacion de estos atributos
public abstract class AuditModel {

    @Temporal(TemporalType.TIMESTAMP) // Formato Fecha-hora
    @Column(name = "created_ad", nullable = false, updatable = false) // Give a diff names to the columns in the DB
    @CreatedDate // guarda fecha y hora cuando se grabe/cree un registro
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_ad", nullable = false)
    @LastModifiedDate //guarda fecha y hora de la ultima modificacion de un registro
    private Date updatedAt;

    public Date getCreatedAt() {
        return createdAt;
    }

    public AuditModel setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public AuditModel setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}
