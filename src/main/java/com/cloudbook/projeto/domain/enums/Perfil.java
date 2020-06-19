package com.cloudbook.projeto.domain.enums;



public enum Perfil {
	
	ADMIN(1,"ROLE_ADMIN"),
	ALUNO(2,"ROLE_ALUNO");
	
	private int cod;
	private String desc;
	
	private Perfil(int cod,String desc) {
		this.cod=cod;
		this.desc=desc;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public static Perfil toEnum(Integer cod) {
		if(cod==null) {
			return null;
		}
		
		for(Perfil x: Perfil.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id invalido "+cod);
	}
}