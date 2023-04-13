package net.feny.phantom_world.block.entity;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.feny.phantom_world.item.ModItems;
import net.feny.phantom_world.networking.ModMessages;
import net.feny.phantom_world.screen.PhantomBookHolderScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import static net.feny.phantom_world.block.custom.PhantomBookHolderBlock.HAS_BOOK;
import static net.feny.phantom_world.block.custom.PhantomBookHolderBlock.SELECTED_PAGE;
import static net.feny.phantom_world.block.entity.ModBlockEntities.PHANTOM_BOOK_HOLDER_ENTITY;

public class PhantomBookHolderEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
    public static final int SLOTS = 2;
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(SLOTS, ItemStack.EMPTY);
    private static int selected = 1;
    private static boolean passed = false;
    public float renderRotation = 0;
    public static boolean book = false;
    public float targetBookRotation;
    public float lastBookRotation;
    public int ticks;
    public static int availablePages = 0;
    public int getAvailablePages(){
        return availablePages;
    }
    ItemStack Page0 = ModItems.PAGE0.getDefaultStack();
    ItemStack Page1 = ModItems.PAGE1.getDefaultStack();
    ItemStack Page2 = ModItems.PAGE2.getDefaultStack();
    ItemStack Page3 = ModItems.PAGE3.getDefaultStack();
    ItemStack Page4 = ModItems.PAGE4.getDefaultStack();
    ItemStack Page5 = ModItems.PAGE5.getDefaultStack();
    ItemStack Page6 = ModItems.PAGE6.getDefaultStack();
    ItemStack Page6_PASS = ModItems.PAGE6_PASS.getDefaultStack();

    public ItemStack getRenderStack() {
        if(this.getStack(0).isEmpty()) {
            return this.getStack(0);
        } else {
            if (selected == 0){
                return this.Page0;
            }
            else if (selected == 1){
                return this.Page1;
            }else if (selected == 2){
                return this.Page2;
            }else if (selected == 3){
                return this.Page3;
            }else if (selected == 4){
                return this.Page4;
            }else if (selected == 5){
                return this.Page5;
            } {
                return passed? this.Page6_PASS:Page6;
            }
        }
    }

    public void setInventory(DefaultedList<ItemStack> inventory) {
        for (int i = 0; i < inventory.size(); i++) {
            this.inventory.set(i, inventory.get(i));
        }
    }

    @Override
    public void markDirty() {
        if(!world.isClient()) {
            PacketByteBuf data = PacketByteBufs.create();
            data.writeInt(inventory.size());
            for(int i = 0; i < inventory.size(); i++) {
                data.writeItemStack(inventory.get(i));
            }
            data.writeBlockPos(getPos());

            for (ServerPlayerEntity player : PlayerLookup.tracking((ServerWorld) world, getPos())) {
                ServerPlayNetworking.send(player, ModMessages.ITEM_SYNC, data);
            }
        }

        super.markDirty();
    }

    public PhantomBookHolderEntity(BlockPos pos, BlockState state) {
        super(PHANTOM_BOOK_HOLDER_ENTITY, pos, state);
    }


    //From the ImplementedInventory Interface

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    //These Methods are from the NamedScreenHandlerFactory Interface
    //createMenu creates the ScreenHandler itself
    //getDisplayName will Provide its name which is normally shown at the top

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        //We provide *this* to the screenHandler as our class Implements Inventory
        //Only the Server has the Inventory at the start, this will be synced to the client in the ScreenHandler
        return new PhantomBookHolderScreenHandler(syncId, playerInventory, this);
    }

    @Override
    public Text getDisplayName() {
        // for 1.19+
        return Text.translatable(getCachedState().getBlock().getTranslationKey());
        // for earlier versions
        // return new TranslatableText(getCachedState().getBlock().getTranslationKey());
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, this.inventory);
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, this.inventory);
    }

    public static void tick(World world, BlockPos blockPos, BlockState state, PhantomBookHolderEntity entity) {
        float g;

        entity.lastBookRotation = entity.renderRotation;
        PlayerEntity playerEntity = world.getClosestPlayer((double)blockPos.getX() + 0.5, (double)blockPos.getY() + 0.5, (double)blockPos.getZ() + 0.5, 3.0, false);
        if (playerEntity != null) {
            double d = playerEntity.getX() - ((double) blockPos.getX() + 0.5);
            double e = playerEntity.getZ() - ((double) blockPos.getZ() + 0.5);
            entity.targetBookRotation = (float) MathHelper.atan2(e, d);
        } else {
            entity.targetBookRotation += 0.02f;
        }
        while (entity.targetBookRotation >= (float)Math.PI) {
            entity.targetBookRotation -= (float)Math.PI * 2;
        }
        while (entity.targetBookRotation < (float)(-Math.PI)) {
            entity.targetBookRotation += (float)Math.PI * 2;
        }
        for (g = entity.targetBookRotation - entity.renderRotation; g >= (float)Math.PI; g -= (float)Math.PI * 2) {
        }
        while (g < (float)(-Math.PI)) {
            g += (float)Math.PI * 2;
        }
        entity.renderRotation += g * 0.4f;
        ++entity.ticks;

        if(world.isClient()) {
            return;
        }
        ItemStack bookItem = entity.getStack(0);
        book = bookItem.getItem() == ModItems.PHANTOM_WORLD_STARTER;

        assert entity.world != null;
        entity.world.setBlockState(entity.getPos(), entity.getCachedState().with(HAS_BOOK,entity.book));
        if (book) {
            world.syncWorldEvent(5000, blockPos, 0);
            if (bookItem.hasNbt()){
                availablePages = bookItem.getNbt().getInt("book_progress");
                if (availablePages == 7){
                    passed = true;
                }
                else passed = false;
            }
            else availablePages = 0;
            if (state.get(SELECTED_PAGE) <= availablePages){
                selected = state.get(SELECTED_PAGE);
            }
            else {
                entity.world.setBlockState(entity.getPos(), entity.getCachedState().with(SELECTED_PAGE,0));
            }
        }



    }


    public static int pageLevel(Item stack) {
        int i = 0;
        if (stack == ModItems.PAGE1) i = 1;
        if (stack == ModItems.PAGE2) i = 2;
        if (stack == ModItems.PAGE3) i = 3;
        if (stack == ModItems.PAGE4) i = 4;
        if (stack == ModItems.PAGE5) i = 5;
        if (stack == ModItems.PAGE6) i = 6;
        if (stack == ModItems.PASS) i = 7;
        return i;
    }

    public int getSelected() {
        return selected;
    }

}

