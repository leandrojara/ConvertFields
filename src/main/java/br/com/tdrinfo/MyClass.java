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
 * Observação : esta classe representa uma classe de modelo, que possui um nome e uma lista de fields
 * ***********************************************
 */
public class MyClass {

    /**
     * Nome da classe que está sendo representada
     * @required é necessário para converter de volta para objeto
     */
    private String className;
    /**
     * Lista de fields que a classe possui, e suas características
     */
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
