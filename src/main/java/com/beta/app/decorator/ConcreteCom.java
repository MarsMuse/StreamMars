package com.beta.app.decorator;

public class ConcreteCom implements Component {

    @Override
    public void fire() {
        System.out.println("未被装饰的原始Fire");

    }

}
