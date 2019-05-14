package com.insanj.stackdisenchantedbooks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.container.GrindstoneContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.BookItem;
import net.minecraft.item.EnchantedBookItem;

@Mixin(GrindstoneContainer.class)
public class MixinGrindstoneContainer {
	@Inject(at = @At("HEAD"), method="grind")
	public void grind(ItemStack item, int damage, int amount, CallbackInfoReturnable ci) {
		
		ItemStack value = (ItemStack)ci.getReturnValue();
		System.out.println("grind value = " + value);
		if (value.getItem() instanceof BookItem) {
			System.out.println("grind value instanceof BookItem");
		} else if (value.getItem() instanceof EnchantedBookItem) {
			System.out.println("grind value instanceof EnchantedBookItem");

			Item emptyBookItem = new BookItem(new BookItem.Settings());
			ci.setReturnValue(new ItemStack(emptyBookItem));
		} else {
			System.out.println("grind value instanceof ???");
		}
	}
}