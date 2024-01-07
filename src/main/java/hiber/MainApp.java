package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("Andrey", "Ivanov", "user1@mail.ru",
              new Car("LADA",2109)));
      userService.add(new User("Ivan", "Prokhorov", "user2@mail.ru",
              new Car("BMW",3)));
      userService.add(new User("Robert", "Forstemann", "user3@mail.ru",
              new Car("AUDI",6)));
      userService.add(new User("Mihail", "Aleksandrov", "user4@mail.ru",
              new Car("PORSCHE",911)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      System.out.println(userService.getUserByCar("BMW", 3));

      context.close();
   }
}
