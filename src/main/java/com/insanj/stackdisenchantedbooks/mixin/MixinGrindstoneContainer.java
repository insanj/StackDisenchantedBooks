package com.insanj.stackdisenchantedbooks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.container.Container;
import net.minecraft.container.GrindstoneContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.BookItem;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.inventory.Inventory;
import net.minecraft.container.SlotActionType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.container.ContainerType;

import java.lang.reflect.Field;
import org.apache.commons.lang3.reflect.FieldUtils;

@Mixin(Container.class)
public class MixinGrindstoneContainer {
	@Inject(at = @At("HEAD"), method="onSlotClick")
	public void onSlotClick(int slotId, int clickData, SlotActionType action, PlayerEntity player, CallbackInfoReturnable ci) {
		System.out.println("onSlotClick");

		if ((Container)(Object)this instanceof GrindstoneContainer) {
			System.out.println("confirmed grindstone container");
			ItemStack stack = (ItemStack)ci.getReturnValue();
			System.out.println(String.format("slot click = %s", stack));
		}
	}
}

/*
-----
Notes
-----
/*
public MixinGrindstoneContainer(ContainerType type, int i) {
	super(type, i);
}


@Mixin(GrindstoneContainer.class)
public abstract class MixinGrindstoneContainer {

	@Shadow
	private Inventory resultInventory;

/*
	@Inject(at = @At("HEAD"), method="grind")
	public void grind(ItemStack item, int damage, int amount, CallbackInfoReturnable ci) {
		/*ItemStack value = (ItemStack)ci.getReturnValue();
		System.out.println(String.format("grind item = %s return value = %s", item, value));
		if (value == null) {
			System.out.println("grind null return value");
		} else if (value.getItem() instanceof BookItem) {
			System.out.println("grind value instanceof BookItem");
		} else if (value.getItem() instanceof EnchantedBookItem) {
			System.out.println("grind value instanceof EnchantedBookItem");

			Item emptyBookItem = new BookItem(new BookItem.Settings());
			ci.setReturnValue(new ItemStack(emptyBookItem));
		} else {
			System.out.println("grind value instanceof ???");
		}

		System.out.println("grindstone grind");

		if (item != null && item.getItem() instanceof EnchantedBookItem) {
			System.out.println("grindstone grinding enchanted book");
			ItemStack replacement = new ItemStack(new BookItem(new BookItem.Settings()));
			try {
					
					Inventory resultInventory = (Inventory)field.get(this);

					resultInventory.setInvStack(0, replacement);
					resultInventory.markDirty();
			} catch (Exception e) {
				System.out.println("[stackdisenchantedbooks] " + e);
			}
		}
	}

/*
	@Override
	private void updateResult() {
		System.out.println("grindstone updateresult");
	}

	@Override
  private void setResultInventory(Inventory resultInventory) {
    this.resultInventory = resultInventory;
  }
}

/*
	@Override
	public ItemStack grind(ItemStack item, int damage, int amount) {
		if (item != null && item.getItem() instanceof EnchantedBookItem) {
			ItemStack replacement = new ItemStack(new BookItem(new BookItem.Settings()));
			super.grind(replacement, damage, amount);
		} else {
			super.grind(item, damage, amount);
		}
	}
	*/
