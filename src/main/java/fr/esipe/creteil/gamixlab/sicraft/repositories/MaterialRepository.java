/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esipe.creteil.gamixlab.sicraft.repositories;

import fr.esipe.creteil.gamixlab.sicraft.entities.MaterialEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author zouhairhajji
 */
public interface MaterialRepository extends JpaRepository<MaterialEntity, Long>{
    
    public Optional<MaterialEntity> findByMaterialName(String materialName);
    
    @Query("DELETE FROM MaterialEntity")
    public void deleteAll();
    
}
