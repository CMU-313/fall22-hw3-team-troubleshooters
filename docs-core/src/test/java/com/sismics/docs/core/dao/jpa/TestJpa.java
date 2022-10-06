package com.sismics.docs.core.dao.jpa;

import com.sismics.docs.BaseTransactionalTest;
import com.sismics.docs.core.dao.UserDao;
import com.sismics.docs.core.model.jpa.User;
import com.sismics.docs.core.util.TransactionUtil;
import com.sismics.docs.core.util.authentication.InternalAuthenticationHandler;
import org.junit.Assert;
import org.junit.Test;

// test the document layer
import com.sismics.docs.core.dao.DocumentDao;
import com.sismics.docs.core.model.jpa.Document;
import java.util.Date;

/**
 * Tests the persistance layer.
 * 
 * @author jtremeaux
 * @author jasminenie
 */
public class TestJpa extends BaseTransactionalTest {
    @Test
    public void testJpa() throws Exception {
        // Create a user
        UserDao userDao = new UserDao();
        User user = new User();
        user.setUsername("username");
        user.setPassword("12345678");
        user.setEmail("toto@docs.com");
        user.setRoleId("admin");
        user.setStorageQuota(10L);
        String user_id = userDao.create(user, "me");
        
        TransactionUtil.commit();

        // Search a user by his ID
        user = userDao.getById(user_id);
        Assert.assertNotNull(user);
        Assert.assertEquals("toto@docs.com", user.getEmail());

        // Authenticate using the database
        Assert.assertNotNull(new InternalAuthenticationHandler().authenticate("username", "12345678"));

        //Create a document
        DocumentDao documentDao = new DocumentDao();
        Document document = new Document();
        document.setUserId(user_id);
        document.setTitle("This is a new document.");
        document.setDescription("This is the description");
        document.setGPA(4.0);
        document.setAge(25);
        document.setGender("female");
        document.setExperience(5);
        document.setSkills(8);
        document.setProgram("Masters of Business Administration");
        document.setCreateDate(new Date());
        document.setLanguage("eng");
        String doc_id = documentDao.create(document, user_id);
        
        TransactionUtil.commit();

        //Search a document by its ID
        document = documentDao.getById(doc_id);
        Assert.assertNotNull(document);
        // asserts that document data is working:
        Assert.assertEquals(4.0, document.getGPA(), 0); // need the 0 for epilson/delta comparison because this is a double
        Assert.assertEquals(25, document.getAge());
        Assert.assertEquals("female", document.getGender());
        Assert.assertEquals(5, document.getExperience());
        Assert.assertEquals(8, document.getSkills());
        Assert.assertEquals("Masters of Business Administration", document.getProgram());
    }
}
