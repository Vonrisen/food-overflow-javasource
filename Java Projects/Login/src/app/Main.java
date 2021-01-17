package app;

import java.sql.SQLException;
import java.util.ArrayList;
import daos_implementation.ShopDAOPostgresImplementation;
import daos_interfaces.ShopDAO;
import entities.Rider;
import entities.Shop;
import utilities.DateUtility;

public class Main {
	
	public static void main(String[] args) {
		
		
		ShopDAO shop_dao = new ShopDAOPostgresImplementation();
		ArrayList<Shop>shop_list=new ArrayList<Shop>();
		try {
			shop_list = shop_dao.getAllShops();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
//		for(Shop s : shop_list)
//			{System.out.println("SHOP: "+s.getName()+"\n\n");
//			for(Meal st : s.getMeal_list())
//				System.out.println(st.getAllergen_list());
//			}
//		String name = shop_list.get(0).getMeal_list().get(0).getName();
//		System.out.println(name);
		ArrayList<Rider>riders = shop_list.get(0).getEmployed_riders_list();
		DateUtility du = new DateUtility();
		for(Rider r : riders)
			System.out.println(r.getCf());
		
	}
	
}