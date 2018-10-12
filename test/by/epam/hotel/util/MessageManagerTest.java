package by.epam.hotel.util;

import java.util.Locale;

import org.testng.annotations.Test;

public class MessageManagerTest {
  @Test
  public void f() {
	  String  message = MessageManager.getProrerty("message.loginerror", new Locale("ru","RU"));
	  System.out.println(message);
  }
}
