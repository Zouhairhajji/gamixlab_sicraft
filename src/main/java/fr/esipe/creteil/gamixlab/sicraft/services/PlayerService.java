/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esipe.creteil.gamixlab.sicraft.services;

import fr.esipe.creteil.gamixlab.sicraft.entities.PlayerEntity;
import fr.esipe.creteil.gamixlab.sicraft.repositories.MaterialRepository;
import fr.esipe.creteil.gamixlab.sicraft.repositories.PlayerRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author zouhairhajji
 */
@Service
public class PlayerService {
    
    @Autowired
    private PlayerRepository playerRepository;
    
    private MaterialRepository materialRepository;
    
    @Transactional
    public Optional<PlayerEntity> findPlayerByPlayername(String playerName) {
        return this.playerRepository.findByPlayerName(playerName);
    }
    
    @Transactional
    public PlayerEntity saveOrUpdatePlayer(PlayerEntity playerEntity) {
        return this.playerRepository.save(playerEntity);
    }
    
        
}
