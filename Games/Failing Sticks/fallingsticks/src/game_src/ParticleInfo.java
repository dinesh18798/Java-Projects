package game_src;

/**
 * Created by Dinesh on 22/03/2020
 */
public class ParticleInfo {

    private int id;
    private GameInfo.ParticleType particleType;

    public ParticleInfo(int id, GameInfo.ParticleType type) {
        this.id = id;
        this.particleType = type;
    }

    public int getId() {
        return id;
    }

    public GameInfo.ParticleType getParticleType() {
        return particleType;
    }

    public void setParticleType(GameInfo.ParticleType particleType) {
        this.particleType = particleType;
    }
}
