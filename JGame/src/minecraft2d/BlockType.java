package minecraft2d;

public enum BlockType {
    AIR("resources/air.png"), STONE("resources/stone.png"), 
    DIRT("resources/dirt.png"), GRASS("resources/grass.png");
    
    public final String location;
    
    BlockType(String location) {
        this.location = location;
    }
}
