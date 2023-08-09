package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.endpoints.UserEndpoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest2 {
	Faker faker;
	User userPayLoad;
	public Logger logger;
	@BeforeClass
	public void setup()
	{	
		faker=new Faker();
		userPayLoad=new User();
		userPayLoad.setId(faker.idNumber().hashCode());
		userPayLoad.setUserName(faker.name().username());
		userPayLoad.setFirstName(faker.name().firstName());
		userPayLoad.setLastName(faker.name().lastName());
		userPayLoad.setEmail(faker.internet().safeEmailAddress());
		userPayLoad.setPassword(faker.internet().password());
		userPayLoad.setPhone(faker.phoneNumber().cellPhone());
		
		logger=LogManager.getLogger(this.getClass());

	}
	@Test(priority = 1)
	public void testPostUser()
	{
		logger.info("************Creating User***************");
		Response response=UserEndpoints2.createUser(userPayLoad);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("************User is created***************");
	}

	@Test(priority = 2)	
	public void testGetUserByName()
	{
		logger.info("************reading user info***************");
		Response response=UserEndpoints2.readUser(this.userPayLoad.getUserName());		
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),404);
		logger.info("************user info is displayed***************");
	}

	@Test(priority = 3)
	public void testUpdateUserByName()
	{
		logger.info("************updating User***************");
		userPayLoad.setFirstName(faker.name().firstName());
		userPayLoad.setLastName(faker.name().lastName());
		userPayLoad.setEmail(faker.internet().safeEmailAddress());

		Response response=UserEndpoints2.updateUser(this.userPayLoad.getUserName(), userPayLoad);
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(),200);

		Response responseAfterUpdate=UserEndpoints2.readUser(this.userPayLoad.getUserName());
		Assert.assertEquals(responseAfterUpdate.getStatusCode(),404);
		
		logger.info("************User is updated***************");

	} 

	@Test(priority = 4)
	public void testDeleteUserByName() {
		
		logger.info("************deleting User***************");
		Response response=UserEndpoints2.deleteUser(this.userPayLoad.getUserName());
		Assert.assertEquals(response.getStatusCode(), 404);
		logger.info("************User deleted***************");
	}

}
