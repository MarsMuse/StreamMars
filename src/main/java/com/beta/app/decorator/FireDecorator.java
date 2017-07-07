package com.beta.app.decorator;

public abstract class FireDecorator implements Component {
    
    protected  Component  component;
    
    protected FireDecorator(Component  component){
        this.component = component;
    }

}
