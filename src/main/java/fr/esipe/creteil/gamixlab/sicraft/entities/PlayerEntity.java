package fr.esipe.creteil.gamixlab.sicraft.entities;


 
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "players")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerEntity {

    @Id
    @Column(name = "id_player")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPlayer;

    
    @Column(name = "player_name", unique = true)
    private String playerName;

    @Column(name = "x_position")
    private Double xPosition;
    
    @Column(name = "y_position")
    private Double yPosition;
    
    @Column(name = "z_position")
    private Double zPosition;
    
    @Column(name = "health_point")
    private Integer hp;
    
    @Column(name = "breath")
    private Integer breath;

    @Column(name = "date_creation")
    private Timestamp dateCreation;
    
    
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "player", fetch = FetchType.LAZY)
    private List<MaterialEntity> materials;

    @Override
    public String toString() {
        return "PlayerEntity{" + "idPlayer=" + idPlayer + ", playerName=" + playerName + ", xPosition=" + xPosition + ", yPosition=" + yPosition + ", zPosition=" + zPosition + ", hp=" + hp + ", breath=" + breath + ", dateCreation=" + dateCreation + '}';
    }

    
    
    
    
}
