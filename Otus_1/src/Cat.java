public class Cat extends Animal {
    @Override
    public void Say(){
        System.out.println("Мяу");
    }

    public Cat(String name, int age, String color, int weight){
        super(name, age, color, weight);
    }
}
