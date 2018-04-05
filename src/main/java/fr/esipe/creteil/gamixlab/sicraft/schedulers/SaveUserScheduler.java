package fr.esipe.creteil.gamixlab.sicraft.schedulers;

import fr.esipe.creteil.gamixlab.sicraft.entities.MaterialEntity;
import fr.esipe.creteil.gamixlab.sicraft.entities.PlayerEntity;
import fr.esipe.creteil.gamixlab.sicraft.services.MaterialService;
import fr.esipe.creteil.gamixlab.sicraft.services.PlayerService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author zouhairhajji
 */
@Component
public class SaveUserScheduler {

    private final static Logger logger = LoggerFactory.getLogger(SaveUserScheduler.class);

    @Value("${minetest.worlds_directory}")
    private String worldDirectory;

    @Value("${minetest.world_name}")
    private String worldName;

    @Autowired
    private MaterialService materialService;

    @Autowired
    private PlayerService playerService;

    @Scheduled(fixedDelay = 1000 * 5)
    public void saveUser() throws IOException {
        logger.info("SAVE ALL PLAYERS IN THE DATABASE");
        String folderName = String.format("%s/%s/players", worldDirectory, worldName);
        Files.walk(Paths.get(folderName))
                .filter(Files::isRegularFile)
                .forEach(f -> updateUser(folderName, f.getFileName().toString()));
        logger.info("END OF SAVE ALL PLAYERS IN THE DATABASE");
    }

    public void updateUser(String folderName, String username) {
        try {
            
            // delete all records
            logger.info("  1/ delete all material records");
            this.materialService.deleteAll();
            
            String filename = String.format("%s/%s", folderName, username);
            List<String> lines = Files.lines(Paths.get(filename))
                    .collect(Collectors.toList());

            // save user first
            Optional<PlayerEntity> optPlayer = this.playerService.findPlayerByPlayername(username);
            PlayerEntity playerEntity = null;
            if (!optPlayer.isPresent()) {
                playerEntity = PlayerEntity.builder()
                        .xPosition(getPosition(lines, 0))
                        .yPosition(getPosition(lines, 1))
                        .zPosition(getPosition(lines, 2))
                        .playerName(username)
                        .breath(getBreath(lines))
                        .hp(getHp(lines))
                        .dateCreation(new Timestamp(System.currentTimeMillis()))
                        .materials(new ArrayList<>())
                        .build();

                logger.info("the player is : " + playerEntity);
            } else {
                playerEntity = optPlayer.get();
                playerEntity.setBreath(getBreath(lines));
                playerEntity.setHp(getHp(lines));
                playerEntity.setXPosition(getPosition(lines, 0));
                playerEntity.setYPosition(getPosition(lines, 1));
                playerEntity.setZPosition(getPosition(lines, 2));
            }
            
            
            getMaterials(lines, playerEntity.getMaterials(), playerEntity);
            
            // save all materials for the user
            logger.info("  save user");
            playerEntity = this.playerService.saveOrUpdatePlayer(playerEntity);
            /* /save all materials first 
            for (String line : lines) {
                String[] split = line.replace("Item ", "").split(" |:");
                Optional<MaterialEntity> optMaterial = this.materialService.findMaterialByName(split[1]);
                MaterialEntity materialEntity = optMaterial.get();
                if (!optMaterial.isPresent()) {
                    materialEntity = MaterialEntity.builder()
                            .dateCreation(new Timestamp(System.currentTimeMillis()))
                            .idMaterial(null)
                            .materialName(split[1])
                            .players(new ArrayList<>())
                            .build();
                    materialEntity = this.materialService.saveMaterial(materialEntity);
                }
                logger.info("data is {} {} {}", split);
            }
             */
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    public List<MaterialEntity> getMaterials(List<String> lines, List<MaterialEntity> materialEntitys, PlayerEntity playerEntity) {
        lines = lines.stream()
                .filter(s -> s.startsWith("Item "))
                .collect(Collectors.toList());
        
        for (String line : lines) {
            String[] split = line.replace("Item ", "").split(" |:");
            
            MaterialEntity materialEntity = MaterialEntity.builder()
                    .dateCreation(new Timestamp(System.currentTimeMillis()))
                    .materialName(split[1])
                    .quantity(split.length > 2 ? Long.parseLong(split[2]) : 1)
                    .player(playerEntity)
                    .build();
            materialEntitys.add(materialEntity);
        }
        return materialEntitys;
    }

    public Double getPosition(List<String> lines, int index) {
        List<String> list = lines.stream()
                .filter(s -> s.startsWith("position = ("))
                .collect(Collectors.toList());
        String line = list.get(0).replace("position = (", "");
        line = line.replace(")", "");
        return Double.parseDouble(line.split(",")[index]);
    }

    public Integer getHp(List<String> lines) {
        List<String> list = lines.stream()
                .filter(s -> s.startsWith("hp = "))
                .collect(Collectors.toList());
        String line = list.get(0).replace("hp = ", "");
        return Integer.parseInt(line);
    }

    public Integer getBreath(List<String> lines) {
        List<String> list = lines.stream()
                .filter(s -> s.startsWith("breath = "))
                .collect(Collectors.toList());
        String line = list.get(0).replace("breath = ", "");
        return Integer.parseInt(line);
    }

}
