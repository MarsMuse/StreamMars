package com.beta.app.decorator;

public class ConcreteDec extends FireDecorator{

    
    public ConcreteDec(Component component) {
        super(component);
    }

    @Override
    public void fire() {
        System.out.println("****************装饰过后的Fire****************");
        component.fire();
        System.out.println("*********************************************");
        System.out.println("*********************************************");
    }

}
