package ru.mail.polis.open.task4;

import org.jetbrains.annotations.Nullable;

public class ExprBuilderImpl {

    //todo check skobki

    Expr build(@Nullable String input) {
        if ( (input == null) || (input.length() == 0) ) throw new IllegalArgumentException("Wrong syntax");

        int openBracketsCount = 0;

        //Checking + and -
        for ( int i = input.length() - 1, j; i >= 0 ; i-- ) {
            if(input.charAt(i) == ')'){
                openBracketsCount++;
            }
            if(input.charAt(i) == '('){
                openBracketsCount--;
            }
            if(openBracketsCount == 0){ // Ignore mode off
                switch (input.charAt(i)) {
                    case '+':
                        return new Add(build(input.substring(0,i)), build(input.substring(i+1)));
                    case '-':
                        j = i;
                        while(true){
                            if (j > 0) {
                                if(Character.isDigit(input.charAt(j)) ){
                                    return new Sub(build(input.substring(0,i)), build(input.substring(i+1)));
                                }
                            } else {
                                return new Sub(build("0" + input.substring(0,i)), build(input.substring(i+1)));
                            }
                            j--;
                        }
                }
            }
        }

        if (openBracketsCount != 0) throw new IllegalArgumentException("Wrong brackets count");
        openBracketsCount = 0;

        //Checking *
        for ( int i = input.length() - 1; i >= 0 ; i-- ) {
            if(input.charAt(i) == ')'){
                openBracketsCount++;
            }
            if(input.charAt(i) == '('){
                openBracketsCount--;
            }
            if(openBracketsCount == 0) { // Ignore mode off
                if(input.charAt(i) == '*') {
                    return new Mult(build(input.substring(0,i)), build(input.substring(i+1)));
                }
            }
        }

        if (openBracketsCount != 0) throw new IllegalArgumentException("Wrong brackets count");
        openBracketsCount = 0;

        //Checking /
        for ( int i = input.length() - 1; i >= 0 ; i-- ) {
            if(input.charAt(i) == ')'){
                openBracketsCount++;
            }
            if(input.charAt(i) == '('){
                openBracketsCount--;
            }
            if(openBracketsCount == 0) { // Ignore mode off
                if (input.charAt(i) == '/') {
                    return new Div(build(input.substring(0, i)), build(input.substring(i + 1)));
                }
            }
        }

        if (openBracketsCount != 0) throw new IllegalArgumentException("Wrong brackets count");
        openBracketsCount = 0;

        //Checking ^
        for ( int i = input.length() - 1; i >= 0 ; i-- ) {
            if(input.charAt(i) == ')'){
                openBracketsCount++;
            }
            if(input.charAt(i) == '('){
                openBracketsCount--;
            }
            if(openBracketsCount == 0) { // Ignore mode off
                if (input.charAt(i) == '^') {
                    return new Pow(build(input.substring(0, i)), build(input.substring(i + 1)));
                }
            }
        }

        if (openBracketsCount != 0) throw new IllegalArgumentException("Wrong brackets count");
        openBracketsCount = 0;

        //Checking brackets
        if ( (input.charAt(0) == '(') && (input.charAt(input.length()-1) == ')') ){
            //throw new IllegalArgumentException("brackets ok " + input.substring(1, input.length()-1));
            return new Brackets(build(input.substring(1, input.length()-1)));
        }

        if (openBracketsCount != 0) throw new IllegalArgumentException("Wrong brackets count");

        //Checking constant
        int tmp = 0;
        for ( int i = 0; i < input.length(); i++ ) {
            if(!Character.isSpaceChar(input.charAt(i))) {
                if (Character.isDigit(input.charAt(i))) {
                    tmp = tmp * 10 + input.charAt(i) - '0';
                } else {
                    throw new IllegalArgumentException("Wrong symbol: " + input.charAt(i));
                }
            }
        }

        return new Const(tmp);
    }
}
