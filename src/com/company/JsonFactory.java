package com.company;

public class JsonFactory {


    //use getShape method to get object of type shape
    public Parser getParser(String ParserType){
        if(ParserType.toLowerCase().contains("layout")){
            return new LayoutParser();
        }
        if(ParserType.toLowerCase().contains("event")){
            return new EventParser();
        }

        return null;
    }
}

