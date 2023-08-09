package api.endpoints;

/*
 swagger URI---> https://petstore.swagger.io
 create user(post): https:// petstore.swagger.io/v2/user
 get user (get): https:// petstore.swagger.io/v2/user/{username}
 Update user (put):https:// petstore.swagger.io/v2/user/{username}
 delete user (delete):https:// petstore.swagger.io/v2/user/{username}
 */

public class Routes {
	
	public static String base_url="https://petstore.swagger.io/v2";
	
	// user module
	public static String post_url=base_url+"/user";
	public static String get_url=base_url+"/user/{user_name}";
	public static String update_url=base_url+"/user/{user_name}";
	public static String delete_url=base_url+"/user/{user_name}";
	
	//store module
	
	// here you will create store module URLs
	 
	//pet module
	
	// here you will create pet module URLs

}
