package com.mytaxi.domainvalue;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Manufacturer
{
    @Column(name = "manufacturer")
    private String name;

}
