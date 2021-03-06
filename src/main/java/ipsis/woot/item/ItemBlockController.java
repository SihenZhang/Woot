package ipsis.woot.item;

import ipsis.Woot;
import ipsis.woot.util.WootMob;
import ipsis.woot.util.WootMobBuilder;
import ipsis.woot.util.StringHelper;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemBlockController extends ItemBlock {

    public ItemBlockController(Block block) {

        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(false);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

        if (!stack.isEmpty() && stack.hasTagCompound()) {

            WootMob wootMob = WootMobBuilder.create(stack.getTagCompound());
            if (wootMob.isValid()) {
                tooltip.add(String.format("Mob: %s", StringHelper.localize(wootMob.getDisplayName())));
                tooltip.add(String.format("Tier: %s", Woot.wootConfiguration.getFactoryTier(worldIn, wootMob.getWootMobName())));
            }
        }

        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
