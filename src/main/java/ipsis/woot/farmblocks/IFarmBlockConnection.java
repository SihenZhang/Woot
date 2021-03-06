package ipsis.woot.farmblocks;

import net.minecraft.util.math.BlockPos;

public interface IFarmBlockConnection {

    void setMaster(IFarmBlockMaster master);
    void clearMaster();
    boolean hasMaster();
    BlockPos getStructurePos();
}
