package com.microsoft.whtch.domain.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity<I extends Serializable> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private I id;
}
