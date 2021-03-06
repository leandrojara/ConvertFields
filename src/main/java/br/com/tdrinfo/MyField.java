package br.com.tdrinfo;

/**
 * gsea
 * TDR Informática Ltda
 * Todos os direitos reservados ©
 * ***********************************************
 * Nome do arquivo: MyField.java
 * Criado por : Leandro de Souza Jara
 * Data da criação : 17/12/2018
 * Observação : essa classe define a estrutura de um field pertencente a uma classe
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
    private long minLength = 0L;
    /**
     * Define a quantidade máxima de caracteres
     */
    private long maxLength = Long.MAX_VALUE;
    /**
     * Define se a informação é obrigatoria
     */
    private boolean required = false;
    /**
     * Define o width do campo
     */
    private int percentWidth;
    /**
     * Define a URI que o campo de pesquisa deverá chamar, caso o fieldType seja do tipo FieldType.OBJECT
     */
    private String searchURI;

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

    public long getMinLength() {
        return minLength;
    }

    public void setMinLength(long minLength) {
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

    public int getPercentWidth() {
        return percentWidth;
    }

    public void setPercentWidth(int percentWidth) {
        this.percentWidth = percentWidth;
    }

    public String getSearchURI() {
        return searchURI;
    }

    public void setSearchURI(String searchURI) {
        this.searchURI = searchURI;
    }
}
