public class Repositories {
    private static Repositories instance = new Repositories();

    public static Repositories getInstance(){
        return instance;
    }

    public TodoRepository getTodoRepository(){
        return new MockupRepository();
    }
}
