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
	@Inject(at = @At("RETURN"), method="onSlotClick", cancellable = true)
	public void onSlotClick(int slotId, int clickData, SlotActionType action, PlayerEntity player, CallbackInfoReturnable ci) {
		if ((Container)(Object)this instanceof GrindstoneContainer) {
			ItemStack stack = (ItemStack)ci.getReturnValue();
			if (stack != null && stack.getItem() instanceof EnchantedBookItem) {
				ci.setReturnValue(new ItemStack(new BookItem(new BookItem.Settings())));
			}
		}
	}
}
