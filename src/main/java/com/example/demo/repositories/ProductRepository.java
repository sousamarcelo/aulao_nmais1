package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	//Na aula o professor comenta que JOIN FECH não aceita 'Page' só 'List' mas nos testes o Page aceitou o 'JOIN FETCH' sem problemas, pode ser devida a versão mais atual do springdata
	
	/*
	@Query("SELECT obj FROM Product obj JOIN FETCH obj.categories")
	Page<Product> findProductsCategories(Pageable pageable);
	*/
	
	
	/* --o metodo 'findProductsCategories' de exemplo abaixo, representa esse sql
	 * SELECT * FROM tb_product 
	INNER JOIN tb_product_category ON tb_product.id = tb_product_category.product_id
	INNER JOIN tb_category ON tb_category.id = tb_product_category.category_id
	WHERE tb_product.id IN (1,2,3,4,5)
	 * */
	
	@Query("SELECT obj FROM Product obj JOIN FETCH obj.categories WHERE obj IN :product")
	List<Product> findProductsCategories(List<Product> product);

}
