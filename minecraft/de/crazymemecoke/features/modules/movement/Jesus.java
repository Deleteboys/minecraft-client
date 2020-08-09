package de.crazymemecoke.features.modules.movement;

import de.crazymemecoke.manager.clickguimanager.settings.Setting;
import de.crazymemecoke.Client;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.utils.time.TimeHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.optifine.BlockPosM;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;

public class Jesus extends Module {

    public TimeHelper time = new TimeHelper();
    ArrayList<String> mode = new ArrayList<>();
    private int stage;
    private boolean canjump;
    private int delay;
    private int timer;

    public Jesus() {
        super("Jesus", Keyboard.KEY_NONE, Category.MOVEMENT, -1);
        mode.add("Normal");
        mode.add("Dolphin");
        mode.add("AAC");
        Client.instance().getSetmgr().rSetting(new Setting("Mode", this, "Normal", mode));
    }

    @Override
    public void onEnable() {
        stage = 0;
    }

    public void onUpdate() {
        String jesusMode = Client.instance().getSetmgr().getSettingByName("Mode", this).getMode();
        if (getState()) {
            if (jesusMode.equalsIgnoreCase("Normal")) {
                doNormalJesus();
            } else if (jesusMode.equalsIgnoreCase("Dolphin")) {
                doDolphinJesus();
            } else if (jesusMode.equalsIgnoreCase("AAC")) {
                if (mc.thePlayer.isInWater()) {
                    mc.thePlayer.motionY = 5.9D;
                    mc.thePlayer.jumpMovementFactor *= 0.9F;
                    mc.thePlayer.motionY = 0.2D;
                }
            }
        }
    }

