package controllers;


import gui.ShopFrame;
import gui.ShopMealFrame;

public class ShopController {
	
	public ShopController() 
	{
		
	}
	
	public void openShopFrame()
	{
		ShopFrame shop_frame = new 	ShopFrame(this);
		shop_frame.setVisible(true);
		return;
	}
	
	public void openShopMealFrame(ShopFrame shop_frame)
	{
		shop_frame.dispose();
		ShopMealFrame shop_meal_frame = new ShopMealFrame(this);
		shop_meal_frame.setVisible(true);
		return;
	}

}
