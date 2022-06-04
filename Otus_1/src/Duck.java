public class Duck extends Animal implements IFlying {
    @Override
    public void Say(){
        System.out.println("Кря");
    }

    public void Fly(){
        System.out.println("Я лечу");
    }

    public Duck(String name, int age, String color, int weight){
        super(name, age, color, weight);
    }
}