    public static boolean getColliding(final int i) {
        final Minecraft mc = Minecraft.getMinecraft();
        int mx = i;
        int mz = i;
        int max = i;
        int maz = i;
        if (mc.thePlayer.motionX > 0.01) {
            mx = 0;
        } else if (mc.thePlayer.motionX < -0.01) {
            max = 0;
        }
        if (mc.thePlayer.motionZ > 0.01) {
            mz = 0;
        } else if (mc.thePlayer.motionZ < -0.01) {
            maz = 0;
        }
        final int xmin = MathHelper.floor_double(mc.thePlayer.getEntityBoundingBox().minX - mx);
        final int ymin = MathHelper.floor_double(mc.thePlayer.getEntityBoundingBox().minY - 1.0);
        final int zmin = MathHelper.floor_double(mc.thePlayer.getEntityBoundingBox().minZ - mz);
        final int xmax = MathHelper.floor_double(mc.thePlayer.getEntityBoundingBox().minX + max);
        final int ymax = MathHelper.floor_double(mc.thePlayer.getEntityBoundingBox().minY + 1.0);
        final int zmax = MathHelper.floor_double(mc.thePlayer.getEntityBoundingBox().minZ + maz);
        for (int x = xmin; x <= xmax; ++x) {
            for (int y = ymin; y <= ymax; ++y) {
                for (int z = zmin; z <= zmax; ++z) {
                    final Block block = getBlock(new BlockPos(x, y, z));
                    if (block instanceof BlockLiquid && !mc.thePlayer.isInsideOfMaterial(Material.lava) && !mc.thePlayer.isInsideOfMaterial(Material.water)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static Block getBlock(final BlockPos pos) {
        return Minecraft.getMinecraft().theWorld.getBlockState(pos).getBlock();
    }

    public static boolean isInLiquid() {
        boolean inLiquid = false;
        int y = (int) mc.thePlayer.boundingBox.minY;
        for (int x = MathHelper.floor_double(mc.thePlayer.boundingBox.minX); x < MathHelper
                .floor_double(mc.thePlayer.boundingBox.maxX) + 1; x++) {
            for (int z = MathHelper.floor_double(mc.thePlayer.boundingBox.minZ); z < MathHelper
                    .floor_double(mc.thePlayer.boundingBox.maxZ) + 1; z++) {
                Block block = mc.theWorld.getBlockState(new BlockPos(x, y, z)).getBlock();
                if ((block != null) && (!(block instanceof BlockAir))) {
                    if (!(block instanceof BlockLiquid)) {
                        return false;
                    }
                    inLiquid = true;
                }
            }
        }
        return inLiquid;
    }

    private void doDolphinJesus() {
        final BlockPos bp = new BlockPosM(mc.thePlayer.posX, mc.thePlayer.posY - 1.0, mc.thePlayer.posZ);
        if (!canjump && mc.theWorld.getBlockState(bp).getBlock() == Blocks.water) {
            ++delay;
            stage = 0;
            mc.thePlayer.motionY = 0.1;
        } else if (mc.thePlayer.onGround && mc.theWorld.getBlockState(bp).getBlock() != Blocks.water) {
            canjump = false;
            delay = 0;
        }
        if (delay > 2) {
            canjump = true;
        }
        if (canjump) {
            ++stage;
            double moty = mc.thePlayer.motionY;
            switch (stage) {
                case 1: {
                    moty = 0.5;
                    break;
                }
                case 2: {
                    moty = 0.483;
                    break;
                }
                case 3: {
                    moty = 0.46;
                    break;
                }
                case 4: {
                    moty = 0.42;
                    break;
                }
                case 5: {
                    moty = 0.388;
                    break;
                }
                case 6: {
                    moty = 0.348;
                    break;
                }
                case 7: {
                    moty = 0.316;
                    break;
                }
                case 8: {
                    moty = 0.284;
                    break;
                }
                case 9: {
                    moty = 0.252;
                    break;
                }
                case 10: {
                    moty = 0.22;
                    break;
                }
                case 11: {
                    moty = 0.188;
                    break;
                }
                case 12: {
                    moty = 0.166;
                    break;
                }
                case 13: {
                    moty = 0.165;
                    break;
                }
                case 14: {
                    moty = 0.16;
                    break;
                }
                case 15: {
                    moty = 0.136;
                    break;
                }
                case 16: {
                    moty = 0.11;
                    break;
                }
                case 17: {
                    moty = 0.111;
                    break;
                }
                case 18: {
                    moty = 0.1095;
                    break;
                }
                case 19: {
                    moty = 0.073;
                    break;
                }
                case 20: {
                    moty = 0.085;
                    break;
                }
                case 21: {
                    moty = 0.06;
                    break;
                }
                case 22: {
                    moty = 0.036;
                    break;
                }
                case 23: {
                    moty = 0.04;
                    break;
                }
                case 24: {
                    moty = 0.03;
                    break;
                }
                case 25: {
                    moty = 0.004;
                    break;
                }
                case 26: {
                    moty = 0.0025;
                    break;
                }
                case 27: {
                    moty = 0.002;
                    break;
                }
                case 28: {
                    moty = 0.0015;
                    break;
                }
                case 29: {
                    moty = -0.025;
                    break;
                }
                case 30: {
                    moty = -0.06;
                    break;
                }
                case 31: {
                    moty = -0.09;
                    break;
                }
                case 32: {
                    moty = -0.12;
                    break;
                }
                case 33: {
                    moty = -0.15;
                    break;
                }
                case 34: {
                    moty = -0.18;
                    break;
                }
                case 35: {
                    moty = -0.21;
                    break;
                }
                case 36: {
                    moty = -0.24;
                    break;
                }
                case 37: {
                    moty = -0.28;
                    break;
                }
                case 38: {
                    moty = -0.34;
                    break;
                }
                case 39: {
                    moty = -0.4;
                    break;
                }
                case 40: {
                    moty = -0.46;
                    break;
                }
                case 41: {
                    moty = -0.52;
                    break;
                }
                case 42: {
                    moty = -0.58;
                    break;
                }
                case 43: {
                    moty = -0.65;
                    break;
                }
                case 44: {
                    moty = -0.71;
                    break;
                }
                case 45: {
                    canjump = false;
                    break;
                }
            }
            mc.thePlayer.motionY = moty;
        }
        if (mc.thePlayer.moveForward == 0.0f && mc.thePlayer.moveStrafing == 0.0f && !mc.thePlayer.isSneaking() && getColliding(0)) {
            final int delay = 40;
            if (timer < delay) {
                ++timer;
            } else {
                ++timer;
                if (timer < delay + 5) {
                    mc.thePlayer.motionX = 0.1;
                } else if (timer < delay + 20 && timer > delay + 10) {
                    mc.thePlayer.motionZ = -0.1;
                } else if (timer < delay + 30 && timer > delay + 20) {
                    mc.thePlayer.motionX = -0.1;
                } else if (timer < delay + 40 && timer > delay + 30) {
                    mc.thePlayer.motionZ = 0.1;
                }
                if (timer > delay + 40) {
                    timer = delay;
                }
            }
        } else {
            timer = 0;
        }
    }

    private void doNormalJesus() {
        if (mc.thePlayer == null) {
            return;
        }
        if ((isInLiquid()) && (mc.thePlayer.isInsideOfMaterial(Material.air)) && (!mc.thePlayer.isSneaking())) {
            try {
                if (time.isDelayComplete(50L)) {
                    mc.thePlayer.motionY = 0.01D;
                    time.setLastMS();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            mc.thePlayer.motionY = 0.11D;
        }
    }
}