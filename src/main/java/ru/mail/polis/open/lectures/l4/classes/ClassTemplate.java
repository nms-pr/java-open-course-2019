package ru.mail.polis.open.lectures.l4.classes;

/**
 * @author mikhail.nechaev
 * Since 18/03/2019
 */
public /* ? access modifier */ class /* keyword */ ClassTemplate /* classname */ {

    public /* ? access modifier */ int /* type */ varX /* instance variable */;
    private  /* ? access modifier */ String /* type */ varS /* instance variable */;
    static double varD /* class variable */ = 0.5F /* default value */;

    //default implicit constructor -> implicit extends Object
    /*
    public ClassTemplate() {
        super();
    }
    */

    int /* type */ getVarX /* method name */(/* parameter list */) {
        // body of method
        return varX;
    }

    void /* type */ setVarS /* method name */(String varS /* parameter list */) {
        // body of method
        this.varS = varS;
    }

    public static void main(String[] args) {
        ClassTemplate /* classname */ classTemplate1 /* class var */; // == null
        classTemplate1 /* class var */ = new ClassTemplate /* classname */(/* constructor */);

        ClassTemplate classTemplate2 = new ClassTemplate();
    }

}
