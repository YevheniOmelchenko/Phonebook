package com.ait.phonebook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomepageTest extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        if (!app.getHomaPage().isHomeComponentPresent()) {
            app.getHomaPage().clickOnHomeLink();
        }
    }

    @Test
    public void isHomeComponentPresentTest() {
        Assert.assertTrue(app.getHomaPage().isHomeComponentPresent());
    }

}
