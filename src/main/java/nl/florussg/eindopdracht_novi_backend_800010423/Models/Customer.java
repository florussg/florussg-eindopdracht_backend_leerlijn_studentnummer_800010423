package nl.florussg.eindopdracht_novi_backend_800010423.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    private String firstname;

    private String lastname;

    // NOG BEDENKEN HOE IK DE DATUM GA INRICHTEN, bv. dmv SimpleDate//
    private String dateofbirth;

    private int bsnnumber;

    private String streetadress;

    private int housenumber;

    private String residence;

    private String postalcode;

    private int phonenumber;

    private String emailadress;

}

