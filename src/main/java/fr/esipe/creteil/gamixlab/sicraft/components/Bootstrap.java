/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esipe.creteil.gamixlab.sicraft.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 *
 * @author zouhairhajji
 */
@Component
public class Bootstrap implements ApplicationListener<ApplicationReadyEvent>{

    @Value("${minetest.worlds_directory}")
    private String worldDirectory;
    
    @Value("${minetest.world_name}")
    private String worldName;
    
    private final static Logger logger = LoggerFactory.getLogger(Bootstrap.class);
    
    @Override
    public void onApplicationEvent(ApplicationReadyEvent e) {
        logger.info("the pragram was lanched to save user in the database");
        logger.info("the world directory is in {} {}", worldDirectory, worldName);
    }
    
}
