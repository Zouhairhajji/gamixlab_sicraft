/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esipe.creteil.gamixlab.sicraft.repositories;

import fr.esipe.creteil.gamixlab.sicraft.entities.PlayerEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author zouhairhajji
 */
public interface PlayerRepository extends JpaRepository<PlayerEntity, Long>{
    
    public Optional<PlayerEntity> findByPlayerName(String playerName);
    
    
}
