package com.example.threedots.findyourguy;

import com.example.threedots.findyourguy.Model.Message;
import com.example.threedots.findyourguy.Model.Room;
import com.example.threedots.findyourguy.Model.User;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test_Room() throws Exception {
        Room room = new Room(10, "Pagos", 745, "vagos", true);

        assertEquals(10, room.getID());
        assertEquals("Pagos", room.getTitle());
        assertEquals(745, room.getUIDCreator());
        assertEquals("vagos", room.getUserName());
        assertTrue(room.getIsPrivate());
    }

    @Test
    public void test_Message() throws Exception {
        Message message = new Message(10, "Paok", 45, "Hello");

        assertEquals(10, message.getID());
        assertEquals("Paok", message.getUserName());
        assertEquals(45, message.getUIDSender());
        assertEquals("Hello", message.getMessage());
    }

    @Test
    public void test_User() throws Exception{
        User user = new User(1, 6.3, 2, "Evangelos", "Monasthriou 18", "Good", "Welcome");

        assertEquals(1, user.getUserId());
        assertEquals(6.3, user.getAvgRating(), 0);
        assertEquals(2, user.getCRating());
        assertEquals("Evangelos", user.getName());
        assertEquals("Monasthriou 18", user.getAddress());
        assertEquals("Good", user.getAttitude());
        assertEquals("Welcome", user.getDescription());
    }

    @Test
    public void test_Set_User() throws Exception{
        User user = new User();

        user.setUserId(2);
        user.setAvgRating(4.2);
        user.setCRating(10);
        user.setName("Vagos");
        user.setAddress("TEI");
        user.setAttitude("bad");
        user.setDescription("other");
        assertEquals(2, user.getUserId());
        assertEquals(4.2, user.getAvgRating(), 0);
        assertEquals(10, user.getCRating());
        assertEquals("Vagos", user.getName());
        assertEquals("TEI", user.getAddress());
        assertEquals("bad", user.getAttitude());
        assertEquals("other", user.getDescription());
    }

    @Test
    public void test_Set_Room() throws Exception{
        Room room = new Room();

        room.setID(9);
        room.setTitle("Party");
        room.setUIDCreator(5);
        room.setUserName("xristos");
        room.setIsPrivate(false);

        assertEquals(9, room.getID());
        assertEquals("Party", room.getTitle());
        assertEquals(5, room.getUIDCreator());
        assertEquals("xristos", room.getUserName());
        assertFalse(room.getIsPrivate());
    }

    @Test
    public void test_Set_Message() throws Exception{
        Message message = new Message();

        message.setID(758);
        message.setUserName("giannhs");
        message.setMessage("dwda");
        message.setUIDSender(54);

        assertEquals(758, message.getID());
        assertEquals("giannhs", message.getUserName());
        assertEquals("dwda", message.getMessage());
        assertEquals(54, message.getUIDSender());
    }
}