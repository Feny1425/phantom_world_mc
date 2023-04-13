package net.feny.phantom_world.screen;

import net.feny.phantom_world.item.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.Property;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;

import static net.feny.phantom_world.block.entity.PhantomBookHolderEntity.SLOTS;
import static net.feny.phantom_world.block.entity.PhantomBookHolderEntity.pageLevel;
import static net.feny.phantom_world.item.custom.PhantomBookItem.BOOK_PROGRESS;

public class PhantomBookHolderScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final Property selectedRecipe = Property.create();



    //This constructor gets called on the client when the server wants it to open the screenHandler,
    //The client will call the other constructor with an empty Inventory and the screenHandler will automatically
    //sync this empty inventory with the inventory on the server.
    public PhantomBookHolderScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(SLOTS));
    }

    //This constructor gets called from the BlockEntity on the server without calling the other constructor first, the server knows the inventory of the container
    //and can therefore directly provide it as an argument. This inventory will then be synced to the client.
    public PhantomBookHolderScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(ScreenHandlers.PHANTOM_BOOK_HOLDER_SCREEN_HANDLER, syncId);
        checkSize(inventory, SLOTS);
        this.inventory = inventory;
        //some inventories do custom logic when a player opens it.
        inventory.onOpen(playerInventory.player);

        //This will place the slot in the correct locations for a 3x3 Grid. The slots exist on both server and client!
        //This will not render the background of the slots however, this is the Screens job
        //Our inventory
        this.addSlot(new Slot(inventory, 0, 92, 35){
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isOf(ModItems.PHANTOM_WORLD_STARTER);
            }
        });

        this.addSlot(new Slot(inventory, 1, 68, 35));



        //The player inventory
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 86 + i * 18));
            }
        }
        //The player Hotbar
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 144));
        }


    }

    @Override
    public boolean onButtonClick(PlayerEntity player, int id) {

        boolean book;
        ItemStack bookItem = inventory.getStack(0);
        book = bookItem.getItem() == ModItems.PHANTOM_WORLD_STARTER;
        if (book && id == 0) {
            int bookProgress = bookItem.hasNbt()? bookItem.getNbt().getInt(BOOK_PROGRESS):0;

            if (pageLevel(inventory.getStack(1).getItem()) == bookProgress+1) {
                if (bookProgress == 6){
                    player.sendMessage(Text.of( "§4Phantom Book §f: Congrats, you have collected everything you need for this book..."));
                }
                NbtCompound nbt = new NbtCompound();
                nbt.putInt("book_progress",pageLevel(inventory.getStack(1).getItem()));
                inventory.setStack(1, ItemStack.EMPTY);
                inventory.getStack(0).setNbt(nbt);
            }
        }
        return super.onButtonClick(player, id);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    public boolean canCombine(){
        int bookProgress = 0;
        ItemStack bookItem = inventory.getStack(0);
        boolean book = bookItem.getItem() == ModItems.PHANTOM_WORLD_STARTER;
        if (book) {
            bookProgress = bookItem.hasNbt() ? bookItem.getNbt().getInt(BOOK_PROGRESS) : 0;
        }
            return (inventory.getStack(1).getItem() == ModItems.PAGE1 && bookProgress+1 == 1 )||
                    (inventory.getStack(1).getItem() == ModItems.PAGE2&& bookProgress+1 == 2 )||
                    (inventory.getStack(1).getItem() == ModItems.PAGE3&& bookProgress+1 == 3 )||
                    (inventory.getStack(1).getItem() == ModItems.PAGE4&& bookProgress+1 == 4 )||
                    (inventory.getStack(1).getItem() == ModItems.PAGE5&& bookProgress+1 == 5 )||
                    (inventory.getStack(1).getItem() == ModItems.PAGE6&& bookProgress+1 == 6 )||
                    (inventory.getStack(1).getItem() == ModItems.PASS&& bookProgress+1 == 7 );
    }

    // Shift + Player Inv Slot
    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }


}