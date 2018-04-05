/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esipe.creteil.gamixlab.sicraft.services;

import fr.esipe.creteil.gamixlab.sicraft.entities.MaterialEntity;
import fr.esipe.creteil.gamixlab.sicraft.repositories.MaterialRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author zouhairhajji
 */
@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;
    
    @Transactional
    public Optional<MaterialEntity> findMaterialByName(String materialName) {
        return this.materialRepository.findByMaterialName(materialName);
    }

    @Transactional
    public MaterialEntity saveMaterial(MaterialEntity materialEntity) {
        return this.materialRepository.save(materialEntity);
    }

    @Transactional
    public void deleteAll() {
        this.materialRepository.deleteAllInBatch();
    }
    
}
