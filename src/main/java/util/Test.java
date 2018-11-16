package util;

public class Test {
    public static void main(String[] args) {
        Father f = new Son();
        Test test = new Test();
        test.f(f);
        f.f();
    }

    private void f(Father f){
        System.out.println("Father");
    }

    private void f(Son s){
        System.out.println("Son");
    }
}

class Father{
    public void f(){
        System.out.println("Father");
    }
}

class Son extends Father{
    @Override
    public void f(){
        System.out.println("Son");
    }
}
