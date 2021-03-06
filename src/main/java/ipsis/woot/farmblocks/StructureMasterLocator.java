package ipsis.woot.farmblocks;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class StructureMasterLocator implements IFarmBlockMasterLocator {

    @Nullable
    public IFarmBlockMaster findMaster(World world, BlockPos origin, IFarmBlockConnection farmBlockStructure) {

        List<IFarmBlockConnection> connected = new ArrayList<>();
        Stack<IFarmBlockConnection> traversing = new Stack<>();

        IFarmBlockMaster tmpMaster = null;
        boolean masterFound = false;

        traversing.add(farmBlockStructure);
        while (!masterFound && !traversing.isEmpty()) {
            IFarmBlockConnection curr = traversing.pop();

            connected.add(curr);
            for (EnumFacing facing : EnumFacing.values()) {
                TileEntity te = world.getTileEntity(curr.getStructurePos().offset(facing));
                if (te instanceof IFarmBlockStructure && te instanceof IFarmBlockConnection && !connected.contains(te)) {
                    traversing.add((IFarmBlockConnection) te);
                } else if (te instanceof IFarmBlockMaster) {
                    masterFound = true;
                    tmpMaster = (IFarmBlockMaster)te;
                }
            }
        }

        return tmpMaster;
    }
}
