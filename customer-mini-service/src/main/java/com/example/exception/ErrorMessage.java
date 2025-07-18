package com.example.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {

    private MessageType messageType;
    private String ofStatic;

    public String prepareErrorMessage(){

        StringBuilder builder = new StringBuilder();
        builder.append(messageType.getMesaj());
        if(ofStatic != null){
            builder.append(" Hata Kaynağı : " + ofStatic);
        }
        return builder.toString();

    }

}
