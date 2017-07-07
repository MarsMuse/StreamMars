package com.beta.app.decorator;

public class ConcreteDecOther  extends  FireDecorator{

    public ConcreteDecOther(Component component) {
        super(component);
    }

    @Override
    public void fire() {
        System.out.println("******************other层装饰开始******************");
        component.fire();
        System.out.println("******************otherDecoratorEnd******************");
    }

}
