package test;

import models.userAuth;
import org.testng.Assert;
import service.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AuthTests extends baseTest{
 public userAuth auth;
    @BeforeClass(alwaysRun = true)
    public void setup()
    {
        auth= new userAuth();
        auth.setId(12);
        auth.setEmail("pottt@gmail.com");
        auth.setPassword("dummy@123");
    }
    @Test(description = "user does sign up",priority=1)
    public void authsignupTest()
    {
        AuthService auths= new AuthService();
        String msg=auths.signupUser(auth);
        Assert.assertEquals(msg,"User created successfully");
    }
    @Test(description ="user logs in", groups = {"smoke"})
    public void authloginTest()
    {
        AuthService auths=new AuthService();
        auths.loginUser(auth);
    }
}
