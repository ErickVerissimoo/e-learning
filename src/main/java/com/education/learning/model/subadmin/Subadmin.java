package com.education.learning.model.subadmin;

import com.education.learning.model.superclass.Usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@Table(name="subadmin")
public class Subadmin extends Usuario {


}
