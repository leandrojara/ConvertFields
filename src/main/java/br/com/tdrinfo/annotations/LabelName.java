package br.com.tdrinfo.annotations;

/**
 * ConvertFields
 * TDR Informática Ltda
 * Todos os direitos reservados ©
 * ***********************************************
 * Nome do arquivo: LabelName.java
 * Criado por : Leandro de Souza Jara
 * Data da criação : 17/12/2018
 * Observação : esta anotação deve ser utilizada para definir o texto que será exibido no label do campo no front-end
 * ***********************************************
 */
public @interface LabelName {

    public String value() default "";
}
