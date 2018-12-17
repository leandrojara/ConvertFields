package br.com.tdrinfo;

import java.util.List;

/**
 * ConvertFields
 * TDR Informática Ltda
 * Todos os direitos reservados ©
 * ***********************************************
 * Nome do arquivo: MyClass.java
 * Criado por : Leandro de Souza Jara
 * Data da criação : 17/12/2018
 * Observação :
 * ***********************************************
 */
public class MyClass {

    private String className;
    private List<MyField> fields;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<MyField> getFields() {
        return fields;
    }

    public void setFields(List<MyField> fields) {
        this.fields = fields;
    }
}
