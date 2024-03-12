package Model;

import com.sun.jdi.VirtualMachine;

import java.util.Arrays;

public enum Type implements java.lang.reflect.Type, com.sun.jdi.Type {
    F(0),
    M(1),
    U(2);

    private final int value;

    Type(int value) {
        this.value = value;
    }

    public static Type getTypeByValue(int value){
        return Arrays.stream(Type.values()).filter(e->e.value==value).findFirst().orElseThrow();
    }

    @Override
    public String getTypeName() {
        return java.lang.reflect.Type.super.getTypeName();
    }

    @Override
    public String signature() {
        return null;
    }

    @Override
    public VirtualMachine virtualMachine() {
        return null;
    }
}