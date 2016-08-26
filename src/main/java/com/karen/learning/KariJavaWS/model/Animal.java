package com.karen.learning.KariJavaWS.model;

/**
 * Created by Karen on 19/08/2016.
 */
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Animal")  
public class Animal {

    private String id;
    private String clase;
    private String diet;

    public Animal() {

    }

    public String getId() {
        return id;
    }

    public void setId(String raza) {
        this.id = raza;
    }


    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }


    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

}
