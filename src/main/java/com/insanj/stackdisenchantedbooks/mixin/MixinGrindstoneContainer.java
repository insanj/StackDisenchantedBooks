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

@Mixin(GrindstoneContainer.class)
public class MixinGrindstoneContainer {
	@Inject(at = @At("RETURN"), method="grind", cancellable=true)
	public void grind(ItemStack item, int damage, int amount, CallbackInfoReturnable ci) {
		ItemStack value = (ItemStack)ci.getReturnValue();
		if (value != null && value.getItem() instanceof BookItem) {
			value.removeSubTag("display");
			ci.setReturnValue(value);
		}
	}
}
