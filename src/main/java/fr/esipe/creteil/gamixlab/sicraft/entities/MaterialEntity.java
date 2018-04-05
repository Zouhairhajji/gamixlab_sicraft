/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esipe.creteil.gamixlab.sicraft.entities;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author zouhairhajji
 */

@Entity
@Table(name = "materials")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaterialEntity {
    
    @Id
    @Column(name = "id_material")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idMaterial;
    
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long quantity;
    
    @Column(name = "material_name")
    private String materialName;
    
    @Column(name = "date_creation")
    private Timestamp dateCreation;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private PlayerEntity player;

    @Override
    public String toString() {
        return "MaterialEntity{" + "idMaterial=" + idMaterial + ", quantity=" + quantity + ", materialName=" + materialName + ", dateCreation=" + dateCreation + ", players=" + player + '}';
    }
    
    
    
}
