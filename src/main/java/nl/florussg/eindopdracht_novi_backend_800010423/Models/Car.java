package nl.florussg.eindopdracht_novi_backend_800010423.Models;

import jdk.incubator.vector.VectorOperators;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.stream.Stream;

@Entity
public class Car {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Long id;

    private String brand;

    private String type;

    private String licenseplatenumber;

    private int kms;

    private int constructionyear;

    // 11-11 nog te bedenken hoe ik de uploadfunctie ga inrichten //
    //private VectorOperators.Binary carpapers;




}
