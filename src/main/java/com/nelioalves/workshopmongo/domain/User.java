package com.nelioalves.workshopmongo.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/* @Document - annotation usada para informar para o spring data
 * que essa entidade corresponde a uma coleção do meu banco de dados mongodb.
 * 
 * collection = "user" serve para personalizar o nome da coleção, caso
 * contrário o spring data utilizará o nome da entidade.
 * */
@Document(collection = "user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String name;
	private String email;
	
	/* A annotation DBRef informa para o spring que o atributo
	 * post estará apenas referenciando outra coleção do mongoDB, ou seja,
	 * os ids referentes a cada registro da outra coleção.
	 * 
	 * Geralmente a gente não quer carregar automaticamente os posts
	 * quando eu carregar um user do banco de dados. Vamos supor que
	 * você está recuperando os users para fazer um relatório de
	 * users, vamos supor que você faz um consulta que te retorna mil
	 * users, imagina se para cada um desses mil users já viessem também
	 * a lista de posts dele, ia ser muito dado, muito tráfego na rede
	 * desnecessário, então para evitar isso, nós colocamos na frente do
	 * DBRef o lazy = true, isso irá garantir para nós que o posts só irão
	 * ser carregados se eu explicitamente acessa-los.
	 * 
	 * Se eu recuperar users ele trafegarão pela rede, pois eles estão
	 * hospedados em um servidor de banco de dados, então os dados trafegam
	 * pela rede até chegaram ao requerente deles, que é o cliente,
	 * meu computador.
	 * */
	@DBRef(lazy = true)
	private List<Post> posts = new ArrayList<>();
	
	public User() {}

	public User(String id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

	public List<Post> getPosts() {
		return posts;
	}	
}

