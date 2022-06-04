public class Dog extends Animal {
    @Override
    public void Say(){
        System.out.println("Гав");
    }

    public Dog(String name, int age, String color, int weight){
        super(name, age, color, weight);
    }
}
