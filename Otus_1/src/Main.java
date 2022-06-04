import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    public enum Commands {
        add,
        list,
        exit,
        other
    }

    static Scanner scan = new Scanner(System.in);

    public static Animal createAnimal(){
        System.out.println("Enter kind od animal");
        String s = scan.next();
        String kind = s.trim().toLowerCase(Locale.ROOT);

        System.out.println("Enter name");
        String name = scan.next();
        System.out.println("Enter age");
        int age = Integer.valueOf(scan.next());
        System.out.println("Enter color");
        String color = scan.next();
        System.out.println("Enter weight");
        int weight = Integer.valueOf(scan.next());

        Animal animal;

        switch (kind){
            case "cat":
                System.out.println("Cat is creating...");
                animal =  new Cat(name, age, color, weight);
                break;
            case "dog":
                System.out.println("Dog is creating...");
                animal =  new Dog(name, age, color, weight);
                break;
            case "duck":
                System.out.println("Duck is creating...");
                animal =  new Duck(name, age, color, weight);
                break;
            default:
                System.out.println("Animal is creating...");
                animal =  new Animal(name, age, color, weight);
                break;
        }

        return animal;
    }

    public static Commands getCommand(){
        System.out.println("Enter command (add/list/exit)");
        String s = scan.next();
        try {
            return Commands.valueOf(s.trim().toLowerCase(Locale.ROOT));
        }
        catch (Exception e){
            return Commands.other;
        }
    }

    public static void main(String[] args) {
        ArrayList<Animal> animals =  new ArrayList<Animal>(Arrays.asList(new Cat("Муся", 10, "серый", 3),
                new Dog("Пчелка", 15, "рыжий", 14),
                new Duck("Крякря", 1, "серый", 1),
                new Animal("Боня", 8, "черный", 1)));

        Commands c = null;
        while (c != Commands.exit){
            c = getCommand();
            switch (c){
                case add:
                    Animal newAnimal = createAnimal();
                    animals.add(newAnimal);
                    break;
                case list:
                    System.out.println("Animals:");
                    Stream<Animal> stream = animals.stream();
                    stream.forEach(System.out::println);
                    break;
                default:
                    break;
            }
        }


    }

}
