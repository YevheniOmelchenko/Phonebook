package com.ait.phonebook.tests;

import com.ait.phonebook.models.Contact;
import com.ait.phonebook.models.User;
import com.ait.phonebook.utils.ContactData;
import com.ait.phonebook.utils.DataProviders;
import com.ait.phonebook.utils.UserData;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddNewContactTests extends TestBase {

    //precondition: User should be logged out
    @BeforeMethod
    public void ensurePrecondition() {
        if (!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSignOutButton();
        }
        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new User()
                .setEmail(UserData.EMAIL)
                .setPassword(UserData.PASSWORD));
        app.getUser().clickOnLoginButton();
    }

    @Test
    public void addNewContactPositiveTest() {
        app.getContact().clickOnAddLink();
        app.getContact().fillContactForm(new Contact()
                .setName(ContactData.NAME)
                .setLastname(ContactData.LAST_NAME)
                .setPhone(ContactData.PHONE)
                .setAddress(ContactData.ADDRESS)
                .setMail(ContactData.EMAIL)
                .setDescription(ContactData.DESCRIPTION));
        app.getContact().clickOnSaveButton();

        Assert.assertTrue(app.getContact().isContactCreatedByText("Karl"));
    }


    @AfterMethod
    public void postCondition() {
        app.getContact().removeContact();
    }


    @Test(dataProvider = "addNewContact", dataProviderClass = DataProviders.class)
    public void addNewContactFormDataProviderTest(String name, String lastname, String phone,
                                                  String mail, String address, String desc) {
        app.getContact().clickOnAddLink();

        app.getContact().fillContactForm(new Contact()
                .setName(name)
                .setLastname(lastname)
                .setPhone(phone)
                .setMail(mail)
                .setAddress(address)
                .setDescription(desc));
        app.getContact().clickOnSaveButton();

    }
    @Test(dataProvider = "addNewContactFromCSV", dataProviderClass = DataProviders.class)
    public void addNewContactFormDataProviderCSWPositiveTest(Contact contact) {
        app.getContact().clickOnAddLink();

        app.getContact().fillContactForm(contact);
        app.getContact().clickOnSaveButton();

    }

}
