package com.example.productcatalogservice.TableInheritanceExamples.TablePerClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name = "tpc_ta")
public class Ta extends User{
    private Integer helpRequests;
}
