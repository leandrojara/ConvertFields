package br.com.tdrinfo.annotations;

/**
 * ConvertFields
 * TDR Informática Ltda
 * Todos os direitos reservados ©
 * ***********************************************
 * Nome do arquivo: ConvertField.java
 * Criado por : Leandro de Souza Jara
 * Data da criação : 17/12/2018
 * Observação :
 * ***********************************************
 */
public @interface ConvertField {

    public String labelName() default "";

    public int percentWidth() default 100;

    public String searchURI();
}
