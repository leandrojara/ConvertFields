package br.com.tdrinfo;

/**
 * gsea
 * TDR Informática Ltda
 * Todos os direitos reservados ©
 * ***********************************************
 * Nome do arquivo: ObjTeste.java
 * Criado por : Leandro de Souza Jara
 * Data da criação : 17/12/2018
 * Observação :
 * ***********************************************
 */
public class MyField {

    /**
     * Define o nome do atributo na classe
     */
    private String fieldName;
    /**
     * Conteúdo do atributo
     */
    private Object fieldValue;
    /**
     * Define a descrição do label do campo
     */
    private String labelName;
    /**
     * Define o tipo do atributo: String, int, double...
     */
    private FieldType fieldType;
    /**
     * Define a quantidade mínima de caracteres
     */
    private int minLength = 0;
    /**
     * Define a quantidade máxima de caracteres
     */
    private long maxLength = Long.MAX_VALUE;
    /**
     * Define se a informação é obrigatoria
     */
    private boolean required = false;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(Object fieldValue) {
        this.fieldValue = fieldValue;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public long getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(long maxLength) {
        this.maxLength = maxLength;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }
}
